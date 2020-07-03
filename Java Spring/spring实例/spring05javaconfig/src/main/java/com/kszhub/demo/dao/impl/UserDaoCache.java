package com.kszhub.demo.dao.impl;

import com.kszhub.demo.dao.UserDao;

/**
 * @author Kong
 * @date 2020/6/29 11:37
 */
public class UserDaoCache implements UserDao {
    @Override
    public void add() {
        System.out.println("添加用户到缓存中......");
    }
}
