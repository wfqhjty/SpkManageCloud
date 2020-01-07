package cn.spk.base.util;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class SpringContestUtils implements ApplicationContextAware {

    private ApplicationContext applicationContext;

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        this.applicationContext = applicationContext;
    }

    public <T> List<T> getBeansByType(Class<T> requireType) {
        List<T> result = new ArrayList<>();
        String[] beanNmaes = applicationContext.getBeanNamesForType(requireType);
        for (String beanName : beanNmaes) {
            Object object = applicationContext.getBean(beanName);
            result.add((T) object);
        }
        return result;
    }


}
