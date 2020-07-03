package com.kszhub.demo.config;

import com.kszhub.demo.dao.UserDao;
import com.kszhub.demo.service.UserService;
import com.kszhub.demo.web.UserController;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

/**
 * @author Kong
 * @date 2020/6/27 21:19
 */
@Configuration
@ComponentScan("com.kszhub.demo")
//@ComponentScan(basePackages = {"com.kszhub.demo.web", "com.kszhub.demo.service", "com.kszhub.demo.dao"})
//@ComponentScan(basePackageClasses = {UserController.class, UserService.class, UserDao.class})
public class AppConfig {
}
