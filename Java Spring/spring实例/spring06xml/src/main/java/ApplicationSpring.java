import com.kszhub.demo.soundsystem.CompactDisc;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * @author Kong
 * @date 2020/6/29 14:22
 */
public class ApplicationSpring {

    public static void main(String[] args) {
        System.out.println("AplicationSpring is running......");

        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("applicationContext-constructure.xml");
        CompactDisc cd = (CompactDisc)context.getBean("compactDisc1");
        cd.play();
    }
}
