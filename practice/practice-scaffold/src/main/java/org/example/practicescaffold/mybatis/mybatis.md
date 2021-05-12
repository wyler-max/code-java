
## Mybatis 知识点：

工厂模式（Factory 工厂模式）、构造者模式（Builder 模式）、代理模式，反射，自定义注解，
注解的反射，xml 解析，数据库元数据，元数据的反射等。

### Mybatis 与 JDBC 编程比较
1. 数据库链接创建、释放频繁造成系统资源浪费从而影响系统性能，如果使用数据库链接池可解决此问题。
解决：
在 SqlMapConfig.xml 中配置数据链接池，使用连接池管理数据库链接。

2. Sql 语句写在代码中造成代码不易维护，实际应用 sql 变化的可能较大，sql 变动需要改变 java 代码。
解决：
将 Sql 语句配置在 XXXXmapper.xml 文件中与 java 代码分离。

3. 向 sql 语句传参数麻烦，因为 sql 语句的 where 条件不一定，可能多也可能少，占位符需要和参数对应。
解决：
Mybatis 自动将 java 对象映射至 sql 语句，通过 statement 中的 parameterType 定义输入参数的
类型。

4. 对结果集解析麻烦，sql 变化导致解析代码变化，且解析前需要遍历，如果能将数据库记录封装成 pojo 对
象解析比较方便。
解决：
Mybatis 自动将 sql 执行结果映射至 java 对象，通过 statement 中的 resultType 定义输出结果的
类型。

### Mybatis Mapper参数：

parameterType:  
支持基本数据类型、引用数据类型（参考TypeAliasRegistry.class）、实体类类型（POJO类），也可以是实体类的包装类
如果注册过类型别名的，可以直接使用别名。没有注册过的必须使用全限定类名（推荐）

resultType:  
支持基本数据类型、和实体类类型

resultMap:  
建立实体类和数据库表的对应关系

### Mybatis 开发习惯
使用 Mybatis 开发 Dao，通常有两个方法，即原始 Dao 开发方式和 Mapper 接口代理开发方式。
现在主流的开发方式是接口代理开发方式，这种方式总体上更加简便。

### Mybatis 配置文件 

SqlMapConfig.xml

```
-properties（属性）
--property
-settings（全局配置参数）
--setting
-typeAliases（类型别名）
--typeAliase
--package
-typeHandlers（类型处理器）
-objectFactory（对象工厂）
-plugins（插件）
-environments（环境集合属性对象）
--environment（环境子属性对象）
---transactionManager（事务管理）
---dataSource（数据源）
-mappers（映射器）
--mapper
--package
```

在使用 properties 标签配置时，我们可以采用两种方式指定属性配置
1. 在 properties 标签内配置 property 属性
2. 在 classpath 下定义 db.properties 文件
```properties
jdbc.driver=com.mysql.jdbc.Driver
jdbc.url=jdbc:mysql://localhost:3306/mybatis?characterEncoding=utf-8
jdbc.username=root
jdbc.password=123456
```

properties 标签配置

### Mybatis 连接池

数据源 DataSource 分为三类：
- UNPOOLED 不使用连接池的数据源
- POOLED 使用连接池的数据源
- JNDI 使用 JNDI 实现的数据源

我们一般用的是 POOLED 数据源（很多时候我们所说的数据源就是为了更好的管理数据
库连接，也就是我们所说的连接池技术）

MyBatis 是通过工厂模式来创建数据源 DataSource 的， MyBatis 定义了抽象的工厂接口
org.apache.ibatis.datasource.DataSourceFactory
通过其 getDataSource()方法返回数据源 DataSource。

连接池工作原理：

### Mybatis Mapper 一对多&多对一

Case：多个学生对应一个老师，一个老师对应多个学生  

多对一：
多个学生对应一个老师
使用 association 
```xml
<maper namespace="com.example.mybatis.domain.xx">
    <select id="getStudents" resultMap="studentMap" >
        select s.id sid, s.name sname , t.name tname
        from student s,teacher t
        where s.tid = t.id
    </select>
    <resultMap id="studentMap" type="Student">
        <id property="id" column="sid"/>
        <result property="name" column="sname"/>
        <!--关联对象property 关联对象在Student实体类中的属性-->
        <!--association关联属性  property属性名 javaType属性类型 column在多的一方的表中的列名-->
        <association property="teacher" colum="tid" javaType="Teacher">
            <id property="id" column="tid"/>
            <result property="name" column="tname"/>
        </association>
    </resultMap>
</maper>
```

