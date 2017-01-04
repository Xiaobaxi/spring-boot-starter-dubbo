package com.hikvision.ga.boot.dubbo;

import com.alibaba.dubbo.config.spring.ServiceBean;
import com.hikvision.ga.boot.dubbo.demo.EchoService;
import com.hikvision.ga.boot.dubbo.demo.EchoServiceImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Created by fangzhibin on 2017/1/4.
 */
@Configuration
public class BeanConfiguration {

    @Bean
    public ServiceBean<EchoService> echoService() {
        ServiceBean<EchoService> serviceBean = new ServiceBean<EchoService>();
        serviceBean.setInterface(EchoService.class);
        //开启监控
        serviceBean.setRef(new EchoServiceImpl());
        return serviceBean;
    }
}
