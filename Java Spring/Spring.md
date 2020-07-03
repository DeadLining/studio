[TOC]

### 项目创建流程

1. 创建项目

2. 修改`pom.xml`文件，加入依赖

   ```java
   <dependencies>
   	<dependency>
       	<groupId>org.springframework</groupId>
       	<artifactId>spring-context</artifactId>
       	<version>5.2.7.RELEASE</version>
       </dependency>
   </dependencies>
   ```

3. **必须进行的操作**：在项目文件处，右键，选择**Maven->Reload project**

   可以解决大部分的问题

   <img src="\Spring.assets\image-20200623190141946.png" alt="image-20200623190141946" style="zoom:80%;" />

4. 在`src/main/java`目录下创建存放类的文件夹

5. 在目标`.java`文件的类前面加入`@Component`，用于说明未来该对象无需用户手动`new`创建

   ```java
   //自动生成，无需自已写
   import org.springframework.stereotype.Component;
   
   @Component
   ```

6. 在主函数的`.java`文件的类前面加入`@ComponentScan`

   ```java
   //自动生成，无需自已写
   import org.springframework.context.annotation.ComponentScan;
   
   @ComponentScan
   ```

7. 主函数中Spring容器初始化(参数：主函数所在的类对象)

   ```java
   ApplicationContext context = new AnnotationConfigApplicationContext(ApplicationSpring.class);
   ```

8. 从容器中获取类对象，函数：`context.getBean()`

   ```java
   MessagePrinter printer = context.getBean(MessagePrinter.class);
   ```

9. 自动调用类中的设置函数，在设置函数前面加入`@Autowired`：

   ```java
   @Autowired
   public void setService(MessageService service) {
   	this.service = service;
   }
   ```

   意味着无需再从容器中获取某一个类对象，因为Spring自动地帮我们调用了类对象中的设置函



### XML配置文件

1. 在`resources`文件处，右键，选择**New->XML Configuration File->Spring Config**

   约定俗成的命名：`applicationContext.xml`

   <img src="\Spring.assets\image-20200623191600680.png" alt="image-20200623191600680" style="zoom:80%;" />

2. 建立bean对象

   ```java
   <!--
   	bean元素：描述当前的对象需要由spring容器管理
   	id属性：标识对象，未来在应用程序中可以根据id获取对象
   	class：被管理对象的类全名
   -->
   <bean id="service" class="hello.MessageService"></bean>
   ```

3. 设置类对象之间的依赖关系，bean内设置

   ```java
   //MessagePrinter中有一个成员变量MessageService
   //name：命名与MessagePrinter中的类成员变量MessageService同名
   //ref：类成员变量设置为指定的bean对象
   <bean id="printer" class="hello.MessagePrinter">
   	<property name="service" ref="service"></property>
   </bean>
   ```

4. 在主函数中，初始化Spring容器（参数为XML配置文件）

   ```java
   ApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");
   ```

5. 查看项目的依赖关系：

   <img src="\Spring.assets\image-20200627155945132.png" alt="image-20200627155945132" style="zoom:80%;" />

   效果呈现：

   <img src="\Spring.assets\image-20200627160010556.png" alt="image-20200627160010556" style="zoom:80%;" />

6. [Spring  Framework]: https://spring.io/projects/spring-framework#learn

7. **注意**：Spring 5框架中，log4j已经被删除了，在这次教程中，log4j将基于4.3.13版本进行讲解

   [Spring Framework 4.3.13.RELEASE]: https://docs.spring.io/spring/docs/4.3.13.RELEASE/spring-framework-reference/htmlsingle/

8. 在`resources`模块中创建文件：`log4j.properties`，导入相应的内容（参考文档）

   ```java
   log4j.rootCategory=INFO, stdout
   
   log4j.appender.stdout=org.apache.log4j.ConsoleAppender
   log4j.appender.stdout.layout=org.apache.log4j.PatternLayout
   log4j.appender.stdout.layout.ConversionPattern=%d{ABSOLUTE} %5p %t %c{2}:%L - %m%n
   
   log4j.category.org.springframework.beans.factory=DEBUG
   ```



### 自动装载

9. 组件扫描

   ```java
   @Component //表示这个类需在应用程序中被创建
   @Autowired //自动发现应用程序中创建的类
   ```

2. 自动装配

   ```java
   @Autowired //自动满足bean之间的依赖
   ```

3. 定义配置类

   ```java
   @Configuration //表示当前类是一个配置类
   ```

   用法实例

   ```java
   //AppConfig.java
   @Configuration
   @ComponentScan
   public class AppConfig {
   }
   
   //App.java
   public class App {
   
       public static void main(String[] args) {
   
           ApplicationContext context = new AnnotationConfigApplicationContext(AppConfig.class);
           CDPlayer player = context.getBean(CDPlayer.class);
           player.play();
   
       }
   }
   ```



### junit4单元测试

1. 引入Spring单元测试模块

   ```java
   maven //junit、spring-test
   @RunWith(SpringJUnit4ClassRunner.class)
   ```

