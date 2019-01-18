package com.tyshawn.framework.proxy;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.lang.reflect.Method;

/**
 * 切面代理 抽象类
 */
public abstract class AspectProxy implements Proxy {

    private static final Logger logger = LoggerFactory.getLogger(AspectProxy.class);

    @Override
    public final Object doProxy(ProxyChain proxyChain) throws Throwable {
        Object result = null;

        Class<?> cls = proxyChain.getTargetClass();
        Method method = proxyChain.getTargetMethod();
        Object[] params = proxyChain.getMethodParams();

        begin();
        try {
            if (intercept(method, params)) {
                before(method, params);
                result = proxyChain.doProxyChain();
                after(method, params);
            } else {
                result = proxyChain.doProxyChain();
            }
        } catch (Exception e) {
            logger.error("proxy failure", e);
            error(method, params, e);
            throw e;
        } finally {
            end();
        }

        return result;
    }

    public void begin() {
    }

    public boolean intercept(Method method, Object[] params) throws Throwable {
        return true;
    }

    public void before(Method method, Object[] params) throws Throwable {
    }

    public void after(Method method, Object[] params) throws Throwable {
    }

    public void error(Method method, Object[] params, Throwable e) {
    }

    public void end() {
    }
}