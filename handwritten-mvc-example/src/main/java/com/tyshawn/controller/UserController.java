package com.tyshawn.controller;

import com.tyshawn.domain.UserBean;
import com.tyshawn.framework.annotation.Autowired;
import com.tyshawn.framework.annotation.Controller;
import com.tyshawn.framework.annotation.RequestMapping;
import com.tyshawn.framework.annotation.RequestMethod;
import com.tyshawn.framework.bean.Data;
import com.tyshawn.framework.bean.Param;
import com.tyshawn.framework.bean.View;
import com.tyshawn.service.IUserService;

import java.util.List;

/**
 * @author litianxiang
 */
@Controller
public class UserController {
    @Autowired
    private IUserService userService;

    @RequestMapping(value = "/userList", method = RequestMethod.GET)
    public View getUserList() {
        List<UserBean> userList = userService.getAllUser();
        try {
            //延迟3s, 模拟性能
            Thread.sleep(3000L);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        return new View("index.jsp").addModel("userList", userList);
    }

    @RequestMapping(value = "/userEdit", method = RequestMethod.GET)
    public Data getUserInfo(Param param) {
        String id = (String) param.getParamMap().get("id");
        UserBean userBean = userService.GetUserInfoById(Integer.parseInt(id));

        return new Data(userBean);
    }
}
