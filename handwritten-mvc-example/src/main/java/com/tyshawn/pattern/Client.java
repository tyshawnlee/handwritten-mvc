package com.tyshawn.pattern;


import net.sf.cglib.proxy.Enhancer;

import java.lang.reflect.Proxy;

/**
 * @author litianxiang
 */
public class Client {
    public static void main(String[] args) {
        //创建目标对象
        IAccountService target = new AccountServiceImpl();
        //切面
        AccountAspect accountAspect = new AccountAspect();
        //创建代理对象
        IAccountService proxy = (IAccountService) ProxyFactory.createProxy(target.getClass(), accountAspect);
        proxy.transfer();
    }
}
