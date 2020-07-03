package com.kszhub.demo.service.impl;

import com.kszhub.demo.service.UserService;
import org.springframework.stereotype.Component;

/**
 * @author Kong
 * @date 2020/6/27 21:33
 */
@Component
public class UserServiceFestival implements UserService {

    public UserServiceFestival() {
        super();
        System.out.println("Festival");
    }

    @Override
    public void add() {
        System.out.println("注册用户并发送优惠卷");
    }
}
