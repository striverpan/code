package com.pan.util.annotation;

import java.lang.annotation.*;

import static java.lang.annotation.ElementType.METHOD;
import static java.lang.annotation.ElementType.TYPE;

/**
 * Created by pan on 2018/2/7.
 */
@Target({METHOD,TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Inherited
@Documented
public @interface JdbcConfig {

    String ip();
    int port() default 3306;
    String database();
    String encoding();
    String loginName();
    String password();

}
