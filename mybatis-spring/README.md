# mybatis-springboot
spring整合mybatis

### 1、添加依赖

```xml
<dependencies>
    <dependency>
        <groupId>junit</groupId>
        <artifactId>junit</artifactId>
        <version>4.12</version>
        <scope>test</scope>
    </dependency>
    <dependency>
        <groupId>org.mybatis</groupId>
        <artifactId>mybatis-spring</artifactId>
        <version>2.0.6</version>
    </dependency>

    <dependency>
        <groupId>org.springframework</groupId>
        <artifactId>spring-context</artifactId>
        <version>${spring.version}</version>
    </dependency>

    <dependency>
        <groupId>org.springframework</groupId>
        <artifactId>spring-jdbc</artifactId>
        <version>${spring.version}</version>
    </dependency>
    
    <!-- https://mvnrepository.com/artifact/org.apache.commons/commons-dbcp2 -->
    <dependency>
        <groupId>org.apache.commons</groupId>
        <artifactId>commons-dbcp2</artifactId>
        <version>2.9.0</version>
    </dependency>
    <!-- c3p0数据源连接池依赖 -->
    <dependency>
        <groupId>com.mchange</groupId>
        <artifactId>c3p0</artifactId>
        <version>0.9.5.5</version>
        <scope>compile</scope>
    </dependency>

    <dependency>
        <groupId>org.springframework</groupId>
        <artifactId>spring-test</artifactId>
        <version>${spring.version}</version>
        <scope>test</scope>
    </dependency>

</dependencies>
```

### 2、编写配置文件

application.xml

```xml
<?xml version="1.0" encoding="UTF-8"?>
<beans xmlns="http://www.springframework.org/schema/beans"
       xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
       xmlns:context="http://www.springframework.org/schema/context"
       xmlns:tx="http://www.springframework.org/schema/tx"
       xsi:schemaLocation="http://www.springframework.org/schema/beans http://www.springframework.org/schema/beans/spring-beans.xsd
                           http://www.springframework.org/schema/context http://www.springframework.org/schema/context/spring-context.xsd
                           http://www.springframework.org/schema/tx http://www.springframework.org/schema/tx/spring-tx.xsd">
    <!-- 引入外部资源文件 -->
    <context:property-placeholder location="classpath:jdbc.properties"/>
    <context:component-scan base-package="com.zds.service.impl"/>
    <!-- dbcp2数据源连接池配置 -->
    <bean id="dataSource" class="org.apache.commons.dbcp2.BasicDataSource">
        <property name="driverClassName" value="${jdbc.driver}"/>
        <property name="url" value="${jdbc.url}"/>
        <property name="username" value="${jdbc.username}"/>
        <property name="password" value="${jdbc.password}"/>
    </bean>
    <!-- c3p0数据源连接池配置 -->
    <!--<bean id="dataSource" class="com.mchange.v2.c3p0.ComboPooledDataSource">
        <property name="driverClass" value="${jdbc.driver}"/>
        <property name="jdbcUrl" value="${jdbc.url}"/>
        <property name="user" value="${jdbc.username}"/>
        <property name="password" value="${jdbc.password}"/>
    </bean>-->

    <!-- SqlSession -->
    <bean id="sqlSession" class="org.mybatis.spring.SqlSessionFactoryBean">
        <!-- 指定mybatis配置文件路径，也可以不使用配置文件的形式，直接使用configuration属性配置，只能二选一 -->
        <!--<property name="configLocation" value="classpath:mybatis-config.xml"/>-->
        <!-- 指定数据源 -->
        <property name="dataSource" ref="dataSource"/>
        <!-- 指定mapper扫描路径 -->
        <property name="mapperLocations" value="classpath:com/zds/mapper/*.xml"/>
        <!-- 指定设置javaBean的别名包路径 -->
        <property name="typeAliasesPackage" value="com.zds.entity"/>
        <!-- 其他设置 -->
        <property name="configuration">
            <bean class="org.apache.ibatis.session.Configuration">
                <!-- 指定日志类 -->
                <property name="logImpl" value="org.apache.ibatis.logging.stdout.StdOutImpl"/>
                <!-- 开始驼峰式命名自动转换 -->
                <property name="mapUnderscoreToCamelCase" value="true"/>
            </bean>
        </property>
    </bean>

    <!-- 配置Mapper接口扫描，自动将代理mapper注入spring容器中 -->
    <bean class="org.mybatis.spring.mapper.MapperScannerConfigurer">
        <!-- 指定mapper接口的包路径 -->
        <property name="basePackage" value="com.zds.mapper"/>
        <property name="sqlSessionFactoryBeanName" value="sqlSession"/>
    </bean>

    <!-- 配置事务管理 -->
    <bean id="transactionManager" class="org.springframework.jdbc.datasource.DataSourceTransactionManager">
        <property name="dataSource" ref="dataSource"/>
    </bean>

    <!-- 开启事务注解，再service层方法上加上@Transactional实现 -->
    <tx:annotation-driven transaction-manager="transactionManager"/>
</beans>
```



