package com.tyshawn.framework.proxy;

import net.sf.cglib.proxy.MethodInterceptor;
import net.sf.cglib.proxy.MethodProxy;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.lang.reflect.Method;

/**
 * @author litianxiang
 */
public abstract class BaseAspect implements MethodInterceptor {
    private static final Logger logger = LoggerFactory.getLogger(BaseAspect.class);

    /**
     * 代理方法, 每次调用目标方法时都会进到这里
     */
    @Override
    public Object intercept(Object obj, Method method, Object[] args, MethodProxy methodProxy) throws Throwable {
        Object result = null;

        begin();
        try {
            if (intercept(method, args)) {
                before(method, args);
                result = methodProxy.invoke(obj, args);
                after(method, args);
            } else {
                result = methodProxy.invoke(obj, args);
            }
        } catch (Exception e) {
            logger.error("proxy failure", e);
            error(method, args, e);
            throw e;
        } finally {
            end();
        }

        return result;
    }

    /**
     * 开始增强
     */
    public void begin() {
    }

    /**
     * 切入点判断
     */
    public boolean intercept(Method method, Object[] args) throws Throwable {
        return true;
    }

    /**
     * 前置增强
     */
    public void before(Method method, Object[] args) throws Throwable {
    }

    /**
     * 后置增强
     */
    public void after(Method method, Object[] args) throws Throwable {
    }

    /**
     * 异常增强
     */
    public void error(Method method, Object[] args, Throwable e) {
    }

    /**
     * 最终增强
     */
    public void end() {
    }
}
