package com.tyshawn.framework.bean;

import org.apache.commons.collections4.MapUtils;

import java.util.Map;

/**
 * 请求参数对象
 */
public class Param {

    private Map<String, Object> paramMap;

    public Param() {
    }

    public Param(Map<String, Object> paramMap) {
        this.paramMap = paramMap;
    }

    public Map<String, Object> getParamMap() {
        return paramMap;
    }

    public boolean isEmpty(){
        return MapUtils.isEmpty(paramMap);
    }
}
