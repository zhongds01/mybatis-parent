# Mybatis-Plus

## 1、环境搭建

> 基于springboot整合,先创建工程，导入相关依赖

```xml
<dependencies>
    <dependency>
        <groupId>org.springframework.boot</groupId>
        <artifactId>spring-boot-starter</artifactId>
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
        <groupId>com.baomidou</groupId>
        <artifactId>mybatis-plus-boot-starter</artifactId>
        <version>3.4.3.1</version>
    </dependency>
    <dependency>
        <groupId>org.springframework.boot</groupId>
        <artifactId>spring-boot-starter-test</artifactId>
        <scope>test</scope>
    </dependency>
</dependencies>
```

## 2、配置数据源信息

> 在application.properties中，配置mysql数据源信息

```properties
# 数据源配置
spring.datasource.driver-class-name=com.mysql.cj.jdbc.Driver
spring.datasource.url=jdbc:mysql://192.168.214.128:3306/tutu?useSSL=false&useUnicode=true&characterEncoding=utf-8&zeroDateTimeBehavior=convertToNull&transformedBitIsBoolean=true&tinyInt1isBit=false&allowMultiQueries=true&serverTimezone=GMT%2B8&allowPublicKeyRetrieval=true
spring.datasource.name=zhongdongsheng
spring.datasource.password=123456
```

## 3、编写与数据库对应的实体类



## 4、编写mapper接口

> 自定义定义接口，继承mybatis-plus提供的BaseMapper接口。

```java
public interface CustomMapper extends BaseMapper<Custom> {
}
```

## 4、在springboot启动类上加上mapper接口包路径扫描，或在自定义配置类上加

```java
@Configuration
@MapperScan(value = "com.zds.mapper")
public class MybatisPlusConfig {
}
```

## 5、编写测试类

