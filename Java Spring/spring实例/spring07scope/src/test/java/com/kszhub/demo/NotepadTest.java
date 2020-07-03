package com.kszhub.demo;

import org.junit.Test;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * @author Kong
 * @date 2020/7/3 14:22
 */
public class NotepadTest {

    /**
     * scope="singleton"
     * (1) 无论我们是否去主动获取或注入bean对象，spring上下文一加载就会创建bean对象
     * (2) 无论获取多少次，拿到的都是用一个对象
     * (3) 无论注入多少次，拿到的都是用一个对象
     *
     * scope="prototype"
     * (1) Spring上下文加载的时候不会创建bean对象
     * (2) 每次获取，都会拿到不同的bean对象
     * (3) 每次注入，都会拿到不同的bean对象
     */
    @Test
    public void test01(){

        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");
        Object notepad1 = (Notepad)context.getBean("notepad");
//        Object notepad2 = (Notepad)context.getBean("notepad");
//        System.out.println(notepad1 == notepad2);

        context.destroy();
        context.close();
    }
}
