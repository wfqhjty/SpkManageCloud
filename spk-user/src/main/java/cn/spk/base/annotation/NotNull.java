package cn.spk.base.annotation;

import java.lang.annotation.*;

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
@Documented
public @interface NotNull {
    String[] requires() default {};

    Class<?> param() default Object.class;
}
