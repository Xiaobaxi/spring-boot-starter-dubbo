package com.hikvision.ga.boot.dubbo;

import io.github.xiaobaxi.boot.dubbo.annotation.EnableDubboAutoConfiguration;

import com.hikvision.ga.boot.dubbo.demo.EchoTestService;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

/**
 * Created by fangzhibin on 2017/1/3.
 */
@SpringBootApplication
@EnableDubboAutoConfiguration
public class DemoClient {

    public static void main(String[] args) {
        ConfigurableApplicationContext run = SpringApplication.run(DemoClient.class, args);
        EchoTestService echoTestService = run.getBean(EchoTestService.class);
        System.out.println(echoTestService.echo("demo-test"));
    }
}
