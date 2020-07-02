<center><font size=50pt>Spring简介</font></center>

Spring是一个开源框架

帮助分离项目组件之间的依赖关系

主要目的是简化企业开发

#### 核心概念

- IoC
  - Inversion of Control
  - 控制反转
  - 对象的控制管理不再由用户管理，而是由Spring框架管理
- DI
  - Dependency Injection
  - 依赖注入
- AOP
  - Aspect Oriented Programming
  - 面向切面编程

#### Spring框架组成

<img src="\Spring简介.assets\image-20200623171545252.png" alt="image-20200623171545252" style="zoom:80%;" />

#### 模块分析

- Core Container

  - core

    IoC、DI

  - Beans

    Bean工厂（创建对象的工厂）中Bean的装配（对象的创建过程）

  - Context

    Spring容器（上下文）

  - SpEL

    表达式语言

- Data Access/Integration

  - Transactions：事务管理
  - JDBC：JDBC封装
  - ORM：支持数据集成框架
  - OXM：对象与XML之间的互相转换
  - JMS：生产者、消费者之间的联系

- Web

  - Web：面向Web应用程序
  - WebSocket：Socket开发
  - Servlet：**SpringMVC**的实现
  - Portlet：首页的窗口，内容集成

- Test：单元测试

- AOP：面向切面编程

- Aspects：提供AspectJ的支持

- Instrumentation：检测器

- Messaging：消息处理

#### Web程序的基本架构

<img src="\Spring简介.assets\image-20200627221727705.png" alt="image-20200627221727705" style="zoom:80%;" />