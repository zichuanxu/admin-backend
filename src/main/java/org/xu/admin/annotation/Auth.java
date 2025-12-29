package org.xu.admin.annotation;

import java.lang.annotation.*;

@Target({ElementType.METHOD, ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
public @interface Auth {
    // 是否必须登录
    boolean required() default true;
    // 是否必须是管理员 (true则必须 admin=1)
    boolean mustAdmin() default false;
}