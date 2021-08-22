# mybatis-springboot
springboot整合mybatis

### 1、添加依赖

```xml
<dependencies>
    <dependency>
        <groupId>org.mybatis.spring.boot</groupId>
        <artifactId>mybatis-spring-boot-starter</artifactId>
        <version>2.2.0</version>
    </dependency>

    <dependency>
        <groupId>mysql</groupId>
        <artifactId>mysql-connector-java</artifactId>
        <scope>runtime</scope>
    </dependency>
    <dependency>
        <groupId>org.springframework.boot</groupId>
        <artifactId>spring-boot-configuration-processor</artifactId>
        <optional>true</optional>
    </dependency>
    <dependency>
        <groupId>org.projectlombok</groupId>
        <artifactId>lombok</artifactId>
        <optional>true</optional>
    </dependency>
    <dependency>
        <groupId>org.springframework.boot</groupId>
        <artifactId>spring-boot-starter-test</artifactId>
        <scope>test</scope>
    </dependency>
</dependencies>
```

### 2、编写配置文件

application.properties

```properties
# 数据源配置
spring.datasource.driver-class-name=com.mysql.cj.jdbc.Driver
spring.datasource.url=jdbc:mysql://192.168.214.128:3306/tutu?useSSL=false&useUnicode=true&characterEncoding=utf-8&zeroDateTimeBehavior=convertToNull&transformedBitIsBoolean=true&tinyInt1isBit=false&allowMultiQueries=true&serverTimezone=GMT%2B8&allowPublicKeyRetrieval=true
spring.datasource.name=zhongdongsheng
spring.datasource.password=123456

# mybatis相关配置
## 指定mybatis配置文件路径（所有mybatis相关配置基于mybatis配置文件实现）
# mybatis.config-location=classpath:mybatis-config.xml
# 不使用mybatis配置文件
## 指定mapper文件的路径
mybatis.mapper-locations=com/zds/mapper/*.xml
## 指定别名包的路径
mybatis.type-aliases-package=com.zds.pojo
## 其他配置
mybatis.configuration.map-underscore-to-camel-case=true
mybatis.configuration.log-impl=org.apache.ibatis.logging.stdout.StdOutImpl

## springboot默认使用的数据源连接池为HikariDataSource
#spring.datasource.hikari.connection-test-query
```

### 3、编写其他类与测试类

见代码

### 4、注意点

1、未整合springboot之前，需要手动指定mapper接口的包路径，即MapperScannerConfigurer类的注入，整合springboot后，可通过在每个mapper接口上使用@Mapper注解或在启动类上使用@MapperScan注解

2、springboot默认使用的数据源连接池为HikariDataSource

3、自动输入时，需要使用接口声明实例，而不是实现类。

