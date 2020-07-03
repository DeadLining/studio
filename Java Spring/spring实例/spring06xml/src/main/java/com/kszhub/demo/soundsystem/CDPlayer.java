package com.kszhub.demo.soundsystem;

/**
 * @author Kong
 * @date 2020/6/30 20:59
 */
public class CDPlayer {

    private CompactDisc cd;

    public CDPlayer() {
        super();
        System.out.println("CDPlayer的构造函数" + this.toString());
    }

    public CDPlayer(CompactDisc cd) {
        this.cd = cd;
        System.out.println("CDPlayer的有参构造函数" + this.toString());
    }

    public CompactDisc getCd() {
        return cd;
    }

    public void setCd(CompactDisc cd) {
        this.cd = cd;
        System.out.println("——在" + this.toString() + "中注入cd");
    }

    public void play(){
        System.out.println("CDPlayer：" + this.toString());
        cd.play();
    }
}
