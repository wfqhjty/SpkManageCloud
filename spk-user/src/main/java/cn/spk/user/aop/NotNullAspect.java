package cn.spk.user.aop;

import cn.spk.user.annotation.NotNull;
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
public class NotNullAspect {
    //Controller层切点
    @Pointcut("@annotation(cn.spk.user.annotation.NotNull)")
    public void controllerAspect() {

    }

    @Around("aroundController(notNull)")
    public Object doAround(ProceedingJoinPoint pjp, NotNull notNull) throws Throwable {
        Object[] args = pjp.getArgs();//获取注解方法的入参
        //MethodInvocationProceedingJoinPoint methodInvocationProceedingJoinPoint = (MethodInvocationProceedingJoinPoint) pjp;
        //MethodSignature signature = ((MethodSignature) pjp.getSignature());
        //得到拦截的方法
        //Method method = signature.getMethod();
        // NotNull notNull = method.getAnnotation(NotNull.class);
        String[] require = notNull.requires();
        Object param = null;
        for (Object object : args) {
            if (object.getClass() == notNull.param())
                param = object;
        }
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
