package com.hikvision.ga.boot.dubbo;

import io.github.xiaobaxi.boot.dubbo.annotation.EnableDubboAutoConfiguration;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * Created by fangzhibin on 2017/1/3.
 */
@SpringBootApplication
@EnableDubboAutoConfiguration
public class DemoServer {

    public static void main(String[] args) {
        SpringApplication.run(DemoServer.class, args);
    }
}
