package com.tyshawn.framework.bean;

import java.lang.reflect.Method;

/**
 * 封装 Controller 信息
 */
public class Handler {

    /**
     * Controller 类
     */
    private Class<?> controllerClass;

    /**
     * Controller 方法
     */
    private Method controllerMethod;

    public Handler(Class<?> controllerClass, Method controllerMethod) {
        this.controllerClass = controllerClass;
        this.controllerMethod = controllerMethod;
    }

    public Class<?> getControllerClass() {
        return controllerClass;
    }

    public Method getControllerMethod() {
        return controllerMethod;
    }
}
