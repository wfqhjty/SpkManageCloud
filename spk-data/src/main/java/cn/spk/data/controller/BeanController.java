package cn.spk.data.controller;

import cn.spk.base.util.SpringContestUtils;
import cn.spk.data.callable.CallAbleFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.Filter;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping
public class BeanController {

    @Autowired
    private SpringContestUtils springContestUtils;

    @PostMapping("/getBeanByType")
    public <T> List<T> getBeansByType(@RequestBody Map map) {
        String beanName = (String) map.get("beanName");
        Class clazz = null;
        switch (beanName) {
            case "Filter":
                clazz = Filter.class;
                break;
            default:
                break;
        }
        List list = springContestUtils.getBeansByType(clazz.getClass());
        return list;
    }


}
