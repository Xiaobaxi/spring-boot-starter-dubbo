package com.hikvision.ga.boot.dubbo.demo;

import com.alibaba.dubbo.common.extension.Activate;
import com.alibaba.dubbo.config.annotation.Reference;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * Create by fangzhibin on 2017/01/03.
 */
@Component
public class EchoTestService {

    @Autowired
    private EchoService echoService;

    public String echo(String str) {
        return echoService.echo(str);
    }

}