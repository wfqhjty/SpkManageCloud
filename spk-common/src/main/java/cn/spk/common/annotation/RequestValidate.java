package cn.spk.common.annotation;

import java.lang.annotation.*;

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
@Documented
public @interface RequestValidate {
    String[] requires() default {};

    Class<?> param() default Object.class;
}
