package com.kszhub.demo.web;

import com.kszhub.demo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;

/**
 * @author Kong
 * @date 2020/6/27 22:25
 */
//@Component
@Controller
public class UserController {

    @Autowired
    @Qualifier("userServiceNormal")
    private UserService userService;

    public void add(){
        userService.add();
    }
}