2. 加载配置类

   ```java
   @ContextConfiguration(classes = AppConfig.class)
   ```

3. 用法实列

   <img src="\Spring.assets\image-20200627173924874.png" alt="image-20200627173924874" style="zoom:80%;" />



### 面向接口的实现方式

1. 使用接口时，`@Component`必须放在接口实现类当中；在调用接口（接口实现类）时，通常声明的是接口类型的成员变量

2. 当接口被多个类实现时，`@Component+@Autowired`会报错，报错原因：出现自动装配的歧义性`（NoUniqueBeanDefinitionException）`

   <img src="\Spring.assets\image-20200627213731681.png" alt="image-20200627213731681" style="zoom:80%;" />

3. 自动装配的歧义性的解决方案

   - 声明接口类型的成员变量——>声明接口实现类的类型的成员变量（不推荐）

   - 在首选的接口<font color='red'>实现类</font>当中插入`@Primary`（首选bean<font color='red'>唯一</font>）

   - 设置限定符`@Qualifier("XXXXX")`，在声明和装配的时候均需要标明限定符`@Qualifier`

     ```java
     //UserServiceFestival.java
     @Component
     @Qualifier("festival")
     public class UserServiceFestival implements UserService {}
     
     //UserServiceTest.java
     @Autowired
     @Qualifier("festival")
     private UserService userService;
     ```

   - 在接口实现类的注解`@Component`中添加该类的`id`，用法：`@Component("XXXXX")`

     在装配的时候使用`@Qualifier`

     ```java
     //UserServiceFestival.java
     @Component("festival")
     public class UserServiceFestival implements UserService {}
     
     //UserServiceTest.java
     @Autowired
     @Qualifier("festival")
     private UserService userService;
     ```

     注解`@Autowired+@Qualifier`：首先查找接口实现类当中限定符（注解`@Qualifier`）相同的类，若找不到，则查找类`id`（注解`@Component`）与限定符相同的类

   - Spring会为未显示设置类`id`（注解`@Component("XXXXX")`）的类提供一个默认的类`id`，默认的类`id`为首字母小写的类名

     例如：

     ```java
     public class UserServiceNormal implements UserService {}
     //默认类id：
     userServiceNormal
     ```

   - java标准的解决方案`@Resource`，属于`javax`包

     ```java
     @Resource(name = "userServiceFestival")
     ==(等同于)
     @Autowired
     @Qualifier("userServiceFestival")
     ```



### 处理分层架构

1. 实例：`\spring4wiring_interface`

2. 分层架构：`web->service->dao`，层次之间递归调用下层函数

3. 各层次中注解`@Component`的替代（需要删除原注解`@Component`）

   ```java
   //web
   @Controller
   //service
   @Service
   //dao
   @Repository
   ```

   <img src="\Spring.assets\image-20200627224204910.png" alt="image-20200627224204910" style="zoom:80%;" />

4. 注解`@ComponentScan`可以添加`value`值，指定组件扫描的包或类

   ```java
   @ComponentScan("com.kszhub.demo")
   //or
   @ComponentScan(basePackages = {"com.kszhub.demo"})
   //or
   @ComponentScan(basePackages = {"com.kszhub.demo.web", "com.kszhub.demo.service", "com.kszhub.demo.dao"})
   //or
   @ComponentScan(basePackageClasses = {UserController.class, UserService.class, UserDao.class})
   ```

5. 利用`xml`配置文件进行组件扫描，`xml`配置文件放置于`resources`中，并命名为`applicationContext.xml`

   ```java
   //applicationContext.xml
   <context:component-scan base-package="com.kszhub.demo" />
   //or
   <context:component-scan base-package="com.kszhub.demo.web" />
   <context:component-scan base-package="com.kszhub.demo.service" />
   <context:component-scan base-package="com.kszhub.demo.dao" />
   
   //UserControllerTest.java
   @ContextConfiguration("classpath:applicationContext.xml")
   //classpath:指定resources目录下的xml配置文档
   ```



### 显示装配

#### Java装配

1. 配置类`JavaConfig`中显示的创建`Bean`（使用注解`@Bean`，且需要返回指定类型的类对象），其他操作和之前一样（可以`@Autowired`）

   ```java
   //AppConfig.java
   @Configuration
   public class AppConfig {
   
       @Bean
       public UserDao userDaoNormal(){
           System.out.println("创建UserDao对象");
           return new UserDaoNormal();
       }
   }
   ```

2. 注解`@Bean`：Spring会拦截所有的函数调用请求，若上下文中已经创建了目标类对象，则返回已创建好的；反之，则调用函数创建目标类对象并返回该对象——<font color='red'>单例</font>

   ```java
   //AppConfig.java
   @Configuration
   public class AppConfig {
   
       @Bean
       public UserDao userDaoNormal(){
           System.out.println("创建UserDao对象");
           return new UserDaoNormal();
       }
   
       @Bean
       public UserService userServiceNormal(){
           System.out.println("创建UserService对象");
           UserDao userDao = userDaoNormal();
           return new UserServiceNormal(userDao);
       }
   }
   //输出
   创建UserDao对象
   创建UserService对象
   ```

