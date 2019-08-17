package cn.spk.user.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class IndexController {
    @GetMapping("/")
    public String defaultPage(){
        return "index";
    }
    @GetMapping("/index")
    public String index(){
        return "index";
    }
}
