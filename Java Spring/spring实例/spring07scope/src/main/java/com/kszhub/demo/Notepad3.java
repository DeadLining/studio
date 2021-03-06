package com.kszhub.demo;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

/**
 * @author Kong
 * @date 2020/7/3 14:20
 */
public class Notepad3 {

    public Notepad3() {
        super();
        System.out.println("Notepad3的构造函数......" + this.toString());
    }

    @PostConstruct
    public void init(){
        System.out.println("Notepad3的初始化方法");
    }

    @PreDestroy
    public void destroy(){
        System.out.println("Notepad3的销毁方法");
    }
}
