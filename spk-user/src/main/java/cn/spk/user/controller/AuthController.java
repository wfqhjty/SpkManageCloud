package cn.spk.user.controller;

import cn.spk.base.dict.Dict;
import cn.spk.user.entity.FrameUser;
import cn.spk.user.service.IFrameUserService;
import cn.spk.user.service.IRedisService;
import cn.spk.user.token.JwtToken;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    private static final Logger logger = LoggerFactory.getLogger(AuthController.class);

    @Resource
    private IRedisService redisService;

    @Resource
    private IFrameUserService userService;

    @Autowired
    private JwtToken jwtToken;


    /**
     * 验证用户token
     *
     * @param authorization
     * @return
     */
    @PostMapping("/checkToken")
    public FrameUser checkToken(@RequestBody String authorization) {
        if (!jwtToken.checkToken(authorization)) {
            System.out.println("token认证失败");
        }
        String username = jwtToken.getUserName(authorization);
        FrameUser user = userService.selectByName(username);
        //刷新过期时间
        redisService.setExpire(Dict.USER_TOKEN + username, authorization);
        return user;
    }

    /**
     * 用户登录
     *
     * @param hashMap
     * @return
     */
    @PostMapping("/login")
    public Map Login(@RequestBody Map hashMap) {
        Map result = new HashMap();
        String username = (String) hashMap.get(Dict.USERNAME);
        String password = (String) hashMap.get(Dict.PASSWORD);
        FrameUser frameUser = userService.selectByName(username);
        if (frameUser == null) {
            System.out.println("当前用户不存在");
        } else if (!frameUser.getPasswd().equals(password)) {
            System.out.println("当前用户密码不正确");
        }
        //返回token
        String user_token = jwtToken.sign(username);
        redisService.setExpire(Dict.USER_TOKEN + username, user_token);
        result.put(Dict.TOKEN, user_token);
        return result;
    }

    /**
     * 用户注册
     *
     * @param hashMap
     * @return
     */
    @PostMapping("/register")
    public Map register(@RequestBody Map hashMap) {
        String username = (String) hashMap.get(Dict.USERNAME);
        String password = (String) hashMap.get(Dict.PASSWORD);
        FrameUser frameUser = userService.selectByUsername(username);
        if (frameUser != null) {
            System.out.println("当前用户已存在");
        } else {
            frameUser = new FrameUser();
            frameUser.setUsername(username);
            frameUser.setPasswd(password);
            frameUser.setCreatedate(new Date());
            userService.insert(frameUser);
        }
        return new HashMap();
    }

}
