package cn.spk.data.serivce.impl;

import cn.spk.base.util.SpringContestUtils;
import cn.spk.data.serivce.IBeanService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class BeanServiceImpl implements IBeanService {

    @Autowired
    private SpringContestUtils springContestUtils;


    @Override
    public <T> List<T> getBeansByType(Class<T> requireType) {
        ApplicationContext applicationContext = springContestUtils.getApplicationContext();
        List<T> result = new ArrayList<>();
        String[] beanNmaes = applicationContext.getBeanNamesForType(requireType);
        for (String beanName : beanNmaes) {
            Object object = applicationContext.getBean(beanName);
            result.add((T) object);
        }
        return result;
    }
}
