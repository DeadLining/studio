package com.kszhub.demo.service.impl;

import com.kszhub.demo.dao.UserDao;
import com.kszhub.demo.service.UserService;

/**
 * @author Kong
 * @date 2020/6/29 11:08
 */
public class UserServiceNormal implements UserService {

    private UserDao userDao;

    public UserServiceNormal() {
        super();
    }

    public UserServiceNormal(UserDao userDao) {
        this.userDao = userDao;
    }

    public void setUserDao(UserDao userDao) {
        this.userDao = userDao;
    }

    @Override
    public void add() {
        userDao.add();
    }
}
