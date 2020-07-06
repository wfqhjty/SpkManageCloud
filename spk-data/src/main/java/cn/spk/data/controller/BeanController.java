package cn.spk.data.controller;

import cn.spk.data.serivce.IBeanService;
import org.apache.poi.ss.formula.functions.T;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.servlet.filter.OrderedFilter;
import org.springframework.core.env.Environment;
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
    private IBeanService beanService;

    @PostMapping("/getBeanByType")
    public <T> List<T> getBeansByType(@RequestBody Map map) {
        String beanName = (String) map.get("beanName");
        Class clazz = null;
        switch (beanName) {
            case "Filter":
                clazz = Filter.class;
                break;
            case "OrderedFilter":
                clazz = OrderedFilter.class;
                break;
            default:
                break;
        }
        List list = beanService.getBeansByType(clazz);
        return list;
    }


}
