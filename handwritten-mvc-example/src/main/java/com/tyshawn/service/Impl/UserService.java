package com.tyshawn.service.Impl;

import com.tyshawn.domain.User;
import com.tyshawn.framework.annotation.Service;
import com.tyshawn.framework.annotation.Transactional;
import com.tyshawn.framework.helper.DatabaseHelper;
import com.tyshawn.service.IUserService;

import java.util.List;
import java.util.Map;

/**
 * @author litianxiang
 */
@Service
public class UserService implements IUserService {
    /**
     * 获取所有用户
     */
    public List<User> getAllUser() {
        String sql = "SELECT * FROM user";
        return DatabaseHelper.queryEntityList(User.class, sql);
    }

    /**
     * 根据id获取用户信息
     */
    public User GetUserInfoById(Integer id) {
        String sql = "SELECT * FROM user WHERE id = ?";
        return DatabaseHelper.queryEntity(User.class, sql, id);
    }

    /**
     * 修改用户信息
     */
    @Transactional
    public boolean updateUser(int id, Map<String, Object> fieldMap) {
        return DatabaseHelper.updateEntity(User.class, id, fieldMap);
    }
}