mybatis-config.xml

```xml
<?xml version="1.0" encoding="UTF-8" ?>
<!DOCTYPE configuration PUBLIC "-//mybatis.org//DTD Config 3.0//EN"
        "http://mybatis.org/dtd/mybatis-3-config.dtd">
<configuration>
    <properties resource="jdbc.properties"/>
    <settings>
        <!-- 日志实现 -->
        <setting name="logImpl" value="STDOUT_LOGGING"/>
        <!-- 数据库列名自动转换驼峰式 -->
        <setting name="mapUnderscoreToCamelCase" value="true"/>
    </settings>
    <!-- 别名定义 -->
    <typeAliases>
        <package name="com.zds.entity"/>
    </typeAliases>

    <mappers>
        <mapper resource="com/zds/mapper/CustomMapper.xml"/>
    </mappers>

</configuration>
```

### 3、编写其他类与测试类

见代码

### 4、事务管理

首先在spring配置文件中开启spring事务管理，并开启事务注解扫描

```xml
<!-- 配置事务管理 -->
<bean id="transactionManager" class="org.springframework.jdbc.datasource.DataSourceTransactionManager">
    <property name="dataSource" ref="dataSource"/>
</bean>

<!-- 开启事务注解，再service层方法上加上@Transactional实现 -->
<tx:annotation-driven transaction-manager="transactionManager"/>
```



在service层中的方法加上@Transactional注解，如果在该方法中出现异常，事务会自动回滚。

```java
/**
     * 开启事务,验证该方法执行过程中报错是否会自动回滚
     * 如果不使用事务注解开启事务管理，该方法会成功插入数据
     * 如果使用注解开启事务，则会自动回滚之前的插入操作
     *
     * @param customer customer
     * @return int
     */
@Transactional
@Override
public int insertCustom(Custom customer) {
    int rows = customMapper.insertCustom(customer);
    int i = 1 / 0;
    return rows;
}
```

事务的隔离级别和传播特性

@Transactional注解可配置隔离级别属性isolation

isolation有以下值

| 值                | 说明                                                         |
| ----------------- | ------------------------------------------------------------ |
| READ_UNCOMMITTED  | 读未提交（隔离级别最差，脏读、不可重复读、幻读都不能避免）   |
| READ_COMMITTED    | 读已提交（默认值，事务只能看到其他事务提交之后的数据。可避免脏读，不可重复读、幻读无法避免） |
| REPEATABLE_READ   | 可重复读                                                     |
| SERIALIZABLE      | 序列化                                                       |
| Isolation.DEFAULT | 默认值，采用每个数据库底层事务的默认机制                     |

@Transactional注解可配置传播特性属性propagation

**注意：**传播特性是针对子业务操作的事务而言的，即ServiceA中调用ServiceB，这里的传播特性就是指ServiceB的事务该如何控制。

| 值                                                           | 说明                                                         |
| ------------------------------------------------------------ | ------------------------------------------------------------ |
| REQUIRED(TransactionDefinition.PROPAGATION_REQUIRED)         | 若当前存在事务，则加入该事务，若不存在事务，则新建一个事务。 |
| SUPPORTS(TransactionDefinition.PROPAGATION_SUPPORTS)         | 支持当前事务，若当前不存在事务，以非事务的方式执行。         |
| MANDATORY(TransactionDefinition.PROPAGATION_MANDATORY)       | 强制事务执行，若当前不存在事务，则抛出异常。                 |
| REQUIRES_NEW(TransactionDefinition.PROPAGATION_REQUIRES_NEW) | 若当前没有事务，则新建一个事务。若当前存在事务，则新建一个事务，新老事务相互独立。外部事务抛出异常回滚不会影响内部事务的正常提交。 |
| NOT_SUPPORTED(TransactionDefinition.PROPAGATION_NOT_SUPPORTED) | 以非事务的方式执行，若当前存在事务，则把当前事务挂起。       |
| NEVER(TransactionDefinition.PROPAGATION_NEVER)               | 以非事务的方式执行，如果当前存在事务，则抛出异常。           |
| NESTED(TransactionDefinition.PROPAGATION_NESTED)             | 如果当前存在事务，则嵌套在当前事务中执行。如果当前没有事务，则新建一个事务，类似于REQUIRE_NEW。 |

[ref]: https://blog.csdn.net/qq_17085835/article/details/84837253

