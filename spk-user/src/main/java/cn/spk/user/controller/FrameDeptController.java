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
@RequestMapping("/api/dept")
public class FrameDeptController {

    @Resource
    IFrameDeptService frameDeptService;

    @PostMapping("/query")
    public List<FrameDept> query() {
        return frameDeptService.query();
    }
}
