package com.tyshawn.pattern;

import net.sf.cglib.proxy.MethodInterceptor;
import net.sf.cglib.proxy.MethodProxy;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.lang.reflect.Method;

/**
 * @author litianxiang
 */
public abstract class AspectProxy implements MethodInterceptor {
    private static final Logger logger = LoggerFactory.getLogger(AspectProxy.class);

    @Override
    public Object intercept(Object obj, Method method, Object[] args, MethodProxy methodProxy) throws Throwable {
        Object result = null;

        begin();
        try {
            if (isIntercept(method, args)) {
                before();
                result = methodProxy.invokeSuper(obj, args);
                after();
            } else {
                result = methodProxy.invokeSuper(obj,args);
            }
        } catch (Exception e) {
            logger.error("proxy failure", e);
            error(e);
            throw e;
        } finally {
            end();
        }
        return result;
    }

    public void begin() {
    }

    public boolean isIntercept(Method method, Object[] args) throws Throwable {
        return true;
    }

    public void before() throws Throwable {
    }

    public void after() throws Throwable {
    }

    public void error(Throwable e) {
    }

    public void end() {
    }
}
