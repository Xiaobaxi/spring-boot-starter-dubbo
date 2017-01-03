package com.hikvision.ga.boot.dubbo;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.context.event.ApplicationPreparedEvent;
import org.springframework.context.ApplicationEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextClosedEvent;

/**
 * Created by fangzhibin on 2016/12/30.
 */
public class DubboHolderListener implements ApplicationListener {
    private static Logger LOGGER = LoggerFactory.getLogger(DubboHolderListener.class);

    private static Thread holdThread;
    private static Boolean running = Boolean.FALSE;

    @Override
    public void onApplicationEvent(ApplicationEvent event) {
        if (event instanceof ApplicationPreparedEvent) {
            if (running == Boolean.FALSE)
                running = Boolean.TRUE;
            if (holdThread == null) {
                holdThread = new Thread(new Runnable() {
                    @Override
                    public void run() {
                        LOGGER.debug(Thread.currentThread().getName());
                        while (running && !Thread.currentThread().isInterrupted()) {
                            try {
                                Thread.sleep(2000);
                            } catch (InterruptedException e) {
                            }
                        }
                    }
                }, "Dubbo-Holder");
                holdThread.setDaemon(false);
                holdThread.start();
            }
        }
        if (event instanceof ContextClosedEvent) {
            running = Boolean.FALSE;
            if (null != holdThread) {
                holdThread.interrupt();
                holdThread = null;
            }
        }
    }
}
