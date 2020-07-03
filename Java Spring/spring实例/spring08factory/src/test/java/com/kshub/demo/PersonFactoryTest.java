package com.kshub.demo;

import com.kszhub.demo.Person;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 * @author Kong
 * @date 2020/7/3 16:11
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:applicationContext.xml")
public class PersonFactoryTest {

    @Autowired
    private Person person1;

    @Autowired
    private Person person2;

    @Test
    public void test01(){
        System.out.println(person1);
        System.out.println(person2);
    }
}
