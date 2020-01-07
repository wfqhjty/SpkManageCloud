package cn.spk.user.controller;

import cn.spk.base.annotation.LogAnnotation;
import cn.spk.user.entity.FrameUser;
import cn.spk.user.service.IFrameUserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/user")
public class FrameUserController {

    private static final Logger logger = LoggerFactory.getLogger(FrameUserController.class);

    @Resource
    private IFrameUserService userService;

    @PostMapping("/queryAll")
    public List<FrameUser> queryAll() {
        return userService.listFrameUsers();
    }

    @LogAnnotation()
    @PostMapping(value = "/query/{uid}")
    public FrameUser queryUid(@PathVariable("uid") Integer uid) {
        FrameUser user = userService.selectByPrimaryKey(uid);
        return user;
    }

    @PostMapping("/queryByDeptId")
    public List<FrameUser> queryByDeptId(@RequestBody Map map) {
        int deptid = (Integer) map.get("deptid");
        return userService.selectByDeptid(deptid);
    }

    @PostMapping("/addUser")
    public void addMap(@RequestBody FrameUser frameUser) {
        System.out.println(1111);
        System.out.println(1111);
        System.out.println(1111);
    }
}
