package com.kszhub.demo.service.impl;

import com.kszhub.demo.dao.UserDao;
import com.kszhub.demo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

/**
 * @author Kong
 * @date 2020/6/27 21:18
 */
//@Component
@Service
public class UserServiceNormal implements UserService {

    @Autowired
    private UserDao userDao;

    public UserServiceNormal() {
        super();
        System.out.println("Normal");
    }

    @Override
    public void add() {
        userDao.add();
    }
}
