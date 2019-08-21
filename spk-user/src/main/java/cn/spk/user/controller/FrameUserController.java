package cn.spk.user.controller;

import cn.spk.user.annotation.LogAnnotation;
import cn.spk.user.entity.FrameUser;
import cn.spk.user.service.IFrameUserService;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.List;

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

    @GetMapping("/getUsers")
    public List<FrameUser> getUsers() {
        return userService.listFrameUsers();
    }

}
