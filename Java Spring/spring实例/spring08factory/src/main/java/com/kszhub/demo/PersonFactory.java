package com.kszhub.demo;

/**
 * @author Kong
 * @date 2020/7/3 16:08
 */
public class PersonFactory {

    /**
     * 静态工厂方法
     * @return
     */
    public static Person createPerson1(){
        System.out.println("静态工厂创建Person......");
        return new Person();
    }

    /**
     * 实例工厂方法
     * @return
     */
    public Person createPerson2(){
        System.out.println("实例工厂创建Person......");
        return new Person();
    }
}
