<center><font size=50pt>Spring问题解决</font></center>

- Spring依赖包无法导入，即`pom.xml`文件中`<dependency>`部分报错（找不到目标文件）

  **错误效果：**

  <img src="\Spring问题解决.assets\image-20200623194226717.png" alt="image-20200623194226717" style="zoom:80%;" />

  **解决方案：**

  必须进行的操作：在项目文件处，右键，选择**Maven->Reload project**

  可以解决大部分的问题

  <img src="\Spring问题解决.assets\image-20200623190141947.png" style="zoom:80%;" />

- 在`resources`中，无法创建Spring框架的`XML Config` 文件

  **解决方法：**

  必须进行的操作：在项目文件处，右键，选择**Maven->Reload project**

  可以解决大部分的问题

  **目标效果：**

  <img src="\Spring问题解决.assets\image-20200623191600681.png" style="zoom:80%;" />

- 编译Debug时报错，错误说明：`Error:java:不支持发行版本 5`

  **错误效果：**

  <img src="\Spring问题解决.assets\image-20200623194842553.png" alt="image-20200623194842553" style="zoom:80%;" />

  **解决方案：**

  1. 在IJ IDEA中点击`File -->Project Structure`，看一下`Project`和`Module`栏目中Java版本是否与本地一致：

     <img src="\Spring问题解决.assets\image-20200623195051309.png" alt="image-20200623195051309" style="zoom:80%;" />

     <img src="\Spring问题解决.assets\image-20200623195125462.png" alt="image-20200623195051309" style="zoom:80%;" />

  2. 点击`Settings-->Bulid, Execution,Deployment-->Java Compiler`，`Target bytecode version`设为本地Java版本。（可以在`Default Settings`中把`Project bytecode version `一劳永逸地配置成本地Java版本）

     <img src="\Spring问题解决.assets\image-20200623195258127.png" alt="image-20200623195258127" style="zoom:80%;" />

     <img src="\Spring问题解决.assets\image-20200623195333076.png" alt="image-20200623195333076" style="zoom:80%;" />

- 

