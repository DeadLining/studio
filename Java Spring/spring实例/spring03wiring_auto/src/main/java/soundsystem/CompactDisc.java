package soundsystem;

import org.springframework.stereotype.Component;

/**
 * @author Kong
 * @date 2020/6/27 17:05
 */
//@Component
public class CompactDisc {

    public CompactDisc() {
        super();
        System.out.println("CompactDisc无参构造函数");
    }

    public void play(){
        System.out.println("正在播放音乐......");
    }
}
