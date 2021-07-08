package org.example.practice.practicespring.transaction;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

import javax.annotation.Resource;

import org.example.practice.practicespring.db.mapper.OrderInfoMapper;
import org.example.practice.practicespring.db.mapper.UserMapper;
import org.example.practice.practicespring.db.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.interceptor.TransactionAspectSupport;

import lombok.extern.slf4j.Slf4j;

/**
 * 触发插入用户异常：userName=user-error
 *
 * 触发插入订单异常：userName=order-error
 */
@Service
@Slf4j
public class UserService {

    // @formatter:off
    @Resource
    private UserMapper userMapper;
    @Resource
    private OrderInfoMapper orderInfoMapper;
    @Resource
    private OrderService orderService;
    // 注入新的self
    @Autowired
    private UserService self;

    // 私有方法无法添加事务注解，添加了也不生效
    // @Transactional
    private void insertUserPrivate(String userName) {
        User user = new User();
        user.setUsername(userName);
        user.setPassword("123");
        user.setAddr("soho----123");
        log.info("user=" + user);
        userMapper.insert(user);
        if (user.getUsername().contains("user-error")) {
            log.info("模拟抛出异常");
            throw new RuntimeException("private 非法用户名，在线求回滚");
        }
    }

    // 公有方法添加事务注解
    @Transactional
    public void insertUserPublic(String userName) {
        insertUserPublicWithoutTransaction(userName);
    }

    public void insertUserPublicWithoutTransaction(String userName) {
        User user = new User();
        user.setUsername(userName);
        user.setPassword("123");
        user.setAddr("soho----123");
        log.info("user=" + user);
        userMapper.insert(user);
        if (user.getUsername().contains("user-error")) {
            log.info("模拟抛出异常");
            throw new RuntimeException("public 非法用户名，在线求回滚");
        }
    }

    /**
     * 测试1：私有方法添加事务
     * 执行结果：不回滚
     * 原因分析：spring 默认是通过动态代理的方式实现aop，对目标方法增强。private方法无法生成代理，不能进行动态增强事务处理。
     */
    public int insertUser1(String userName) {
        try {
            insertUserPrivate(userName);
        } catch (Exception e) {
            log.error("创建用户失败：{}", e.getMessage());
        }
        return userMapper.selectAll().size();
    }

    /**
     * 测试2：公共方法添加事务 this.调用
     * 执行结果：不回滚
     * 原因分析：必须通过代理过的类，从外部调用目标方法才能生效。
     *
     * 还是要说到spring事务的原理：spring通过aop对方法增强（代理），要想事务生效，必须调用增强过的方法。
     *
     * 这里this 是指本类，该类没有经过二次注入，该方法也没有被代理。
     */
    public int insertUser2(String userName) {
        try {
            // 即 this.insertUserPublic(userName);
            insertUserPublic(userName);
        } catch (Exception e) {
            log.error("创建用户失败：{}", e.getMessage());
        }
        return userMapper.selectAll().size();
    }

    /**
     * 测试3：重新注入自己。注入一个self，通过self实例调用有 @Transactional 注解的方法。
     * 执行结果：不回滚
     * 原因分析：self 是 spring 通过 cglib 方式增强过的类
     */
    public int insertUser3(String userName) {
        try {
            self.insertUserPublic(userName);
        } catch (Exception e) {
            log.error("创建用户失败：{}", e.getMessage());
        }
        return userMapper.selectAll().size();
    }

    /**
     * 测试4：在标记事务的方法中捕获异常
     * 执行结果：不回滚
     * 原因分析：异常被捕获，无法触发事务
     */
    @Transactional
    public int insertUser4(String userName) {
        try {
            insertUserPublic(userName);
        } catch (Exception e) {
            log.error("创建用户失败：{}", e.getMessage());
        }
        return userMapper.selectAll().size();
    }

    /**
     * --------------------------------分割线--------------------------------
     * 5-6 测试事务传播情况
     * spring 事务默认捕获：RuntimeException 和 Error 异常
     * 接口 TransactionAttribute.rollbackOn() 中定义了什么样的异常将会被回滚。
     */

    // 定义一个 IOException 异常
    private void otherTask() throws IOException {
        Files.readAllLines(Paths.get("file-that-not-exist"));
    }

    /**
     * 测试5：异常无法传播，userName!=user-error
     * 执行结果：不回滚
     * 分析原因：执行sql的异常没有传播过去，新的异常无法回滚
     */
    @Transactional
    public int insertUser5(String userName) {
        try {
            insertUserPublic(userName);
            // 抛出一个新的异常，而原来的异常无法传播过去
            throw new RuntimeException("user-error");
        } catch (Exception e) {
            log.error("创建用户失败：{}", e.getMessage());
        }
        return userMapper.selectAll().size();
    }

