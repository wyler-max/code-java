
## Spring概述

### Spring 是什么
Spring 是分层的 Java SE/EE 应用 full-stack 轻量级开源框架，以 IoC（Inverse Of Control：
反转控制）和 AOP（Aspect Oriented Programming：面向切面编程）为内核，提供了展现层 Spring 
MVC 和持久层 Spring JDBC 以及业务层事务管理等众多的企业级应用技术，还能整合开源世界众多
著名的第三方框架和类库，逐渐成为使用最多的 Java EE 企业应用开源框架

### Spring 优点

- 方便解耦，简化开发
- AOP 编程的支持
- 声明式事务的支持
- 方便程序的测试
- 方便集成各种优秀框架
- 降低 JavaEE API 的使用难度
- Java 源码是经典学习范例

## IOC 概述

### 什么是程序耦合
耦合性(Coupling)，也叫耦合度，是对模块间关联程度的度量。划分模块的一个准则就是高内聚低耦合。

耦合分类：（从高到低）
- 内容耦合
- 公共耦合
- 外部耦合
- 控制耦合
- 标记耦合
- 数据耦合
- 非直接耦合

总结：
耦合是影响软件复杂程度和设计质量的一个重要因素，在设计上我们应采用以下原则：如果模块间必须
存在耦合，就尽量使用数据耦合，少用控制耦合，限制公共耦合的范围，尽量避免使用内容耦合

### 解决耦合

当是我们使用 jdbc 时，是通过反射来注册驱动的，代码如下：
```
Class.forName("com.mysql.jdbc.Driver");
```

此时的好处是，我们的类中不再依赖具体的驱动类，此时就算删除 mysql 的驱动 jar 包，依然可以编译（运
行就不要想了，没有驱动不可能运行成功的）。
另外可以使用配置文件代替全限定类名。

### 工厂模式解耦

在实际开发中我们可以把三层的对象都使用配置文件配置起来，当启动服务器应用加载的时候，让一个类中的
方法通过读取配置文件，把这些对象创建出来并存起来。在接下来的使用的时候，直接拿过来用就好了。
那么，这个读取配置文件，创建和获取三层对象的类就是工厂。

IOC：把创建对象的权利交给框架。它包括依赖注入DI，和依赖查找DL。目的是为了削减程序的耦合。


## 使用IOC解决程序耦合

Case：解决账户的业务层和持久层的依赖关系。

在开始 spring 的配置之前，我们要先准备
一下环境。由于我们是使用 spring 解决依赖关系，并不是真正的要做增删改查操作，所以此时我们没必要写实体
类。并且我们在此处使用的是 java 工程，不是 java web 工程。

**BeanFactory 和 ApplicationContext 的区别**
1. 地位不一样  
BeanFactory 才是 Spring 容器中的顶层接口。
ApplicationContext 是它的子接口。

2. 创建对象的时间点不一样  
ApplicationContext：只要一读取配置文件，默认情况下就会创建对象。
BeanFactory：什么使用什么时候创建对象。

**ApplicationContext 接口的实现类**

- ClassPathXmlApplicationContext：
它是从类的根路径下加载配置文件 推荐使用这种

- FileSystemXmlApplicationContext：
它是从磁盘路径上加载配置文件，配置文件可以在磁盘的任意位置

- AnnotationConfigApplicationContext:
当我们使用注解配置容器对象时，需要使用此类来创建 spring 容器。它用来读取注解

**bean 标签**

```
作用：
用于配置对象让 spring 来创建的。
默认情况下它调用的是类中的无参构造函数。如果没有无参构造函数则不能创建成功。

属性：
id：给对象在容器中提供一个唯一标识。用于获取对象。
class：指定类的全限定类名。用于反射创建对象。默认情况下调用无参构造函数。
scope：指定对象的作用范围。
  * singleton :默认值，单例的.
  * prototype :多例的.
  * request :WEB 项目中,Spring 创建一个 Bean 的对象,将对象存入到 request 域中.
  * session :WEB 项目中,Spring 创建一个 Bean 的对象,将对象存入到 session 域中.
  * global session :WEB 项目中,应用在 Portlet 环境.如果没有 Portlet 环境那么globalSession 相当于 session.
init-method：指定类中的初始化方法名称。
destroy-method：指定类中销毁方法名称。
```

**bean 的作用范围和生命周期**
```
单例对象：scope="singleton"
  一个应用只有一个对象的实例。它的作用范围就是整个引用。
  生命周期：
    对象出生：当应用加载，创建容器时，对象就被创建了。
    对象活着：只要容器在，对象一直活着。
    对象死亡：当应用卸载，销毁容器时，对象就被销毁了

多例对象：scope="prototype"
  每次访问对象时，都会重新创建对象实例。
  生命周期：
    对象出生：当使用对象时，创建新的对象实例。
    对象活着：只要对象在使用中，就一直活着。
    对象死亡：当对象长时间不用时，被 java 的垃圾回收器回收了
```

**实例化 Bean 的三种方式**

```
第一种方式：使用默认无参构造函数
<!--
  在默认情况下：
  它会根据默认无参构造函数来创建类对象。如果 bean 中没有默认无参构造函数，将会创建失败。
--> 
<bean id="accountService" class="com.example.spring.iocbase.service.impl.IAccountServiceImpl"/>

第二种方式：spring 管理静态工厂-使用静态工厂的方法创建对象
/**
* 模拟一个静态工厂，创建业务层实现类
*/
public class StaticFactory {
  public static IAccountService createAccountService(){
  	return new AccountServiceImpl();
  } 
}
<!-- 此种方式是:
  使用 StaticFactory 类中的静态方法 createAccountService 创建对象，并存入 spring 容器
  id 属性：指定 bean 的 id，用于从容器中获取
  class 属性：指定静态工厂的全限定类名
  factory-method 属性：指定生产对象的静态方法
-->
<bean 
  id="accountService" 
  class="com.example.spring.spring_factory.StaticFactory"
  factory-method="createAccountService">
</bean>


第三种方式：spring 管理实例工厂-使用实例工厂的方法创建对象
/**
* 模拟一个实例工厂，创建业务层实现类
* 此工厂创建对象，必须先有工厂实例对象，再调用方法
*/
public class InstanceFactory {
  public IAccountService createAccountService(){
  	return new AccountServiceImpl();
  } 
}
<!-- 此种方式是：
  先把工厂的创建交给 spring 来管理。
  然后在使用工厂的 bean 来调用里面的方法
  factory-bean 属性：用于指定实例工厂 bean 的 id。
  factory-method 属性：用于指定实例工厂中创建对象的方法。
-->
<bean 
  id="instancFactory" 
  class="com.example.factory.InstanceFactory">
</bean> 
<bean 
  id="accountService" 
  factory-bean="instancFactory" 
  factory-method="createAccountService">
</bean>
```

**spring 的依赖注入**





**spring 目录**
1. jdbc
2. factory
3. bean
4. di
5. ioc
6. iocanno
7. ioccasexml
8. ioccasexanno
9. Iocwithoutxml