3. 上述代码中，`UserServiceNormal`在创建过程中使用了**有参构造函数**（**依赖注入**）

   用意：通过构造函数依赖注入（`UserServiceNormal`依赖注入`UserDaoNormal`）

   ```java
   //UserServiceNormal
   public class UserServiceNormal implements UserService {
   
       private UserDao userDao;
   
       public UserServiceNormal() {
           super();
       }
   
       public UserServiceNormal(UserDao userDao) {
           this.userDao = userDao;
       }
   
       @Override
       public void add() {
           userDao.add();
       }
   }
   ```

4. 更优雅的依赖注入：在`@Bean`注解的函数方法中使用参数

   Spring在调用`@Bean`方法时，会根据方法的参数，查找已经创建好的类对象，若找到已经创建好的`UserDao`对象，则会将该类对象赋值给当前`@Bean`方法的形参，用于依赖的注入

   ```java
   @Bean
   public UserService userServiceNormal(UserDao userDao){
       System.out.println("创建UserService对象");
       return new UserServiceNormal(userDao);
   }
   ```

5. 通过`setter`方法依赖注入——>可以用**任意方法**进行依赖的注入

   ```java
   //UserServiceNormal.java
   public void setUserDao(UserDao userDao) {
   	this.userDao = userDao;
   }
   
   //AppConfig.java
   @Bean
   public UserService userServiceNormal(UserDao userDao){
       System.out.println("创建UserService对象");
       UserServiceNormal userService = new UserServiceNormal();
       userService.setUserDao(userDao);
       return userService;
   }
   ```

6. `JavaConfig`装配的歧义性

   <img src="\Spring.assets\image-20200629114006136.png" alt="image-20200629114006136" style="zoom:80%;" />

7. 处理`JavaConfig`装配的歧义性==处理自动装配的歧义性

   - `@Primary`（但是所有的`@Bean`都会被Spring创建）

     ```java
     @Bean
     @Primary
     public UserDao userDaoNormal(){
         System.out.println("创建UserDaoNormal对象");
         return new UserDaoNormal();
     }
     ```

   - `@Qualifier`

     ```java
     @Bean
     @Qualifier("normal")
     public UserDao userDaoNormal(){
     }
     
     @Bean
     @Qualifier("cache")
     public UserDao userDaoCache(){
     }
     
     @Bean
     public UserService userServiceNormal(@Qualifier("normal") UserDao userDao){
     }
     ```

   - `@Bean`中设置id

     ```java
     @Bean("normal")
     public UserDao userDaoNormal(){
     }
     
     @Bean("cache")
     public UserDao userDaoCache(){
     }
     
     @Bean
     public UserService userServiceNormal(@Qualifier("normal") UserDao userDao){
     }
     ```

   - 默认id：`@Bean`的方法名

     ```java
     @Bean
     public UserService userServiceNormal(@Qualifier("userDaoNormal") UserDao userDao){
     }
     ```



#### xml装配

1. 在`xml`配置文件中（默认为`applicationContext.xml`），创建一个`bean`对象（默认为单例模式）

   当`bean`对象未设置指定的`id`或者`name`时，默认的`name`为`com.kszhub.demo.soundsystem.CompactDisc#0`（classname + `#0`）

   ```java
   <bean class="com.kszhub.demo.soundsystem.CompactDisc" />
   ```

2. `bean`歧义性

   ```java
   <bean class="com.kszhub.demo.soundsystem.CompactDisc" />
   <bean class="com.kszhub.demo.soundsystem.CompactDisc" />
   ```

   <img src="\Spring.assets\image-20200629143328164.png" alt="image-20200629143328164" style="zoom:80%;" />

3. 歧义性解决

   - 使用默认的`name`（classname + `#` + `[0,1,2,3,4......]`）

     注意：需要强制转换为目标类的类型（默认获得的是`Object`类型），例如`(CompactDisc)`

   ```java
   CompactDisc cd = (CompactDisc)context.getBean("com.kszhub.demo.soundsystem.CompactDisc#0");
   ```

   - 使用指定的`id`或`name`（同样需要类型的强制转换）

   ```java
   //applicationContext.xml
   <bean id="compactDisc1" class="com.kszhub.demo.soundsystem.CompactDisc" />
   <bean id="compactDisc2" class="com.kszhub.demo.soundsystem.CompactDisc" />
   
   //ApplicationSpring.java
   CompactDisc cd = (CompactDisc)context.getBean("compactDisc1");
   ```

