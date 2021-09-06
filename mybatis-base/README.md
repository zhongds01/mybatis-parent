# mybatis-base
mybatis基本使用

### 1、mybatis-config.xml

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
    <environments default="dev-dbcp2">
        <environment id="dev-default">
            <transactionManager type="jdbc"/>
            <dataSource type="pooled">
                <property name="driver" value="${driver}"/>
                <property name="url" value="${url}"/>
                <property name="username" value="${username}"/>
                <property name="password" value="${password}"/>
            </dataSource>
        </environment>
        <environment id="dev-c3p0">
            <transactionManager type="JDBC"></transactionManager>
            <dataSource type="com.zds.datasource.C3P0DataSource">
                <property name="driverClass" value="${driver}"/>
                <property name="jdbcUrl" value="${url}"/>
                <property name="user" value="${username}"/>
                <property name="password" value="${password}"/>
            </dataSource>
        </environment>
        <environment id="dev-dbcp2">
            <transactionManager type="JDBC"></transactionManager>
            <dataSource type="com.zds.datasource.Dbcp2DataSource">
                <property name="driverClassName" value="${driver}"/>
                <property name="url" value="${url}"/>
                <property name="username" value="${username}"/>
                <property name="password" value="${password}"/>
            </dataSource>
        </environment>
    </environments>

    <mappers>
        <mapper resource="com/zds/mapper/CustomMapper.xml"/>
        <mapper resource="com/zds/mapper/StudentMapper.xml"/>
        <mapper resource="com/zds/mapper/TeacherMapper.xml"/>
        <mapper resource="com/zds/mapper/ScoreMapper.xml"/>
    </mappers>

</configuration>
```

### 2、不同的数据源连接池

mybatis默认不支持自动配置第三方数据源驱动连接池，所以需要手动编写连接池类。

#### 2.1、C3P0

数据源配置见1章节中的dev-c3p0配置

```java
import com.mchange.v2.c3p0.ComboPooledDataSource;
import org.apache.ibatis.datasource.unpooled.UnpooledDataSourceFactory;

public class C3P0DataSource extends UnpooledDataSourceFactory {
    public C3P0DataSource() {
        this.dataSource = new ComboPooledDataSource();
    }
}
```

#### 2.2、dbcp2

数据源配置见1章节中的dev-dbcp2配置

```java
import org.apache.commons.dbcp2.BasicDataSource;
import org.apache.ibatis.datasource.unpooled.UnpooledDataSourceFactory;

public class Dbcp2DataSource extends UnpooledDataSourceFactory {
    public Dbcp2DataSource() {
        this.dataSource = new BasicDataSource();
    }
}
```

### 3、使用SqlSessionFactory获取SqlSession

可使用静态单例模式

```java
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import java.io.IOException;

public class SqlSessionUtil {
    private static SqlSessionFactory sqlSessionFactory;

    static {
        try {
            sqlSessionFactory = new SqlSessionFactoryBuilder().build(Resources.getResourceAsReader("mybatis-config.xml"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static SqlSession getSqlSession() {
        return sqlSessionFactory.openSession(true);
    }
}
```

### 4、javaBean创建

```java
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Custom implements Serializable {
    private static final long serialVersionUID = 9157528864358633663L;
    private Long id;
    private String customName;
    private String customPwd;
    private String customSex;
    private String customTel;
    private String customEmail;
    private String customAddress;
    private Date createDate;
    private Date modifyDate;
    private String status;
}
```



### 5、mapper接口和文件编写

详细代码见源码

```java
import com.zds.entity.Customer;
import org.apache.ibatis.annotations.Param;

public interface CustomMapper {
    Customer selectOneCustomById(@Param("id") Long id);
}
```

```xml
<?xml version="1.0" encoding="UTF-8" ?>
<!DOCTYPE mapper PUBLIC "-//mybatis.org//DTD Config 3.0//EN"
        "http://mybatis.org/dtd/mybatis-3-mapper.dtd">
<mapper namespace="com.zds.mapper.CustomerMapper">
    <select id="selectOneCustomById" parameterType="long" resultType="customer">
        select * from customer where custom_id = #{id}
    </select>
</mapper>
```

### 6、测试类测试

```java
import com.zds.entity.Customer;
import com.zds.mapper.CustomerMapper;
import com.zds.entity.Customer;
import com.zds.util.SqlSessionUtil;
import org.apache.ibatis.session.SqlSession;
import org.junit.Test;

public class CustomMapperTest {
    @Test
    public void testSelectOneCustomById() {
        SqlSession sqlSession = SqlSessionUtil.getSqlSession();
        CustomMapper mapper = sqlSession.getMapper(CustomMapper.class);
        Customer customer = mapper.selectOneCustomById(1415300753928499202L);
        System.out.println(customer);
        sqlSession.close();
    }
}
```

