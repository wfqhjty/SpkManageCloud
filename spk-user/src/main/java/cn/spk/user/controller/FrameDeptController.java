package cn.spk.user.controller;

import cn.spk.user.entity.FrameDept;
import cn.spk.user.service.IFrameDeptService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

@RestController
@RequestMapping("/dept")
public class FrameDeptController {
    @Resource
    IFrameDeptService frameDeptService;

    @PostMapping("/queryAll")
    public List<FrameDept> getFrameDepts() {
        return frameDeptService.listFrameDepts();
    }
}
