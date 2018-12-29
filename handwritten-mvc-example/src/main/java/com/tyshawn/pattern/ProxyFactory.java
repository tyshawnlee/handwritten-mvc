package com.tyshawn.pattern;

import net.sf.cglib.proxy.Enhancer;
import net.sf.cglib.proxy.MethodInterceptor;

/**
 * @author litianxiang
 */
public class ProxyFactory {

    @SuppressWarnings("unchecked")
    public static <T> T createProxy(final Class<?> targetClass, final MethodInterceptor methodInterceptor) {
        return (T) Enhancer.create(targetClass,methodInterceptor);
    }
}
