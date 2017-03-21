package com.hikvision.ga.boot.dubbo.demo;

import io.github.xiaobaxi.boot.dubbo.annotation.DubboConsumer;

import org.springframework.stereotype.Component;

/**
 * Create by fangzhibin on 2017/01/03.
 */
@Component
public class EchoTestService {

	@DubboConsumer
    private EchoService echoService;

    public String echo(String str) {
        return echoService.echo(str);
    }

}