package com.hikvision.ga.boot.dubbo;

import org.springframework.context.annotation.Import;

import java.lang.annotation.*;

/**
 * Created by fangzhibin on 2016/12/30.
 */
@Target({ ElementType.METHOD, ElementType.TYPE })
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Import(value = { DubboAutoConfiguration.class })
public @interface EnableDubboAutoConfiguration {

}
