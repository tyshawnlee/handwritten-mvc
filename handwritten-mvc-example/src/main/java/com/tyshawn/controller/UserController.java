package com.tyshawn.controller;

import com.tyshawn.domain.User;
import com.tyshawn.framework.annotation.Autowired;
import com.tyshawn.framework.annotation.Controller;
import com.tyshawn.framework.annotation.RequestMapping;
import com.tyshawn.framework.annotation.RequestMethod;
import com.tyshawn.framework.bean.Data;
import com.tyshawn.framework.bean.Param;
import com.tyshawn.framework.bean.View;
import com.tyshawn.service.IUserService;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author litianxiang
 */
@Controller
public class UserController {
    @Autowired
    private IUserService userService;

    /**
     * 用户列表
     *
     * @return
     */
    @RequestMapping(value = "/userList", method = RequestMethod.GET)
    public View getUserList() {
        List<User> userList = userService.getAllUser();
        return new View("index.jsp").addModel("userList", userList);
    }

    /**
     * 用户详情
     *
     * @param param
     * @return
     */
    @RequestMapping(value = "/userInfo", method = RequestMethod.GET)
    public Data getUserInfo(Param param) {
        String id = (String) param.getParamMap().get("id");
        User user = userService.GetUserInfoById(Integer.parseInt(id));

        return new Data(user);
    }

    @RequestMapping(value = "/userEdit", method = RequestMethod.GET)
    public Data editUser(Param param) {
        String id = (String) param.getParamMap().get("id");
        Map<String, Object> fieldMap = new HashMap<>();
        fieldMap.put("age", 911);
        userService.updateUser(Integer.parseInt(id), fieldMap);

        return new Data("Success.");
    }

}
