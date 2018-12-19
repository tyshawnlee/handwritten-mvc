package com.tyshawn.service;

import com.tyshawn.domain.UserBean;
import com.tyshawn.framework.annotation.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * @author litianxiang
 */
@Service
public class UserService {
    /**
     * 获取所有用户
     */
    public List<UserBean> getAllUser(){
        List<UserBean> userList = new ArrayList<>();
        userList.add(new UserBean(1,"Tyshawn", 23));
        userList.add(new UserBean(2,"Bob", 32));

        return userList;
    }

    /**
     * 根据id获取用户信息
     */
    public UserBean GetUserInfoById(Integer id) {
        UserBean bean = new UserBean(1,"tyshawn", 23);
        return bean;
    }
}
