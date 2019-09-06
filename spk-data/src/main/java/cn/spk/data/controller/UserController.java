package cn.spk.data.controller;

import cn.spk.data.conf.ClientUserConfigProperties;
import cn.spk.data.entity.FrameUser;
import cn.spk.data.util.Util;
import com.alibaba.fastjson.JSON;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
public class UserController {

    @Resource
    private RestTemplate restTemplate;

    @Autowired
    private ClientUserConfigProperties clientUserConfigProperties;

    @PostMapping("/getUsers")
    public List<FrameUser> getUsers() {
        String url = clientUserConfigProperties.getUrl();
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.add("source", "source");
        Map<String, String> param = new HashMap<>();
        String body = Util.httpExchangePost(url + "user/queryAll", restTemplate, httpHeaders, param);
        System.out.println(JSON.parseArray(body, FrameUser.class));
        return JSON.parseArray(body, FrameUser.class);
    }

    @PostMapping("/getUsersByDeptid")
    public List<FrameUser> getUsersByDeptid(@RequestBody Map map) {
        String url = clientUserConfigProperties.getUrl();
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.add("source", "source");
        Map<String, Integer> param = new HashMap<>();
        param.put("deptid", (Integer) map.get("deptid"));
        HttpEntity<Object> request = new HttpEntity<>(param, httpHeaders);
        String str = Util.httpExchangePost(url + "user/queryByDeptId", restTemplate, httpHeaders, param);
        return JSON.parseArray(str, FrameUser.class);
    }

}
