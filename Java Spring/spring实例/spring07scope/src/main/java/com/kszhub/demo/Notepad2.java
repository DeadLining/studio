package com.kszhub.demo;

import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Lazy;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

/**
 * @author Kong
 * @date 2020/7/3 14:52
 */
@Component
//@Scope("prototype")
//@Scope(scopeName = "prototype")
@Scope(ConfigurableBeanFactory.SCOPE_SINGLETON)
@Lazy
public class Notepad2 {

    public Notepad2() {
        super();
        System.out.println("Notepad2的构造函数......" + this.toString());
    }

    @PostConstruct
    public void init(){
        System.out.println("Notepad2的初始化方法");
    }

    @PreDestroy
    public void destroy(){
        System.out.println("Notepad2的销毁方法");
    }
}
