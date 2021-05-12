package org.example.springstart.spring_factory.factory;

import java.io.InputStream;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

/**
 * 一个创建Bean对象的工厂
 *
 * Bean：在计算机英语中，有可重用组件的含义。
 * JavaBean：用java语言编写的可重用组件。
 *      javabean >  实体类
 *
 *   它就是创建我们的service和dao对象的。
 *
 *   第一个：需要一个配置文件来配置我们的service和dao
 *           配置的内容：唯一标识=全限定类名（key=value)
 *   第二个：通过读取配置文件中配置的内容，反射创建对象
 *
 *   我的配置文件可以是xml也可以是properties
 */
public class BeanFactory {

    // 定义一个 Properties 对象
    private static Properties prop;

    // 定义一个 Map，用于存放我们要创建的对象。我们称之为 "容器"
    private static Map<String,Object> beans;

    private static final String pathConfig = "bean.properties";

    // 单例对象
    // 使用静态代码块为Properties对象赋值
    static {
        try {
            prop = new Properties();

            // 获取 properties 文件的流对象
            InputStream in = BeanFactory.class.getClassLoader().getResourceAsStream(pathConfig);
            // 获取 properties 文件的流对象
            prop.load(in);

            // 实例化容器
            beans = new HashMap<String, Object>();
            // 取出配置文件中所有的Key
            Enumeration keys = prop.keys();

            // 遍历枚举
            while (keys.hasMoreElements()) {
                String key = keys.nextElement().toString();
                String beanPath = prop.getProperty(key);
                Object value = Class.forName(beanPath).newInstance();
                beans.put(key, value);
            }

        } catch (Exception e) {
            throw new ExceptionInInitializerError("初始化 properties 失败！");
        }
    }

    // 多例对象
    static {
        try {
            prop = new Properties();
            // 获取 properties 文件的流对象
            InputStream in = BeanFactory.class.getClassLoader().getResourceAsStream(pathConfig);
            // 获取 properties 文件的流对象
            prop.load(in);

        } catch (Exception e) {
            throw new ExceptionInInitializerError("初始化 properties 失败！");
        }
    }

    // 单例获取实例
    public static Object getBean(String beanName) {
        return beans.get(beanName);
    }

    // 多例获取实例
    public static Object getBeanMultition(String beanName) {
        Object bean = null;
        try {
            String beanPath = prop.getProperty(beanName);
            bean = Class.forName(beanPath).newInstance(); // 每次都会调用莫用构造函数创建对象
        } catch (Exception e) {
            e.printStackTrace();
        }
        return bean;
    }
}
