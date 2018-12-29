package com.tyshawn.pattern;

import net.sf.cglib.proxy.MethodProxy;

import java.lang.reflect.Method;

/**
 * @author litianxiang
 */
public class AccountAspect extends AspectProxy {

    /**
     * 切入点
     */
    public boolean isIntercept(Method method, Object[] args) throws Throwable {
        return method.getName().equals("transfer");
    }

    /**
     * 前置增强
     */
    public void before() throws Throwable {
        System.out.println("对转账人身份进行验证.");
    }
}