4. 利用注解`@Autowired`自动装配`CompactDisc`类对象时，若存在多个`bean`类对象（`id`或`name`不相同的情况下），且想要分开赋值，则需要将成员变量名设置成目标`bean`对象相同的`id`或`name`

   ```java
   //applicationContext.xml
   <bean id="compactDisc1" class="com.kszhub.demo.soundsystem.CompactDisc" />
   <bean id="compactDisc2" class="com.kszhub.demo.soundsystem.CompactDisc" />
   
   //AppTest.java
   @Autowired
   private CompactDisc compactDisc1;
   
   @Autowired
   private CompactDisc compactDisc2;
   
   @Test
   public void test01(){
       compactDisc1.play();
       compactDisc2.play();
   }
   ```

5. 使用注解`@Autowired + @Qualifier("XXX")`同样可以指定自动装配时装配的`bean`对象

   且`compactDisc2`和`compactDisc3`指向的是同一个`bean`类对象（**单例模式**）

   ```java
   @Autowired
   @Qualifier("compactDisc2")
   private CompactDisc compactDisc3;
   
   //输出
   CompactDisc构造函数......com.kszhub.demo.soundsystem.CompactDisc@81d9a72
   CompactDisc构造函数......com.kszhub.demo.soundsystem.CompactDisc@1b410b60
   //compactDisc1
   播放CD音乐......com.kszhub.demo.soundsystem.CompactDisc@81d9a72
   //compactDisc2
   播放CD音乐......com.kszhub.demo.soundsystem.CompactDisc@1b410b60
   //compactDisc3
   播放CD音乐......com.kszhub.demo.soundsystem.CompactDisc@1b410b60
   ```

6. `id`和`name`作用类似，区别`name`可以取别名（分割符：`' ' or ',' or ';'` ）

   ```java
   <bean id="compactDisc1" class="com.kszhub.demo.soundsystem.CompactDisc" />
   <bean name="compactDisc2" class="com.kszhub.demo.soundsystem.CompactDisc" />
   
   //取别名
   <bean name="compactDisc1 compactDisc11" class="com.kszhub.demo.soundsystem.CompactDisc" />
   ```

7. 通过构造函数注入

   - `<constructor-arg>`元素

   ```java
   //CDPlayer.java
   private CompactDisc cd;
   
   public CDPlayer() {
       super();
       System.out.println("CDPlayer的构造函数" + this.toString());
   }
   
   public CDPlayer(CompactDisc cd) {
       this.cd = cd;
       System.out.println("CDPlayer的有参构造函数" + this.toString());
   }
   
   //applicationContext.xml
   <bean name="compactDisc1 compactDisc11" class="com.kszhub.demo.soundsystem.CompactDisc" />
   <bean id="cdPlayer1" class="com.kszhub.demo.soundsystem.CDPlayer">
   	<constructor-arg ref="compactDisc1" />
   </bean>
   ```

   - `c-`名称空间

     用法1：`c:XX-ref=“XXX”`

     - `c:`：表示`bean`的属性
     - `xx`：表示目标类中构造函数的参数名称
     - `-ref`：表示此处使用的是另一个`bean`对象的引用
     - `="XXX"`：表示引用的`bean`对象

     用法2：`c:_x-ref=“XXX”`

     - `_x`：表示目标类中构造函数的参数下标（从0开始，例如`c:_0-ref=“XXX”`）

   ```java
   //CDPlayer.java
   private CompactDisc cd;
   
   public CDPlayer() {
       super();
       System.out.println("CDPlayer的构造函数" + this.toString());
   }
   
   public CDPlayer(CompactDisc cd) {
       this.cd = cd;
       System.out.println("CDPlayer的有参构造函数" + this.toString());
   }
   
   //applicationContext.xml
   //首先声明c-命名空间
   <bean xmlns:c="http://www.springframework.org/schema/c">
   
   //c-命名空间的使用
   <bean id="cdPlayer2" class="com.kszhub.demo.soundsystem.CDPlayer" c:cd-ref="compactDisc2"></bean>
   <bean id="cdPlayer3" class="com.kszhub.demo.soundsystem.CDPlayer" c:_0-ref="compactDisc2"></bean>
   ```

8. `constructor-arg`注入简单类型

   `<constructor-arg value="XXX">`的注入顺序：按照构造函数中参数声明的顺序进行注入的

   或者，使用`name=""`属性来指定注入的参数名字——**用的最多**

   或者，使用`index=""`属性来指定注入的参数下标（从0开始，例如`index="0"`）

   或者，使用`type=""`属性来指定注入的参数类型（例如字符串类型`type="java.lang.String"`）

   ```java
   //CompactDisc.java
   public class CompactDisc {
   
       private String title;
       private String artist;
   
       public CompactDisc() {
           super();
       }
   
       public CompactDisc(String title, String artist) {
           this.title = title;
           this.artist = artist;
       }
   }
   
   //applicationContext.xml
   <bean name="compactDisc1 compactDisc11" class="com.kszhub.demo.soundsystem.CompactDisc">
   	<constructor-arg value="I Do" />
   	<constructor-arg value="JJ" />
   </bean>
   //or
   <bean name="compactDisc1 compactDisc11" class="com.kszhub.demo.soundsystem.CompactDisc">
   	<constructor-arg name="title" value="I Do" />
   	<constructor-arg name="artist" value="JJ" />
   </bean>
   //or
   <bean name="compactDisc1 compactDisc11" class="com.kszhub.demo.soundsystem.CompactDisc">
   	<constructor-arg index="0" value="I Do" />
   	<constructor-arg index="1" value="JJ" />
   </bean>
   //or
   <bean name="compactDisc1 compactDisc11" class="com.kszhub.demo.soundsystem.CompactDisc">
   	<constructor-arg type="java.lang.String" value="I Do" />
   	<constructor-arg type="java.lang.String" value="JJ" />
   </bean>
   ```

