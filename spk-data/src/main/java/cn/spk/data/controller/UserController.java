package cn.spk.data.controller;

import cn.spk.data.conf.ClientUserConfigProperties;
import cn.spk.data.entity.FrameUser;
import cn.spk.data.util.Util;
import com.alibaba.fastjson.JSON;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import javax.annotation.Resource;
import java.util.List;

@RestController
public class UserController {

    @Resource
    private RestTemplate restTemplate;

    @Autowired
    private ClientUserConfigProperties clientUserConfigProperties;

    @GetMapping("/getUsers")
    public List<FrameUser> getUsers() {
        String url = clientUserConfigProperties.getUrl();
        String body = Util.httpExchangeGet(url, restTemplate);
        return JSON.parseArray(body, FrameUser.class);
    }


}
