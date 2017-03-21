package com.hikvision.ga.boot.dubbo.demo;

import com.alibaba.dubbo.config.annotation.Service;

/**
 * Created by fangzhibin on 2017/1/3.
 */
@Service(interfaceClass = EchoService.class)
public class EchoServiceImpl implements EchoService {

    @Override
    public String echo(String str) {
        System.out.println(str);
        return str;
    }
}