9. `c-`命名空间注入简单类型

   用法1：`c:xxx="XXX"`

   - `c:`：表示`bean`的属性
   - `xxx`：表示构造函数中参数的名字
   - `="XXX"`：表示注入的内容（例如：`c:xxx="JJ"`）

   用法2：`c:_x="XXX"`

   - `_x`：表示构造函数中参数的下标（从0开始，例如`c:_0=“JJ”`）

   ```java
   <bean name="compactDisc2" class="com.kszhub.demo.soundsystem.CompactDisc" c:title="周杰伦的床边故事" c:artist="周杰伦"/>
   //or
   <bean name="compactDisc2" class="com.kszhub.demo.soundsystem.CompactDisc" c:_0="周杰伦的床边故事" c:_1="周杰伦"/>
   ```

10. 注入`List`类型

    `value`参数值注入

    ```java
    //CompactDisc.java
    private List<String> tracks;
    public CompactDisc(List<String> tracks) {
        this.tracks = tracks;
    }
    public void play(){
        //for循环结构
        for (String track : this.tracks) {
            System.out.println("音乐：" + track);
        }
    }
    
    //applicationContext.xml
    <bean name="compactDisc1" class="com.kszhub.demo.soundsystem.CompactDisc">
        <constructor-arg name="tracks">
        	<list>
        		<value>I DO 1</value>
        		<value>I DO 2</value>
        		<value>I DO 3</value>
        	</list>
        </constructor-arg>
    </bean>
    ```

    `ref`类对象注入（`bean`对象）

    ```java
    //Music.java
    private String title;
    private Integer duration;
    
    public String getTitle() {
        return title;
    }
    
    public Integer getDuration() {
        return duration;
    }
    
    public Music(String title, Integer duration) {
        this.title = title;
        this.duration = duration;
    }
    
    //CompactDisc.java
    private List<Music> tracks;
    public CompactDisc(List<Music> tracks) {
        this.tracks = tracks;
    }
    public void play(){
        //for循环结构
        for (Music track : this.tracks) {
            System.out.println("音乐：" + track.getTitle() + "，时长：" + track.getDuration());
        }
    }
    
    //applicationContext.xml
    <bean id="music1" class="com.kszhub.demo.soundsystem.Music" >
        <constructor-arg value="I DO 1" />
        <constructor-arg value="270" />
    </bean>
    ......
    <bean name="compactDisc1" class="com.kszhub.demo.soundsystem.CompactDisc">
        <constructor-arg name="tracks">
        	<list>
        		<ref bean="music1" />
                <ref bean="music2" />
                <ref bean="music3" />
        	</list>
        </constructor-arg>
    </bean>
    ```

11. 注入`Set`类型

    ```java
    //CompactDisc.java
    private Set<Music> tracks;
    public CompactDisc(Set<Music> tracks) {
        this.tracks = tracks;
    }
    
    //applicationContext.xml
    <bean name="compactDisc1" class="com.kszhub.demo.soundsystem.CompactDisc">
        <constructor-arg name="tracks">
        	<set>
        		<ref bean="music1" />
    			......
        	</set>
        </constructor-arg>
    </bean>
    ```

12. `List`与`set`区别：

    - `set`：元素唯一
    - `List`：元素不唯一，元素有序

    **注意**：在Java Spring中基于`LinkedHashSet`，`Set`的元素是有序的（一般的`Set`是无序的）

13. 注入`map`类型

    ```java
    //CompactDisc.java
    private Map<String, Music> tracks;
    public CompactDisc(Map<String, Music> tracks) {
        this.tracks = tracks;
    }
    public void play(){
        for (String key : this.tracks.keySet()) {
            System.out.println("key：" + key);
            Music music = this.tracks.get(key);
            System.out.println("音乐：" + music.getTitle() + "，时长：" + music.getDuration());
        }
    }
    
    //applicationContext.xml
    <bean name="compactDisc1" class="com.kszhub.demo.soundsystem.CompactDisc">
        <constructor-arg name="tracks">
        	<map>
        		<!--<entry key="m1" value="" />-->
                <entry key="m1" value-ref="music1" />
                <entry key="m2" value-ref="music2" />
                <entry key="m3" value-ref="music3" />
        	</map>
        </constructor-arg>
    </bean>
    ```

