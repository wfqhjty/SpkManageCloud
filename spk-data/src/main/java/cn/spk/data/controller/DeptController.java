package cn.spk.data.controller;


import cn.spk.data.conf.ClientUserConfigProperties;
import cn.spk.data.entity.FrameDept;
import cn.spk.data.entity.FrameUser;
import cn.spk.data.util.Util;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
public class DeptController {
    @Resource
    private RestTemplate restTemplate;

    @Autowired
    private ClientUserConfigProperties clientUserConfigProperties;

    @PostMapping("/getDepts")
    public List<FrameDept> getDepts() {
        String url = clientUserConfigProperties.getUrl();
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.add("source", "source");
        Map<String, String> param = new HashMap<>();
        String body = Util.httpExchangePost(url + "dept/queryAll", restTemplate, httpHeaders, param);
        return JSON.parseArray(body, FrameDept.class);
    }

    @PostMapping("/stepDepts")
    public List<JSONObject> stepDepts() {
        String url = clientUserConfigProperties.getUrl();
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.add("source", "source");
        Map<String, String> param = new HashMap<>();
        String body = Util.httpExchangePost(url + "dept/queryAll", restTemplate, httpHeaders, param);
        List<FrameDept> result = JSON.parseArray(body, FrameDept.class);
        List<JSONObject> objectList = new ArrayList<>();
        for (FrameDept frameDept : result) {
            JSONObject jsonObject = new JSONObject();
            if (frameDept.getParentid() == null) {
                jsonObject.put("id", frameDept.getDeptid());
                jsonObject.put("label", frameDept.getDeptname());
                objectList.add(jsonObject);
            }
        }
        for (JSONObject object : objectList) {
            List<JSONObject> childList = new ArrayList<>();
            for (FrameDept frameDept : result) {
                if (frameDept.getParentid() == object.get("id")) {
                    JSONObject jsonObject = new JSONObject();
                    jsonObject.put("id", frameDept.getDeptid());
                    jsonObject.put("label", frameDept.getDeptname());
                    childList.add(jsonObject);
                }
            }
            object.put("children", childList);
        }
        return objectList;
    }
}
