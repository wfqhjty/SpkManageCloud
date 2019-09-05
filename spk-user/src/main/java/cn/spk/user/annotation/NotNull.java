package cn.spk.user.annotation;

import java.lang.annotation.*;

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
@Documented
public @interface NotNull {
    String[] requires() default {};

    Class<?> param() default Object.class;
}