14. 注入数组类型

    ```java
    //CompactDisc.java
    private Music[] tracks;
    public CompactDisc(Music[] tracks) {
        this.tracks = tracks;
    }
    public void play(){
        for (Music track : this.tracks) {
    		System.out.println("音乐：" + track.getTitle() + "，时长：" + track.getDuration());
    	}
    }
    
    //applicationContext.xml
    <bean name="compactDisc1" class="com.kszhub.demo.soundsystem.CompactDisc">
        <constructor-arg name="tracks">
        	<array>
                <!--<value>I DO</value>-->
                <ref bean="music1" />
                <ref bean="music2" />
                <ref bean="music3" />
            </array>
        </constructor-arg>
    </bean>
    ```

15. 属性注入`property`（基于类中的`setter`方法）

    `name="XXX"`：表示类中的参数属性名，等同于`setter`方法名中`set`后面接的名字

    例如：`setTitle`的属性名为`title`，`setTitle1`的属性名为`title1`

    ```java
    //Music.java
    private String title;
    private Integer duration;
    
    public void setTitle(String title) {
        this.title = title;
    }
    
    public void setDuration(Integer duration) {
        this.duration = duration;
    }
    
    //applicationContext-Property.xml
    <bean id="music1" class="com.kszhub.demo.soundsystem.Music">
        <property name="title" value="告白气球" />
        <property name="duration" value="215" />
    </bean>
    
    <bean id="music2" class="com.kszhub.demo.soundsystem.Music">
    	<property name="title" value="爱情废材" />
    	<property name="duration" value="305" />
    </bean>
    ```

16. 属性注入中注入数组和列表

    ```java
    //CompactDisc.java
    private String title;
    private String artist;
    //数组array
    private Music[] tracks;
    //列表list
    private List<Music> tracks;
    
    public void setTitle(String title) {
        this.title = title;
    }
    public void setArtist(String artist) {
        this.artist = artist;
    }
    //数组array
    public void setTracks(Music[] tracks) {
        this.tracks = tracks;
    }
    //列表list
    public void setTracks(List<Music> tracks) {
        this.tracks = tracks;
    }
    
    //applicationContext-Property.xml
    <bean id="compactDisc1" class="com.kszhub.demo.soundsystem.CompactDisc">
        <property name="title" value="周杰伦的床边故事" />
        <property name="artist" value="周杰伦" />
        <property name="tracks">
        	//数组array
    		<array>
    			<ref bean="music1" />
                <ref bean="music2" />
            </array>
        	//列表list
            <list>
    			<ref bean="music1" />
                <ref bean="music2" />
            </list>
        </property>
    </bean>
    ```

17. 属性注入中注入对象引用

    ```java
    //CDPlayer.java
    private CompactDisc cd;
    public void setCd(CompactDisc cd) {
    	this.cd = cd;
    }
    
    //applicationContext-Property.xml
    <bean id="cdPlayer1" class="com.kszhub.demo.soundsystem.CDPlayer">
    	<property name="cd" ref="compactDisc1" />
    </bean>
    ```

18. p名称空间注入

    - p名称空间的声明启用

    ```java
    //applicaitonContext.xml
    <beans xmlns:p="http://www.springframework.org/schema/p" />
    ```

    - p名称空间的使用

    ```java
    //applicaitonContext.xml
    <bean id="music2" class="com.kszhub.demo.soundsystem.Music" p:title="爱情废材" p:duration="305" />
    
    <bean id="compactDisc1" class="com.kszhub.demo.soundsystem.CompactDisc" p:title="周杰伦的床边故事" p:artist="周杰伦">
    	<property name="tracks">
    		<array>
    			<ref bean="music1" />
    			<ref bean="music2" />
    		</array>
    	</property>
    </bean>
    
    <bean id="cdPlayer1" class="com.kszhub.demo.soundsystem.CDPlayer" p:cd-ref="compactDisc1" />
    ```

19. util名称空间注入（可以解决列表、集合、数组的注入问题）

    - util名称空间的声明启用（一般不会手动加入，采用自动加入）

    ```java
    //applicaitonContext.xml
    <beans xmlns:util="http://www.springframework.org/schema/util" xsi:schemaLocation="http://www.springframework.org/schema/beans http://www.springframework.org/schema/beans/spring-beans.xsd http://www.springframework.org/schema/util https://www.springframework.org/schema/util/spring-util.xsd"/>
    ```

    - util名称空间的使用（）

      数组列表：`util:list`；集合`set`：`util:set`；映射`map`：`util:map`

    ```java
    //applicaitonContext.xml
    <util:list id="trackList">
    	<ref bean="music1" />
    	<ref bean="music2" />
    </util:list>
    <util:map><</util:map>
    <util:set><</util:set>
    <bean id="compactDisc1" class="com.kszhub.demo.soundsystem.CompactDisc"
    	p:title="周杰伦的床边故事"
    	p:artist="周杰伦" 
    	p:tracks-ref="trackList">
    </bean>
    ```



### 高级装配

1. Spring中`bean`为单例模式

   - 无论我们是否去主动获取或注入bean对象，spring上下文一加载就会创建bean对象
   - 无论获取多少次，拿到的都是用一个对象
   - 无论注入多少次，拿到的都是用一个对象

