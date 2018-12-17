package com.tyshawn.framework.proxy;

/**
 * 代理接口
 */
public interface Proxy {

    /**
     * 执行链式代理
     */
    Object doProxy(ProxyChain proxyChain) throws Throwable;
}