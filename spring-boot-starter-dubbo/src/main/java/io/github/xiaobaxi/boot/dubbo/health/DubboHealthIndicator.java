package io.github.xiaobaxi.boot.dubbo.health;

import org.springframework.boot.actuate.health.AbstractHealthIndicator;
import org.springframework.boot.actuate.health.Health;
import org.springframework.stereotype.Component;

import io.github.xiaobaxi.boot.dubbo.DubboConsumerAutoConfiguration;
import io.github.xiaobaxi.boot.dubbo.domain.ClassIdBean;
import io.github.xiaobaxi.boot.dubbo.listener.ConsumerSubscribeListener;
import com.alibaba.dubbo.rpc.service.EchoService;

/**
 * dubbo health indicator
 * 
 * @author fangzhibin
 *
 */
@Component
public class DubboHealthIndicator extends AbstractHealthIndicator {

	@Override
	protected void doHealthCheck(Health.Builder builder) throws Exception {
		for (ClassIdBean classIdBean : ConsumerSubscribeListener.SUBSCRIBED_INTERFACES_SET) {
			Object service = DubboConsumerAutoConfiguration.DUBBO_REFERENCES_MAP.get(classIdBean);
			EchoService echoService = (EchoService) service;
			if (echoService != null) {
				echoService.$echo("Hello");
				builder.withDetail(classIdBean.toString(), true);
			}
		}
		builder.up();
	}

}
