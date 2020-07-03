package com.kszhub.demo.soundsystem;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 * @author Kong
 * @date 2020/6/29 14:45
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:applicationContext-constructure.xml")
public class CompactDiscTest {

    @Autowired
    private CompactDisc compactDisc1;

//    @Autowired
//    private CompactDisc compactDisc2;

//    @Autowired
//    @Qualifier("compactDisc2")
//    private CompactDisc compactDisc3;

    @Test
    public void test01(){
        compactDisc1.play();
//        compactDisc2.play();
//        compactDisc3.play();
    }
}
