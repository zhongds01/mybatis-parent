# mybatis-cache
测试mybatis中缓存的基本使用

### 1、一级缓存

基于sqlsession级别，只在一个sqlsession中有效。遇到新增、删除、更新操作缓存自动清空。

```java
/**
 * 测试一级缓存（一级缓存基于SqlSession的生命周期，只在同一个SqlSession中有效）
 */
@Test
public void testLevel1Cache() {
    SqlSession sqlSession = SqlSessionUtil.getSqlSession();
    CustomMapper mapper = sqlSession.getMapper(CustomMapper.class);
    // 第一次查询id为1415300753928499202的客户信息
    mapper.selectOneCustomById(1415300753928499202L).forEach(System.out::println);
    // 第二次查询id为1415300753928499202的客户信息，就会从缓存中取，而不是直接查询数据库
    mapper.selectOneCustomById(1415300753928499202L).forEach(System.out::println);
    sqlSession.close();

    System.out.println("--------------------------------------");
    // 如果再另一个SqlSession中查询1415300753928499202的客户信息，就会重新去数据库中查询
    SqlSession sqlSession1 = SqlSessionUtil.getSqlSession();
    CustomMapper mapper1 = sqlSession1.getMapper(CustomMapper.class);
    // 第一次查询id为1415300753928499202的客户信息
    mapper1.selectOneCustomById(1415300753928499202L).forEach(System.out::println);
    sqlSession1.close();

    System.out.println("--------------------------------------");
    // 如果两次查询之间做了插入，修改，删除等操作，会自动刷新缓存
    SqlSession sqlSession2 = SqlSessionUtil.getSqlSession();
    CustomMapper mapper2 = sqlSession2.getMapper(CustomMapper.class);
    // 第一次查询id为1415300753928499202的客户信息
    mapper2.selectOneCustomById(1415300753928499202L).forEach(System.out::println);
    // 插入一条新纪录
    mapper2.insertCustom(Custom.builder().id(1415300753928499205L).customName("zhongdongsheng").customPwd("123456").customSex("男").customTel("13260906627").customEmail("zhongds01@163.com").customAddress("Nanjing").status("U").build());
    // 第二次查询id为1415300753928499202的客户信息
    mapper2.selectOneCustomById(1415300753928499202L).forEach(System.out::println);
    sqlSession2.close();
}
```

### 2、二级缓存

基于mapper级别，在同一个mapper中有效。遇到新增、删除、更新操作缓存自动清空。

mapper.xml

```xml
<?xml version="1.0" encoding="UTF-8" ?>
<!DOCTYPE mapper PUBLIC "-//mybatis.org//DTD Config 3.0//EN"
        "http://mybatis.org/dtd/mybatis-3-mapper.dtd">
<mapper namespace="com.zds.mapper.CustomMapper">
    <!-- 如果使用二级缓存，需要配置以下标签开启 -->
    <cache/>
    <resultMap id="customMap" type="com.zds.pojo.Custom">
        <id column="custom_id" property="id"/>
    </resultMap>
    <!-- 如果想禁用某条查询的缓存，在查询标签中使用useCache="false"配置 -->
    <select id="selectOneCustomById" parameterType="long" resultMap="customMap" useCache="false">
        select *
        from custom
        where custom_id = #{id}
    </select>

    <insert id="insertCustom">
        insert into custom
        values (#{custom.id}, #{custom.customName}, #{custom.customPwd}, #{custom.customSex}, #{custom.customTel},
        #{custom.customEmail}, #{custom.customAddress}, null, null, #{custom.status})
    </insert>
</mapper>
```

```java
/**
 * 测试二级缓存（二级缓存基于Mapper文件，在同一个Mapper.xml中有效）
 */
@Test
public void testLevel2Cache() {
    System.out.println("--------------------------------------");
    SqlSession sqlSession = SqlSessionUtil.getSqlSession();
    CustomMapper mapper = sqlSession.getMapper(CustomMapper.class);
    // 在一个sqlSession中第一次查询id为1415300753928499202的客户信息
    mapper.selectOneCustomById(1415300753928499202L).forEach(System.out::println);
    sqlSession.close();
    // 在另一个sqlSession中第一次查询id为1415300753928499202的客户信息
    SqlSession sqlSession1 = SqlSessionUtil.getSqlSession();
    CustomMapper mapper1 = sqlSession1.getMapper(CustomMapper.class);
    mapper1.selectOneCustomById(1415300753928499202L).forEach(System.out::println);
    sqlSession1.close();
    System.out.println("--------------------------------------");
    // 如果两次查询之间做了插入，修改，删除等操作，会自动刷新缓存
    SqlSession sqlSession2 = SqlSessionUtil.getSqlSession();
    CustomMapper mapper2 = sqlSession2.getMapper(CustomMapper.class);
    // 在一个sqlSession中第一次查询id为1415300753928499202的客户信息
    mapper2.selectOneCustomById(1415300753928499202L).forEach(System.out::println);
    // mapper2.insertCustom(Custom.builder().id(1415300753928499204L).customName("zhongdongsheng").customPwd("123456").customSex("男").customTel("13260906627").customEmail("zhongds01@163.com").customAddress("Nanjing").status("U").build());
    sqlSession2.close();
    // 在另一个sqlSession中第一次查询id为1415300753928499202的客户信息
    SqlSession sqlSession3 = SqlSessionUtil.getSqlSession();
    CustomMapper mapper3 = sqlSession3.getMapper(CustomMapper.class);
    mapper3.selectOneCustomById(1415300753928499202L).forEach(System.out::println);
    sqlSession3.close();
    System.out.println("--------------------------------------");
}
```



### 3、mybatis-config.xml

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
        <package name="com.zds.pojo"/>
    </typeAliases>
    <environments default="dev">
        <environment id="dev">
            <transactionManager type="jdbc"/>
            <dataSource type="pooled">
                <property name="driver" value="${driver}"/>
                <property name="url" value="${url}"/>
                <property name="username" value="${username}"/>
                <property name="password" value="${password}"/>
            </dataSource>
        </environment>
    </environments>

    <mappers>
        <mapper resource="com/zds/mapper/CustomMapper.xml"/>
    </mappers>

</configuration>
```



