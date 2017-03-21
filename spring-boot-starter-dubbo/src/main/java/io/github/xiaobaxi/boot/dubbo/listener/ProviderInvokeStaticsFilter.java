package io.github.xiaobaxi.boot.dubbo.listener;

import io.github.xiaobaxi.boot.dubbo.domain.ClassIdBean;
import io.github.xiaobaxi.boot.dubbo.domain.SpringBootStarterDobboConstants;

import com.alibaba.dubbo.common.Constants;
import com.alibaba.dubbo.common.URL;
import com.alibaba.dubbo.common.extension.Activate;
import com.alibaba.dubbo.rpc.Invocation;
import com.alibaba.dubbo.rpc.Invoker;
import com.alibaba.dubbo.rpc.Result;
import com.alibaba.dubbo.rpc.RpcException;

/**
 * provider invoke statics filter
 * 
 * @author fangzhibin
 *
 */
@Activate(group = Constants.PROVIDER)
public class ProviderInvokeStaticsFilter extends StaticsFilterHelper {

	@Override
	public Result invoke(Invoker<?> invoker, Invocation invocation) throws RpcException {
		Class<?> anInterface = invoker.getInterface();
		URL url = invoker.getUrl();
		String group = url.getParameter(SpringBootStarterDobboConstants.GROUP);
		String version = url.getParameter(SpringBootStarterDobboConstants.VERSION);
		ClassIdBean classIdBean = new ClassIdBean(anInterface, group, version);
		increase(classIdBean, invocation.getMethodName());
		return invoker.invoke(invocation);
	}
}
