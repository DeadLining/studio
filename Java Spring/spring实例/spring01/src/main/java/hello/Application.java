package hello;

/**
 * Created by K 2020/6/23
 */
public class Application {
    public static void main(String[] args){
        System.out.println("application");
        //连接打印机对象
        MessagePrinter printer = new MessagePrinter();
        //创建消息服务对象
        MessageService service = new MessageService();
        //设置打印机对象的service属性
        printer.setService(service);
        //打印消息
        printer.printMessage();
    }
}
