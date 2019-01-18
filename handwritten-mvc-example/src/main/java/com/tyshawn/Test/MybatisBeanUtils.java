package com.tyshawn.Test;

import com.tyshawn.domain.User;

import java.beans.BeanInfo;
import java.beans.Introspector;
import java.beans.PropertyDescriptor;
import java.util.HashMap;
import java.util.Map;

/**
 * @author litianxiang
 */
public class MybatisBeanUtils {

    public static Map<String, Object> transBean2Map(Object obj) {
        Map<String, Object> map = new HashMap<String, Object>();
        if(obj == null){
            return map;
        }
        try {
            BeanInfo beanInfo = Introspector.getBeanInfo(obj.getClass());
            PropertyDescriptor[] propertyDescriptors = beanInfo.getPropertyDescriptors();
            for (PropertyDescriptor property : propertyDescriptors) {
                String key = property.getName();

                // 过滤class属性
                if (!key.equals("class") ) {
                    // 得到property对应的getter方法
                    map.put(key, property.getReadMethod().invoke(obj));
                }
            }
        } catch (Exception e) {
            throw new RuntimeException("ConvertBean2Map failed.errMsg=" + e.getMessage(),e);
        }
        return map;
    }

    public static void main(String[] args){
        User user = new User();
        user.setId(111);
        System.out.println(MybatisBeanUtils.transBean2Map(user));

    }
}
