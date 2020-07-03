package com.kszhub.demo;

/**
 * @author Kong
 * @date 2020/7/3 14:20
 */
public class Notepad {

    public Notepad() {
        super();
        System.out.println("Notepad的构造函数......" + this.toString());
    }

    public void init(){
        System.out.println("Notepada的初始化方法");
    }

    public void destroy(){
        System.out.println("Notepad的销毁方法");
    }
}
