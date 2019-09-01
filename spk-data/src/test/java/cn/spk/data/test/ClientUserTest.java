package cn.spk.data.test;

import cn.spk.data.conf.ClientUserConfigProperties;
import cn.spk.data.entity.FrameDept;
import cn.spk.data.entity.FrameUser;
import cn.spk.data.util.Util;
import com.alibaba.fastjson.JSON;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.web.client.RestTemplate;

import javax.annotation.Resource;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
public class ClientUserTest {

    @Resource
    private RestTemplate restTemplate;

    @Autowired
    private ClientUserConfigProperties clientUserConfigProperties;

    @Test
    public void getUsers() {
        String url = clientUserConfigProperties.getUrl();
        System.out.println("url=" + url);
        String body = Util.httpExchangeGet(url + "frameUserController/getFrameUsers", restTemplate);
        System.out.println(JSON.parseArray(body, FrameUser.class));
    }

    @Test
    public void getDepts(){
        String url = clientUserConfigProperties.getUrl();
        System.out.println("url=" + url);
        String body = Util.httpExchangeGet(url + "frameDeptController/getFrameDepts", restTemplate);
        System.out.println(JSON.parseArray(body, FrameDept.class));
    }


}