package cn.spk.data.controller;

import cn.spk.base.util.SpringContestUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import javax.servlet.Filter;

@Controller
public class IndexController {

    @GetMapping("/")
    public String defaultPage() {
        return "index";
    }

    @GetMapping("/index")
    public String index() {
        return "index";
    }
}
