package com.tyshawn.framework.proxy;

import net.sf.cglib.proxy.MethodProxy;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

/**
 * 代理链
 */
public class ProxyChain {

    private final Class<?> targetClass; //目标类
    private final Object targetObject; //目标对象
    private final Method targetMethod; //目标方法
    private final MethodProxy methodProxy; //方法代理
    private final Object[] methodParams; //方法参数

    private List<Proxy> proxyList = new ArrayList<>(); //代理列表
    private int proxyIndex = 0; //代理索引

    public ProxyChain(Class<?> targetClass, Object targetObject, Method targetMethod, MethodProxy methodProxy, Object[] methodParams, List<Proxy> proxyList) {
        this.targetClass = targetClass;
        this.targetObject = targetObject;
        this.targetMethod = targetMethod;
        this.methodProxy = methodProxy;
        this.methodParams = methodParams;
        this.proxyList = proxyList;
    }

    public Object[] getMethodParams() {
        return methodParams;
    }

    public Class<?> getTargetClass() {
        return targetClass;
    }

    public Method getTargetMethod() {
        return targetMethod;
    }

    /**
     * 递归执行
     */
    public Object doProxyChain() throws Throwable {
        Object methodResult;
        if (proxyIndex < proxyList.size()) {
            //执行增强方法
            methodResult = proxyList.get(proxyIndex++).doProxy(this);
        } else {
            //目标方法最后执行且只执行一次
            methodResult = methodProxy.invokeSuper(targetObject, methodParams);
        }
        return methodResult;
    }
}