    /**
     * 测试5-1：优化，捕获异常，手动触发回滚，userName!=user-error
     * 执行结果：回滚
     * 分析原因：
     */
    @Transactional
    public int insertUser51(String userName) {
        try {
            insertUserPublic(userName);
            // 抛出一个新的异常，而原来的异常无法传播过去
            throw new RuntimeException("user-error");
        } catch (Exception e) {
            log.error("创建用户失败：{}", e.getMessage());
            // todo: do something
            // 异常捕获自己处理之后，手动触发事务回滚
            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
        }
        return userMapper.selectAll().size();
    }

    /**
     * 测试6：IOException 异常无法传播，userName!=user-error
     * 执行结果：不回滚
     * 分析原因：默认的事务只支持捕获 RuntimeException 和 Error 异常
     */
    @Transactional
    public int insertUser6(String userName) throws IOException {
        insertUserPrivate(userName);
        // 模拟异常
        otherTask();
        return userMapper.selectAll().size();
    }

    /**
     * 测试6-1：优化，是事务生效，userName!=user-error
     * 执行结果：回滚
     * 分析原因：设置spring 事务捕获所有异常
     */
    @Transactional(rollbackFor = Exception.class)
    public int insertUser61(String userName) throws IOException {
        insertUserPublic(userName);
        // 模拟异常
        otherTask();
        return userMapper.selectAll().size();
    }

    /**
     * --------------------------------分割线--------------------------------
     * 7-9 测试 sql1 + sql2，其中sql2 在serviceB中 的事务回滚情况
     */

    /**
     * 测试7：测试当事务中有多个SQL语句时，后面SQL抛异常前后SQL的回滚状况
     * 执行结果：sql1回滚、sql2回滚
     * 分析原因：因为抛出了运行时异常，导致主表回滚、子表回滚
     */
    @Transactional
    public int insertUser7(String userName) {
        // 执行 sql1=insert user
        insertUserPublicWithoutTransaction(userName);
        // 执行 sql2=insert order ；通过设置userName=order-error触发sql2抛出异常，测试回滚状况
        orderService.insertOrder1(userName);
        return userMapper.selectAll().size();
    }

    /**
     * 测试8：测试将sql2 的异常捕获，事务回滚状况
     * 执行结果：sql1回滚、sql2回滚
     * 分析原因：由于 主方法 和 子方法 在同一个事务中，所以子逻辑标记了事务回滚，导致 主逻辑也不能提交。
     *  UnexpectedRollbackException 静默回滚（方法本身没有异常，但是提交后 发现子方法已经把当前事务设置为回滚，导致无法提交）
     */
    @Transactional
    public int insertUser8(String userName) {
        // 执行 sql1=insert user
        insertUserPublicWithoutTransaction(userName);
        // 执行 sql2=insert order ；通过设置userName=order-error触发sql2抛出异常，测试回滚状况
        try {
            orderService.insertOrder1(userName);
        } catch (Exception e) {
            log.error("创建订单失败：{}", e.getMessage());
        }
        return userMapper.selectAll().size();
    }

    public int insertUser81(String userName) {
        self.batchInsertOrder(userName);
        return userMapper.selectAll().size();
    }

    @Transactional
    public void batchInsertOrder(String userName) {
        try {
            for (int i = 0; i < 8; i++) {
                if (i == 4) {
                    orderService.insertOrder1("order-error");
                } else {
                    orderService.insertOrder1(userName);
                }
            }
        } catch (Exception e) {
            log.info("===doto something");
            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
        }

    }

    /**
     * 测试9：测试 sql2 开启新事务时，事务回滚状况 userName=order-error
     * 执行结果：sql1不回滚、sql2回滚
     * 分析原因：sql2 开启了一个新的事务，与 sql1 隔离，无法传播给sql1
     */
    @Transactional
    public int insertUser9(String userName) {
        // 执行 sql1=insert user
        insertUserPublicWithoutTransaction(userName);
        // 执行 sql2=insert order ；通过设置userName=order-error触发sql2抛出异常，测试回滚状况
        try {
            orderService.insertOrder2(userName);
        } catch (Exception e) {
            log.error("创建订单失败：{}", e.getMessage());
        }
        return userMapper.selectAll().size();
    }

    /**
     * --------------------------------分割线--------------------------------
     * 10 测试嵌套事务回滚情况
     */

    /**
     * 测试10：测试 sql2 事务设置为嵌套模式时，事务回滚状况 userName!=order-error
     * 执行结果：sql回滚、sql2回滚
     * 分析原因：sql2
     */
    @Transactional
    public int insertUser10(String userName) {
        // 执行 sql1=insert user
        insertUserPublicWithoutTransaction(userName);
        // 执行 sql2=insert order 参数：userName!=order-error；
        // 这里设置为新建事务-不会回滚
        orderService.insertOrder2(userName + 222);
        // 这里设置为嵌套事务-可以回滚
        orderService.insertOrder3(userName + 333);
        /**
         * 若 insertOrder2 为新建事务，在这里抛异常，新建事务将不会被回滚
         * 若 insertOrder3 为嵌套事务，在这里抛异常，将会导致嵌套事务无法【提交】
         */
        throw new RuntimeException("模拟抛出异常");
    }
}
