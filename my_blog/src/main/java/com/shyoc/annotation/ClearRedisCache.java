package com.shyoc.annotation;

import java.lang.annotation.*;


@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface ClearRedisCache {
    /**
     * 需要级联删除的缓存
     * @return
     */
    Class<?>[] cascade() default {};
}
