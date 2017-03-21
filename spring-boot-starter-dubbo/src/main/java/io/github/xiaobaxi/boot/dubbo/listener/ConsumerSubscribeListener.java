package io.github.xiaobaxi.boot.dubbo.listener;

import io.github.xiaobaxi.boot.dubbo.domain.ClassIdBean;
import io.github.xiaobaxi.boot.dubbo.domain.SpringBootStarterDobboConstants;

import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

import com.alibaba.dubbo.common.URL;
import com.alibaba.dubbo.common.extension.Activate;
import com.alibaba.dubbo.common.utils.ConcurrentHashSet;
import com.alibaba.dubbo.rpc.Invoker;
import com.alibaba.dubbo.rpc.RpcException;
import com.alibaba.dubbo.rpc.listener.InvokerListenerAdapter;

/**
 * dubbo client invoker listener
 * 
 * @author fangzhibin
 *
 */
@Activate
public class ConsumerSubscribeListener extends InvokerListenerAdapter {
	public static final Set<ClassIdBean> SUBSCRIBED_INTERFACES_SET = new ConcurrentHashSet<ClassIdBean>();

	public static final Map<ClassIdBean, Set<String>> CONNECTION_MAP = new ConcurrentHashMap<ClassIdBean, Set<String>>();

	@Override
	public void referred(Invoker<?> invoker) throws RpcException {
		Class<?> anInterface = invoker.getInterface();
		URL url = invoker.getUrl();
		String group = url.getParameter(SpringBootStarterDobboConstants.GROUP);
		String version = url.getParameter(SpringBootStarterDobboConstants.VERSION);
		ClassIdBean classIdBean = new ClassIdBean(anInterface, group, version);
		SUBSCRIBED_INTERFACES_SET.add(classIdBean);
		Set<String> connectionSet = CONNECTION_MAP.get(classIdBean);
		if (connectionSet == null) {
			connectionSet = new ConcurrentHashSet<String>();
			CONNECTION_MAP.put(classIdBean, connectionSet);
		}
		connectionSet.add(invoker.getUrl().toString());
	}

	@Override
	public void destroyed(Invoker<?> invoker) {
		Class<?> anInterface = invoker.getInterface();
		URL url = invoker.getUrl();
		String group = url.getParameter(SpringBootStarterDobboConstants.GROUP);
		String version = url.getParameter(SpringBootStarterDobboConstants.VERSION);
		ClassIdBean classIdBean = new ClassIdBean(anInterface, group, version);
		SUBSCRIBED_INTERFACES_SET.remove(classIdBean);
		Set<String> connectionSet = CONNECTION_MAP.get(classIdBean);
		if (connectionSet != null) {
			connectionSet.remove(invoker.getUrl().toString());
		}
		if (connectionSet == null || connectionSet.size() == 0) {
			CONNECTION_MAP.remove(classIdBean);
		}
	}
}