2. `bean`的作用域（第一个是`singleton`，默认作用域）

   用法：`<bean scope="XXX" />`

   ```java
   <bean id="notepad" class="com.kszhub.demo.Notepad" scope="prototype" />
   ```

   <img src="\Spring.assets\image-20200703144202378.png" alt="image-20200703144202378" style="zoom:80%;" />

3. 总结

   ```java
   /**
   * scope="singleton"
   * (1) 无论我们是否去主动获取或注入bean对象，spring上下文一加载就会创建bean对象
   * (2) 无论获取多少次，拿到的都是用一个对象
   * (3) 无论注入多少次，拿到的都是用一个对象
   *
   * scope="prototype"
   * (1) Spring上下文加载的时候不会创建bean对象
   * (2) 每次获取，都会拿到不同的bean对象
   * (3) 每次注入，都会拿到不同的bean对象
   */
   ```

4. 自动装配中定义`bean`的作用域（三种方式）

   ```java
   //Notepad2.java
   @Component
   //@Scope("prototype")
   //@Scope(scopeName = "prototype")
   @Scope(ConfigurableBeanFactory.SCOPE_PROTOTYPE)
   public class Notepad2 {
   }
   
   //applicationContext.xml
   <context:component-scan base-package="com.kszhub.demo" />
   ```

5. `javaconfig`装配中定义`bean`的作用域

   ```java
   //AppConfig.java
   @Configuration
   public class AppConfig {
   
       @Bean
       //和之前一样有三种方式，这里只列举其中一种方式
       @Scope(ConfigurableBeanFactory.SCOPE_PROTOTYPE)
       public Notepad3 notepad3(){
           return new Notepad3();
       }
   }
   ```

6. 延迟加载

   - `xml`配置：`lazy-ini`

     用法：`lazy-init="true"`（默认为`"false"`）

   ```java
   //applicationContext.xml
   <bean id="notepad" class="com.kszhub.demo.Notepad" scope="singleton" lazy-init="true" />
   ```

   - 自动装配：`@Lazy`（只适用于`singleton`模式）

   ```java
   //Notepad2.java
   @Component
   @Scope(ConfigurableBeanFactory.SCOPE_SINGLETON)
   @Lazy
   ```

   - `javaconfig`配置：`@Lazy`（只适用于`singleton`模式）

   ```java
   //AppConfig.java
   @Bean
   @Scope(ConfigurableBeanFactory.SCOPE_SINGLETON)
   @Lazy
   ```

7. 对象的初始化和销毁

   - `xml`配置方法

   ```java
   //Notepad.java
   public class Notepad {
       public void init(){
       }
       public void destroy(){
       }
   }
   
   //applicationContext.xml
   <bean id="notepad" class="com.kszhub.demo.Notepad" destroy-method="destroy" init-method="init" />
   
   //NotepadTest.java
   public class NotepadTest {
       @Test
       public void test01(){
           ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");
           Object notepad1 = (Notepad)context.getBean("notepad");
   
           context.destroy();
           //or
           context.close();
       }
   }
   ```

   - 自动装配方法（测试执行结束后自动销毁对象）

   ```java
   //Notepad2.java
   public class Notepad2 {
       @PostConstruct
       public void init(){
       }
       @PreDestroy
       public void destroy(){
       }
   }
   ```

   ```java
   //需要注意的是，@PostConstruct和@PreDestroy注解是Java EE的一部分。而在Java EE在Java 9中已经弃用（deprecated), 在Java 11的版本中被移除。
   //如果我们使用这两个注解，需要添加下面的依赖：
   <dependency>
       <groupId>javax.annotation</groupId>
       <artifactId>javax.annotation-api</artifactId>
       <version>1.3.2</version>
   </dependency>
   ```

   - `javaconfig`配置方法（用法与自动装配方法相同）`@PostConstruct + @PreDestroy`

8. 工厂方法创建`bean`对象

   - 静态工厂方法`factory-method="XXX"`

   ```java
   //PersonFactory.java
   public class PersonFactory {
   
       /**
        * 静态工厂方法
        * @return
        */
       public static Person createPerson1(){
           System.out.println("静态工厂创建Person......");
           return new Person();
       }
   }
   
   //applicationContext.xml
   <!--静态工厂-->
   <bean id="person1" class="com.kszhub.demo.PersonFactory" factory-method="createPerson1" />
   
   //PersonFactoryTest.java
   public class PersonFactoryTest {
   
       @Autowired
       private Person person1;
   
       @Test
       public void test01(){
           System.out.println(person1);
       }
   }
   ```

   - 实例工厂方法

   ```java
   //PersonFactory.java
   public class PersonFactory {
   
   	/**
        * 实例工厂方法
        * @return
        */
       public Person createPerson2(){
           System.out.println("实例工厂创建Person......");
           return new Person();
       }
   }
   
   //applicationContext.xml
   <!--实例工厂-->
   <bean id="personFactory" class="com.kszhub.demo.PersonFactory" />
   <bean id="person2" factory-bean="personFactory" factory-method="createPerson2" />
   
   //PersonFactoryTest.java
   public class PersonFactoryTest {
   
       @Autowired
       private Person person2;
   
       @Test
       public void test01(){
           System.out.println(person2);
       }
   }
   ```



