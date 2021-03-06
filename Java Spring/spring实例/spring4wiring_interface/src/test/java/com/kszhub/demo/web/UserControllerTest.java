package com.kszhub.demo.web;

import com.kszhub.demo.config.AppConfig;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 * @author Kong
 * @date 2020/6/27 22:31A
 */
@RunWith(SpringJUnit4ClassRunner.class)
//@ContextConfiguration(classes = AppConfig.class)
@ContextConfiguration("classpath:applicationContext.xml")
public class UserControllerTest {

    @Autowired
    private UserController userController;

    @Test
    public void testAdd(){
        userController.add();
    }
}
