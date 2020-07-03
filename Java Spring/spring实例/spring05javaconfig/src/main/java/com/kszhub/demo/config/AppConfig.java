package com.kszhub.demo.config;

import com.kszhub.demo.dao.UserDao;
import com.kszhub.demo.dao.impl.UserDaoCache;
import com.kszhub.demo.dao.impl.UserDaoNormal;
import com.kszhub.demo.service.UserService;
import com.kszhub.demo.service.impl.UserServiceNormal;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

/**
 * @author Kong
 * @date 2020/6/29 10:50
 */
@Configuration
public class AppConfig {

    @Bean
    public UserDao userDaoNormal(){
        System.out.println("创建UserDaoNormal对象");
        return new UserDaoNormal();
    }

    @Bean
    public UserDao userDaoCache(){
        System.out.println("创建UserDaoCache对象");
        return new UserDaoCache();
    }

    @Bean
    public UserService userServiceNormal(@Qualifier("userDaoNormal") UserDao userDao){
        System.out.println("创建UserService对象");
        UserServiceNormal userService = new UserServiceNormal();
        userService.setUserDao(userDao);
        return userService;
    }
}
