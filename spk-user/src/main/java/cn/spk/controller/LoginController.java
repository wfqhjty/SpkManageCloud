package cn.spk.controller;

import cn.spk.entity.FrameUser;
import cn.spk.service.IFrameUserService;
import cn.spk.service.IRedisService;
import cn.spk.util.JwtUtil;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.Date;

@RestController
@RequestMapping("/loginController")
public class LoginController {

    private static final Logger logger = LoggerFactory.getLogger(LoginController.class);

    @Resource
    private IRedisService redisService;

    @Resource
    private IFrameUserService userService;

    @PostMapping("/userLogin")
    public String userLogin(@RequestBody String params, HttpServletRequest request) {
        JSONObject jsonObject = JSON.parseObject(params);
        String username = jsonObject.getString("username");
        String passwd = jsonObject.getString("password");
        FrameUser frameUser = userService.selectByNamePasswd(username, passwd);
        if (frameUser != null) {
            //自定义Redis存储主键
            String uid = "uid_" + frameUser.getUserid();
            //返回token
            String token = JwtUtil.sign(frameUser.getUsername(), uid);
            redisService.setExpire(uid, token);
            HttpSession session = request.getSession();
            System.out.println("username" + session.getAttribute("username"));
            JSONObject jsonobject = new JSONObject();
            jsonobject.put("token", token);
            return jsonobject.toString();
        } else
            return "用户名和密码不正确";
    }

    @PostMapping( "/userRegister")
    public String userRegister(@RequestBody String params) {
        JSONObject jsonObject = JSON.parseObject(params);
        String username = jsonObject.getString("username");
        String passwd = jsonObject.getString("password");
        JSONObject object = new JSONObject();
        FrameUser frameUser = userService.selectByUsername(username);
        if (frameUser != null) {
            object.put("user", "exist");
        } else {
            frameUser = new FrameUser();
            frameUser.setUsername(username);
            frameUser.setPasswd(passwd);
            frameUser.setCreatedate(new Date());
            userService.insert(frameUser);
            object.put("user", "insert");
        }
        return object.toString();
    }

}
