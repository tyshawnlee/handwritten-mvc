package com.tyshawn.framework.bean;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;

/**
 * 封装请求信息
 */
public class Request {

    /**
     * 请求方法
     */
    private String requestMethod;

    /**
     * 请求路径
     */
    private String requestPath;

    public Request(String requestMethod, String requestPath) {
        this.requestMethod = requestMethod;
        this.requestPath = requestPath;
    }

    public String getRequestMethod() {
        return requestMethod;
    }

    public String getRequestPath() {
        return requestPath;
    }

    @Override
    public int hashCode() {
//        return HashCodeBuilder.reflectionHashCode(this);
        int result = 17;
        result = 31 * result + requestMethod.hashCode();
        result = 31 * result + requestPath.hashCode();
        return result;
    }

    @Override
    public boolean equals(Object obj) {
//        return EqualsBuilder.reflectionEquals(this, obj);
        if (this == obj) return true;
        if (!(obj instanceof Request)) return false;
        Request request = (Request) obj;
        return request.getRequestPath().equals(this.requestPath) && request.getRequestMethod().equals(this.requestMethod);
    }
}
