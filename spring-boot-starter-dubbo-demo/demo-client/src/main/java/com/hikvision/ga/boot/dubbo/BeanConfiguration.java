package com.hikvision.ga.boot.dubbo;

import com.alibaba.dubbo.config.spring.ReferenceBean;
import com.hikvision.ga.boot.dubbo.demo.EchoService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Created by fangzhibin on 2017/1/4.
 */
@Configuration
public class BeanConfiguration {

    @Bean
    public ReferenceBean<EchoService> echoService() {
        ReferenceBean<EchoService>  echoServiceReferenceBean = new ReferenceBean<EchoService>();
        echoServiceReferenceBean.setInterface(EchoService.class);
        return echoServiceReferenceBean;
    }
}
