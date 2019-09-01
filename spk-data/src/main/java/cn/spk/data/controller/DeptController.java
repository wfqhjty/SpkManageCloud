package cn.spk.data.controller;


import cn.spk.data.conf.ClientUserConfigProperties;
import cn.spk.data.entity.FrameDept;
import cn.spk.data.util.Util;
import com.alibaba.fastjson.JSON;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import javax.annotation.Resource;
import java.util.List;

@RestController
public class DeptController {
    @Resource
    private RestTemplate restTemplate;

    @Autowired
    private ClientUserConfigProperties clientUserConfigProperties;

    @GetMapping("/getDepts")
    public List<FrameDept> getDepts() {
        String url = clientUserConfigProperties.getUrl();
        System.out.println("url=" + url);
        String body = Util.httpExchangeGet(url + "frameUserController/getFrameDepts", restTemplate);
        System.out.println(JSON.parseArray(body, FrameDept.class));
        return JSON.parseArray(body, FrameDept.class);
    }
}
