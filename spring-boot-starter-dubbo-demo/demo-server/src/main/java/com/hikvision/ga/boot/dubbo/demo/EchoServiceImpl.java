package com.hikvision.ga.boot.dubbo.demo;

/**
 * Created by fangzhibin on 2017/1/3.
 */
public class EchoServiceImpl implements EchoService {

    @Override
    public String echo(String str) {
        System.out.println(str);
        return str;
    }
}
