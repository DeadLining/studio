package com.kszhub.demo.dao.impl;

import com.kszhub.demo.dao.UserDao;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

/**
 * @author Kong
 * @date 2020/6/27 22:20
 */
//@Component
@Repository
public class UserDaoNormal implements UserDao {

    @Override
    public void add() {
        System.out.println("添加用户到数据库中......");
    }
}
