package com.tyshawn.framework.annotation;

import java.lang.annotation.*;

/**
 * 切面注解
 */
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
public @interface Aspect {
    /**
     * 包名
     */
    String pkg() default "";

    /**
     * 类名
     */
    String cls() default "";
}