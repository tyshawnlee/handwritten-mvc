package com.tyshawn.service;

import com.tyshawn.domain.UserBean;

import java.util.List;

/**
 * @author litianxiang
 */
public interface IUserService {
    List<UserBean> getAllUser();

    UserBean GetUserInfoById(Integer id);
}
