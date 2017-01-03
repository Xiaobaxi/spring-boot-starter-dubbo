package com.hikvision.ga.boot.dubbo.demo;

import com.alibaba.dubbo.config.annotation.Reference;
import org.springframework.stereotype.Component;

/**
 * Create by fangzhibin on 2017/01/03.
 */
@Component
public class EchoTestService {

    @Reference(version = "1.0.0")
    private EchoService echoService;

    public String echo(String str) {
        return echoService.echo(str);
    }

}