package com.kszhub.demo.soundsystem;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 * @author Kong
 * @date 2020/7/1 20:55
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:applicationContext-property.xml")
public class AppTest {

    @Autowired
    private CDPlayer cdPlayer;

    @Test
    public void test(){
        cdPlayer.play();
    }
}
