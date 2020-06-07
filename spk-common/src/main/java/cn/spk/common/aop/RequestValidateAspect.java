package cn.spk.common.aop;

import cn.spk.common.annotation.RequestValidate;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

import java.lang.reflect.Field;
import java.util.Map;

/**
 * 判断入参时的Map或者Bean中的字段是否有为空
 */
@Component
@Aspect
public class RequestValidateAspect {
    //Controller层切点
    @Pointcut("@annotation(requestValidate)")
    public void controllerAspect(RequestValidate requestValidate) {

    }

    @Around("controllerAspect(requestValidate)")
    public Object doAround(ProceedingJoinPoint pjp, RequestValidate requestValidate) throws Throwable {
        Object[] args = pjp.getArgs();//获取注解方法的入参
        String[] require = requestValidate.requires();
        Object param = null;
        for (Object object : args) {
            if (object.getClass() == requestValidate.param())
                param = object;
        }
        if (param == null)
            System.out.println("annotation missing parameter");
        for (String str : require) {
            if (param instanceof Map) {
                Map map = (Map) param;
                if (map.get(str) == null) {
                    throw new Exception("obj为空");
                }
            } else {
                Class<?> clazz = param.getClass();
                Field field = clazz.getDeclaredField(str);
                field.setAccessible(true);
                Object obj = field.get(param);
                if (obj == null) {
                    throw new Exception("obj为空");
                }
            }
        }
        return pjp.proceed();
    }

}
