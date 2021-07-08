## spring 声明式事务（@Transactional）失效场景

spring事务失效的几种场景
- 使用不对
  - 私有方法添加事务，不生效
  - this.func() 添加事务，不生效
  - self.func() 添加事务，且注入self，生效
  - try-catch 捕获异常，不生效
- 异常处理不对
  - 捕获原异常，抛出一个新异常，导致异常无法传播，不生效
  - 抛出非 RuntimeException、Error的异常，不生效
- 事务传播，涉及多次数据库操作、跨服务
  - 默认是传播的，生效
  - try-catch 捕获sql2异常，静默回滚，生效
  - sql2 设置事务为新建模式，且sql2异常，则sql1不回滚，sql2回滚
- 事务嵌套
  - sql2 设置事务为嵌套模式，且在最后抛出异常，全部回滚、生效

测试接口
```
# 测试使用方法不对
http://localhost:8080/test1?username=user-error
http://localhost:8080/test2?username=user-error
http://localhost:8080/test3?username=user-error
http://localhost:8080/test4?username=user-error

# 测试异常处理不对
http://localhost:8080/test5?username=user-success
http://localhost:8080/test51?username=user-success
http://localhost:8080/test6?username=user-success
http://localhost:8080/test61?username=user-success

# 测试事务传播
http://localhost:8080/test7?username=order-error
http://localhost:8080/test8?username=order-error
http://localhost:8080/test9?username=order-error

# 测试事务嵌套
http://localhost:8080/test10?username=order-success
```
总结：
事务回滚的2个必要条件：
1. 异常传播，必须把异常传播出去
2. 异常处理，默认抛出 RuntimeException 或 Error 的时候才会回滚

相关代码：
```
// 异常传播
try {
    xxx
} catch (Throwable ex) {
    completeTransactionAfterThrowing(txInfo, ex);
    throw ex;
} finally {
    cleanupTransactionInfo(txInfo)
}

// 异常处理
@Override
public boolean rollbackOn(Throwable ex) {
    return (ex instanceof RuntimeException || ex instanceof Error);
}
```