### 快捷键

- **注解**

  - 行注释`Ctrl+/`

    首先你的光标要处于这一行，处于这行的哪个位置都可以,按`Ctrl+/`，就可以往行首添加`//`，将该行注释掉。

    再次按`Ctrl+/`，可以去掉该行注释。

  - 块注释`Ctrl+Shift+/`

    使用块注释需要先选中要注释的块。

    按`Ctrl+Shift+/`注释掉该块代码

    去除注释的时候，不需要全部选中这块代码，只用光标在注释内容上按`Ctrl+Shift+/`即可。

  - 方法或类注释

    在一个方法或类的开头，输入`/**`，然后按回车,自动根据参数和返回值生成注释模板，我们在这个模板上面编写即可。

- **为成员变量添加操作函数**

  `Alt+insert`

  例如，添加设置函数`public void setService(MessageService service) `：

  ```
  alt+insert
  then:
  Setter
  ```

- **类的构造函数**

  包含有参、无参构造，快捷键：`Ctrl+O`

  例如，创建无参构造：

  ```java
  Ctrl+O
  then:
  hashCode():int
  ```

- **自动处理报错的地方**

  ```java
  Alt+Enter
  ```




### 快捷创建

- 快捷创建成员变量`.var`

  ```java
  new CompactDisc().var
  ==
  CompactDisc compactDisc(可更改) = new CompactDisc();
  ```

- 快捷创建非空判断函数`.nn`

  ```java
  cd.nn
  ==
  if (cd != null) {
  }
  ```

- 创建`main`函数

  - `main`
  - `psvm`

  ```java
  main
  //or
  psvm
  ==
  public static void main(String[] args) {
  }
  ```

- 创建`System.out.println`输出`sout`

  ```java
  sout
  ==
  System.out.println();
  ```

- 创建`for`循环结构`.for`

  ```java
  this.tracks.keySet().for
  ==
  for (String XXX : this.tracks.keySet()) {
  }
  ```




### Spring容器优点

- 自动创建类对象

  条件：类对象文件中需要加入`@Component`，主函数文件中需要加入`@ComponentScan`，主函数中需要初始化Spring容器(参数：主函数所在的类对象)

  ```java
  ApplicationContext context = new AnnotationConfigApplicationContext(ApplicationSpring.class);
  ```

- 自动调用成员函数

  例如：自动调用类中的设置函数，用于连接两个不同的类对象

  条件：在设置函数的前面加入`@Autowired`

- XML配置文件

  操作：在`resources`文件处，右键，选择**New->XML Configuration File->Spring Config**

  约定俗成的命名：`applicationContext.xml`



### Spring知识点

- 装配bean的三种方式

  - 隐式的bean发现机制和自动装配
  - 在Java中进行显示的配置
  - 在SML中进行显示的配置

- `@Autowired`的使用位置

  - 用在构造函数上（在多个依赖的情况下较便捷）<font color='red'>（效率最高）</font>

    单成员变量的有参构造函数、多成员变量的多参数构造函数

    ```java
    @Autowired
    public CDPlayer(CompactDisc cd) {
        this.cd = cd;
        System.out.println("CDPlayer有参构造函数");
    }
    
    @Autowired
    public CDPlayer(CompactDisc cd, Power power) {
        this.cd = cd;
        this.power = power;
        System.out.println("CDPlayerd的多参数构造函数");
    }
    ```

  - 用在成员变量上<font color='red'>（效率低，但是最便捷）</font>

    成员变量声明时

    ```java
    @Autowired
    private CompactDisc cd;
    
    @Autowired
    private Power power;
    ```

  - 用在Setter方法上

    ```java
    @Autowired
    public void setCd(CompactDisc cd) {
        this.cd = cd;
        System.out.println("使用setCd......");
    }
    
    @Autowired
    public void setPower(Power power) {
        this.power = power;
        System.out.println("使用setPower......");
    }
    ```

  - 用在任意方法上

    ```java
    @Autowired
    public void prepare(CompactDisc cd, Power power){
        this.cd = cd;
        this.power = power;
        System.out.println("调用prepare......");
    }
    ```

- 关键字`required`，表示注入的对象是可选的

  ```java
  @Autowired(required = false)
  ```

- 注解总结

  <img src="\Spring.assets\image-20200629103619749.png" alt="image-20200629103619749" style="zoom:80%;" />




### 注意点

- `src/main`是项目的主要目录
- `main`目录下的`java、resources`两个目录都是源码目录的根目录
- `java`：存放的是所有的`.java`文件
- `resources`：存放的是配置文件
- 注解`@ComponentScan`组件扫描的基础是当前配置类所在的包以及其所有的子包