一对多：
一个老师对应多个学生
使用 collection 
```xml
<mapper namespace="com.example.mybatis.domain.xx">
    <select id="getTeacher" resultMap="teacherMap">
        select s.id sid, s.name sname , t.name tname, t.id tid
        from student s,teacher t
        where s.tid = t.id and t.id=#{id}
    </select>

    <resultMap id="teacherMap" type="Teacher">
        <result  property="name" column="tname"/>
        <!--column是一对多的外键 , 写的是一的主键的列名-->
        <collection property="students" ofType="Student">
            <result property="id" column="sid" />
            <result property="name" column="sname" />
            <result property="tid" column="tid" />
        </collection>
    </resultMap>
</mapper>
```
association、collection 中使用select 数学即可实现延迟加载


### Mybatis Mapper 语法

在 resultMap 的result 中 property是数据库字段名，column是实体类名

### Mybatis 延迟加载

association、collection 具备延迟加载功能

### Mybatis 缓存

像大多数的持久化框架一样，Mybatis 也提供了缓存策略，通过缓存策略来减少数据库的查询次数，从而提高性能。
Mybatis 中缓存分为一级缓存，二级缓存。

#### 一级缓存
一级缓存是 SqlSession 级别的缓存，只要 SqlSession 没有 flush(clearCache) 或 close，它就存在

一级缓存是 SqlSession 范围的缓存，当调用 SqlSession 的修改，添加，删除，commit()，close()等方法时，就会清空一级缓存。


#### 二级缓存

二级缓存是 mapper 映射级别的缓存，多个 SqlSession 去操作同一个 Mapper 映射的 sql 语句，多个 SqlSession 可以共用二级缓存，二级缓存是跨 SqlSession 的。

首先开启 mybatis 的二级缓存。 

sqlSession1 去查询用户信息，查询到用户信息会将查询数据存储到二级缓存中。

如果 SqlSession3 去执行相同 mapper 映射下 sql，执行 commit 提交，将会清空该 mapper 映射下的二级缓存区域的数据。

sqlSession2 去查询与 sqlSession1 相同的用户信息，首先会去缓存中找是否存在数据，如果存在直接从缓存中取出数据。

**二级缓存的开启或关闭**
第一步：在 SqlMapConfig.xml 文件开启二级缓存
```xml
<settings>
<!-- 开启二级缓存的支持 --> 
<setting name="cacheEnabled" value="true"/>
</settings>
```
因为 cacheEnabled 的取值默认就为 true，所以这一步可以省略不配置。为 true 代表开启二级缓存；为false 代表不开启二级缓存  


第二步：配置相关的 Mapper 映射文件

```xml
<!-- cache 标签表示当前这个 mapper 映射将使用二级缓存，区分的标准就看 mapper 的 namespace 值 -->
<mapper namespace="com.example.springboot.db.IUserDao">
    <!-- 开启二级缓存的支持 -->
    <cache></cache>
</mapper>
```


第三步：配置 statement 上面的 useCache 属性
```xml
<!-- 根据 id 查询 --> 
<!-- 将 UserDao.xml 映射文件中的<select>标签中设置 useCache=”true”代表当前这个 statement 要使用二级缓存，如果不使用二级缓存可以设置为 false。 -->
<select id="findById" resultType="userReq" parameterType="int" useCache="true">
select * from userReq where id = #{uid}
</select>
```
注意：针对每次查询都需要最新的数据 sql，要设置成 useCache=false，禁用二级缓存


### Mybatis 注解开发

注解开发越来越流行，Mybatis 也可以使用注解开发方式，这样我们就可以减少编写 Mapper 映射文件了

mybatis 的常用注解说明  
```

@Insert:实现新增
@Update:实现更新
@Delete:实现删除
@Select:实现查询
@Result:实现结果集封装，                         代替了 <id>标签和<result>标签
@Results:可以与@Result 一起使用，封装多个结果集    代替了 <resultMap> 标签
属性介绍：
id 是否是主键字段
column 数据库的列名
property 需要装配的属性名
one 需要使用的@One 注解（@Result（one=@One）（）））
many 需要使用的@Many 注解（@Result（many=@many）（）））

@ResultMap:实现引用@Results 定义的封装
@One:实现一对一结果集封装                         代替了 <assocation> 标签，是多表查询的关键，在注解中用来指定子查询返回单一对象
使用格式：
@Result(column=" ",property="",one=@One(select=""))

@Many:实现一对多结果集封装                        代替了 <Collection> 标签，是多表查询的关键，在注解中用来指定子查询返回对象集合
使用格式：
@Result(property="",column="",many=@Many(select=""))

@SelectProvider: 实现动态 SQL 映射
@CacheNamespace:实现注解二级缓存的使用

```

**mybatis 基于注解的二级缓存**

1. 在 SqlMapConfig 中开启二级缓存支持
```xml
<!-- 配置二级缓存 --> 
<settings>
<!-- 开启二级缓存的支持 --> 
    <setting name="cacheEnabled" value="true"/>
</settings>
```

2. 在持久层接口中使用注解配置二级缓存
```
@CacheNamespace(blocking=true)//mybatis 基于注解方式实现配置二级缓存
public interface IUserDao {}
```

