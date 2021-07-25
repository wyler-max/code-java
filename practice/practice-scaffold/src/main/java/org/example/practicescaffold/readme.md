
### http请求链路
request
    ->ResponseUtil.process
        ->AbstractRequest (commonParams, validParam)
        ->AbstractPagerRequest (pager)

response


### 访问Redis
Redis 有几种常见的客户端
1. 使用 spring-boot-data-redis
   1. Lettuce --默认客户端
   2. Jedis
2. 使用 Redisson

#### Lettuce
特点：
1. 线程安全，常见用于主备集群
2. 异步的
3. 支持集群，Sentinel，管道和编码器

配置参数 spring.redis.xxx
```
# Redis数据库索引（默认为0）
spring.redis.database=0
# Redis服务器地址
spring.redis.host=127.0.0.1
# Redis服务器连接端口
spring.redis.port=6379
# Redis服务器连接密码（默认为空）
spring.redis.password=
# 连接池最大连接数（使用负值表示没有限制）
spring.redis.pool.max-active=8
# 连接池最大阻塞等待时间（使用负值表示没有限制）
spring.redis.pool.max-wait=-1
# 连接池中的最大空闲连接
spring.redis.pool.max-idle=8
# 连接池中的最小空闲连接
spring.redis.pool.min-idle=0
# 连接超时时间（毫秒）
spring.redis.timeout=0  
```


#### 使用Jedis
特点：
1. 线程不安全，常见用于分片集群；
2. 同步的，配合线程池使用
3. 丰富的操作命令

配置依赖
```
<!-- 修改Spring Boot Data Redis默认客户端为Redis -->
<dependency>
    <groupId>org.springframework.boot</groupId>
    <artifactId>spring-boot-starter-data-redis</artifactId>
    <exclusions>
        <exclusion>
            <groupId>io.lettuce</groupId>
            <artifactId>lettuce-core</artifactId>
        </exclusion>
    </exclusions>
</dependency>

<!-- 引入Jedis客户端依赖, 如果线程池报错可降低版本至2.9.1 -->
<dependency>
    <groupId>redis.clients</groupId>
    <artifactId>jedis</artifactId>
    <version>3.3.0</version>
</dependency>
```

配置参数 spring.redis.xxx 和 spring.redis.jedis.xxx
```
```

功能：spring-data-redis针对jedis提供了如下功能
```
1. 连接池自动管理，提供了一个高度封装的“RedisTemplate”类

2. 针对jedis客户端中大量api进行了归类封装,将同一类型操作封装为operation接口

ValueOperations：简单K-V操作
SetOperations：set类型数据操作
ZSetOperations：zset类型数据操作
HashOperations：针对map类型的数据操作
ListOperations：针对list类型的数据操作

3. 提供了对key的“bound”(绑定)便捷化操作API，可以通过bound封装指定的key，然后进行一系列的操作而无须“显式”的再次指定Key，即BoundKeyOperations：

BoundValueOperations
BoundSetOperations
BoundListOperations
BoundSetOperations
BoundHashOperations

4. 将事务操作封装，有容器控制。

5. 针对数据的“序列化/反序列化”，提供了多种可选择策略(RedisSerializer)

默认：JdkSerializationRedisSerializer：POJO对象的存取场景，使用JDK本身序列化机制，将pojo类通过ObjectInputStream/ObjectOutputStream进行序列化操作，最终redis-server中将存储字节序列。是目前最常用的序列化策略。
推荐：StringRedisSerializer：Key或者value为字符串的场景，根据指定的charset对数据的字节序列编码成string，是“new String(bytes, charset)”和“string.getBytes(charset)”的直接封装。是最轻量级和高效的策略。
JacksonJsonRedisSerializer：jackson-json工具提供了javabean与json之间的转换能力，可以将pojo实例序列化成json格式存储在redis中，也可以将json格式的数据转换成pojo实例。因为jackson工具在序列化和反序列化时，需要明确指定Class类型，因此此策略封装起来稍微复杂。【需要jackson-mapper-asl工具支持】
OxmSerializer：提供了将javabean与xml之间的转换能力，目前可用的三方支持包括jaxb，apache-xmlbeans；redis存储的数据将是xml工具。不过使用此策略，编程将会有些难度，而且效率最低；不建议使用。【需要spring-oxm模块的支持】
```

#### 使用redisson
特点：
1. 线程安全，常见用于主备集群
2. 异步的

配置参数
```
```

#### Redis集群
1. Redis集群分为 Sentinel哨兵机制集群(一主多备) 和 3.0之后使用JedisCluster集群(n个一主多备)
2. 使用 ShardedJedisPool 实现客户端分片访问（多主）
