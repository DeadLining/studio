package com.kszhub.demo.dao.impl;

import com.kszhub.demo.dao.UserDao;

/**
 * @author Kong
 * @date 2020/6/29 10:47
 */
public class UserDaoNormal implements UserDao {

    @Override
    public void add() {
        System.out.println("添加用户到数据库中......");
    }
}
