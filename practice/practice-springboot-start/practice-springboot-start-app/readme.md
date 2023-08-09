1、创建环境总目录profiles，创建环境子目录dev/test/prod
2、配置pom.xml
2.1 配置环境目录

```xml

<profiles>
    <profile>
        <id>dev</id>
        <activation>
            <activeByDefault>true</activeByDefault>
        </activation>
        <properties>
            <activeProfile>dev</activeProfile>
            <resource.directory>src/main/profiles/dev</resource.directory>
        </properties>
    </profile>
    <profile>
        <id>test</id>
        <properties>
            <activeProfile>test</activeProfile>
            <resource.directory>src/main/profiles/test</resource.directory>
        </properties>
    </profile>
    <profile>
        <id>prod</id>
        <properties>
            <activeProfile>prod</activeProfile>
            <resource.directory>src/main/profiles/prod</resource.directory>
        </properties>
    </profile>
</profiles>
```

2.2 配置环境目录动态引用

```xml

<build>
    <resource>
        <directory>src/main/resources</directory>
        <!--开启过滤，用指定的参数替换directory下的文件中的参数 -->
        <filtering>true</filtering>
    </resource>
    <resources>
        <resource>
            <directory>src/main/profiles/${activeProfile}</directory>
        </resource>
    </resources>
</build>
```

3.指定启动环境
在application.yaml中

```yaml
spring:
  profiles:
    #  active: @activeProfile@
    active: dev
```