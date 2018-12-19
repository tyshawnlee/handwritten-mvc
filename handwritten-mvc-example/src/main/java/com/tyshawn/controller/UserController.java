package com.tyshawn.controller;

import com.tyshawn.domain.UserBean;
import com.tyshawn.framework.annotation.Autowired;
import com.tyshawn.framework.annotation.Controller;
import com.tyshawn.framework.annotation.RequestMapping;
import com.tyshawn.framework.bean.Data;
import com.tyshawn.framework.bean.Param;
import com.tyshawn.framework.bean.View;
import com.tyshawn.service.UserService;

import java.util.List;

/**
 * @author litianxiang
 */
@Controller
public class UserController {
    @Autowired
    private UserService userService;

    @RequestMapping("get:/userList")
    public View getUserList(){
        List<UserBean> userList = userService.getAllUser();

        return new View("index.jsp").addModel("userList",userList);
    }

    @RequestMapping("get:/userEdit")
    public Data getUserInfo(Param param) {
        String id = (String) param.getParamMap().get("id");
        UserBean userBean = userService.GetUserInfoById(Integer.parseInt(id));

        return new Data(userBean);
    }
}
