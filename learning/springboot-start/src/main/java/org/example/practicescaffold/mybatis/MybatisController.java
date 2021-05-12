package org.example.practicescaffold.mybatis;

/**
 * @author wangyulin
 * @date 2020/6/11
 */
public class MybatisController {

    /**
     * Mybatis 知识点：
     *
     * 工厂模式（Factory 工厂模式）、构造者模式（Builder 模式）、代理模式，反射，自定义注解，
     * 注解的反射，xml 解析，数据库元数据，元数据的反射等。
     *
     * Mybatis 与 JDBC 编程比较
     * 1.数据库链接创建、释放频繁造成系统资源浪费从而影响系统性能，如果使用数据库链接池可解决此问题。
     * 解决：
     * 在 SqlMapConfig.xml 中配置数据链接池，使用连接池管理数据库链接。
     *
     * 2.Sql 语句写在代码中造成代码不易维护，实际应用 sql 变化的可能较大，sql 变动需要改变 java 代码。
     * 解决：
     * 将 Sql 语句配置在 XXXXmapper.xml 文件中与 java 代码分离。
     *
     * 3.向 sql 语句传参数麻烦，因为 sql 语句的 where 条件不一定，可能多也可能少，占位符需要和参数对应。
     * 解决：
     * Mybatis 自动将 java 对象映射至 sql 语句，通过 statement 中的 parameterType 定义输入参数的
     * 类型。
     *
     * 4.对结果集解析麻烦，sql 变化导致解析代码变化，且解析前需要遍历，如果能将数据库记录封装成 pojo 对
     * 象解析比较方便。
     * 解决：
     * Mybatis 自动将 sql 执行结果映射至 java 对象，通过 statement 中的 resultType 定义输出结果的
     * 类型。
     *
     * Mybatis 参数：
     *
     * parameterType:
     * 支持基本数据类型、引用数据类型（参考TypeAliasRegistry.class）、实体类类型（POJO类），也可以是实体类的包装类
     * 如果注册过类型别名的，可以直接使用别名。没有注册过的必须使用全限定类名（推荐）
     *
     * resultType:
     * 支持基本数据类型、和实体类类型
     *
     * resultMap:
     * 建立实体类和数据库表的对应关系
     *
     *
     */
}
