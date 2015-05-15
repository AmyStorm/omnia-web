package com.omnia.module.command.user.annotation;


import java.lang.annotation.*;

/**
 * Created by Administrator on 2015/4/17.
 */
@Documented
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.METHOD, ElementType.TYPE})
public @interface Login {
    /**
     * True value to set the login access.
     * @return
     */
    boolean value() default true;

    /**
     * type for the return method,normal page forward or ajax.
     * @return
     */
    ReturnType type() default ReturnType.PAGE;
}

enum ReturnType{
    PAGE,
    AJAX
}
