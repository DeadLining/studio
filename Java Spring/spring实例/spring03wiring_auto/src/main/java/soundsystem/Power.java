package soundsystem;

import org.springframework.stereotype.Component;

/**
 * @author Kong
 * @date 2020/6/27 19:55
 */
@Component
public class Power {

    public Power() {
        super();
    }

    public void supply(){
        System.out.println("电源供电中......");
    }
}
