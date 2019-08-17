package cn.spk.controller;

import cn.spk.annotation.LogAnnotation;
import cn.spk.entity.FrameUser;
import cn.spk.service.IFrameUserService;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

@RestController
@RequestMapping("/frameUserController")
public class FrameUserController {

    private static final Logger logger = LoggerFactory.getLogger(FrameUserController.class);

    @Resource
    private IFrameUserService userService;

    @PostMapping("/selectByPrimaryKey")
    @LogAnnotation()
    public FrameUser selectByPrimaryKey(@RequestBody String params, HttpServletRequest request) {
        JSONObject jsonObject = JSON.parseObject(params);
        String userid = jsonObject.getString("userid");
        FrameUser user = userService.selectByPrimaryKey(Integer.parseInt(userid));
        return user;
    }

}
