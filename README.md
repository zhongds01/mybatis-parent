## 1、mapper基本标签

- insert

  > insert标签中属性的含义
  >
  > parameterType：传入的参数类型，如果在接口参数使用了@Param注解后，可以不设置该属性。

  ```xml
  <insert id="insertStudent" parameterType="student">
      insert into student
      values (#{id}, #{sName}, #{sBirthday}, #{sSex})
  </insert>
  ```

- update

  > update标签中属性的含义
  >
  > parameterType：传入的参数类型，如果在接口参数使用了@Param注解后，可以不设置该属性。

  ```xml
  <update id="updateStudent" parameterType="student">
      update student
      <set>
          <if test="sName != null">
              s_name = #{sName}
          </if>
          <if test="sBirthday != null">
              , s_birthday = #{sBirthday}
          </if>
          <if test="sSex != null">
              , s_sex = #{sSex}
          </if>
      </set>
      where id = #{id}
  </update>
  ```

- delete

  > delete标签中属性的含义
  >
  > parameterType：传入的参数类型，如果在接口参数使用了@Param注解后，可以不设置该属性。
  >
  > parameterMap：与resultMap类似，需要手动定义parameterMap标签，不常用。

  ```xml
  <delete id="deleteStudentById" parameterType="long">
      delete
      from student
      where id = #{id}
  </delete>
  ```

- select

  > select标签中属性的含义
  >
  > parameterType：传入的参数类型，如果在接口参数使用了@Param注解后，可以不设置该属性。
  >
  > resultType：返回值类型，一般为javaBean的全类路径名，如果设置了别名，可以使用类名或类名首字母小写表示。
  >
  > resultMap：返回值类型，只能为使用resultMap标签指定的id

  ```xml
  <select id="selectOneCustomById" parameterType="long" resultType="customer">
      select * from customer where custom_id = #{id}
  </select>
  <select id="getScoreWithStudentByStuIdAndCourseId" resultMap="scoreAndStudent">
      select sc.s_id, sc.c_id, sc.score, stu.id, stu.s_name, stu.s_birthday, stu.s_sex
      from score sc,
      student stu
      where sc.s_id = stu.id
      and sc.c_id = #{cId}
      and sc.s_id = #{sId}
  </select>
  ```

- resultMap

  > resultMap标签中属性的含义
  >
  > type：该map中存放的属性类型，一般为javaBean的全类路径名，如果设置了别名，可以使用类名或类名首字母小写表示。
  >
  > extends：继承其他的resultMap
  >
  > association：如果该结果集中的属性为其他javaBean实体类，需要使用association标签**association中的javaType指定类别名**。
  >
  > collection：如果该结果集中的属性为其他javaBean实体类的List集合，需要使用collection标签。**collection标签中的ofType指明List集合中泛型对应的类别名**
  >
  > 剩下的id、result表示javaBean属性名与数据库表列名的映射关系。（id特指主键）

  ```xml
  <resultMap id="scoreMap" type="com.zds.entity.Score">
      <result property="sId" column="s_id"/>
      <result property="tId" column="t_id"/>
      <result property="score" column="score"/>
  </resultMap>
  <resultMap id="scoreAndStudent" type="com.zds.entity.ScoreAndStudent" extends="scoreMap">
      <association property="student" javaType="com.zds.entity.Student">
          <id property="id" column="id"/>
          <result property="sName" column="s_name"/>
          <result property="sBirthday" column="s_birthday"/>
          <result property="sSex" column="s_sex"/>
      </association>
  </resultMap>
  
  <resultMap id="teacherMap" type="com.zds.entity.Teacher">
      <id property="id" column="t_id"/>
      <result property="tName" column="t_name"/>
  </resultMap>
  <resultMap id="teacherWithStudentMap" type="com.zds.entity.TeacherAndStudent" extends="teacherMap">
      <collection property="studentList" ofType="com.zds.entity.Student">
          <id column="s_id" property="id"/>
          <result property="sName" column="s_name"/>
          <result property="sBirthday" column="s_birthday"/>
          <result property="sSex" column="s_sex"/>
      </collection>
  </resultMap>
  ```

- sql

  > 定义一个可重用的sql片段

  ```xml
  <sql id="studentColumns">
      id
      ,s_name,s_birthday,s_sex
  </sql>
  ```

  > 使用方式：使用 <include refid="studentColumns"/>

  ```xml
  <select id="selectStudentById" parameterType="long" resultType="student">
      select
      <include refid="studentColumns"/>
      from student where id = #{id}
  </select>
  ```

  

## 2、动态 SQL

- if判断

  > 用于表达式判断，只有if标签中的表达式成立时，该标签下的sql才会生效。

  ```xml
  <update id="updateStudent" parameterType="student">
      update student
      <set>
          <if test="sName != null">
              s_name = #{sName}
          </if>
          <if test="sBirthday != null">
              , s_birthday = #{sBirthday}
          </if>
          <if test="sSex != null">
              , s_sex = #{sSex}
          </if>
      </set>
      where id = #{id}
  </update>
  ```

- choose选择

  > choose、when、otherwise结合使用，when和otherwise同时只能返回一个，多个when时，也只会返回第一个符合条件的when。

  > 以下如果sName不为空，设置sName为传入的sName，否则设置为默认值1

  ```xml
  <update id="updateStudent" parameterType="student">
      update student
      set
      <choose>
          <when test="sName != null">
              s_name = #{sName}
          </when>
          <otherwise>
              s_name = 1
          </otherwise>
      </choose>
      where id = #{id}
  </update>
  ```

- where

  > where标签作用就是where关键字，不同的是，标签可以自动去除第一个指定的符合。
  >
  > **注意：**foreach中的标签collections的值一般为list或array，分别对应List集合和数组的场景，但是如果使用了@Param注解指定了名称，就可以使用指定的名称（ids）

  ```xml
  <delete id="batchDeleteStudentByIds">
      delete from student
      <where>
          <if test="ids != null and ids.size() > 0">
              id in
              <foreach collection="ids" item="id" open="(" close=")" separator=",">
                  #{id}
              </foreach>
          </if>
      </where>
  </delete>
  ```

- set

  > 和where标签类似，用于修改数据时使用，相当于set关键字。

- trim

  > trim可以自定义匹配规则，通过trim可以实现where和set标签的作用

  ```xml
  <trim prefix="WHERE" prefixOverrides="AND |OR ">
    ...
  </trim>
  <trim prefix="SET" suffixOverrides=",">
    ...
  </trim>
  ```



## 3、一对多映射关系

> 一个成绩对应一个学生信息，一个学生信息可以对应多个成绩
>
> 根据学生信息获取该学生的成绩信息，就是一对多

```java
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Student {
    private Long id;
    private String sName;
    private Date sBirthday;
    private String sSex;
}



import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.util.List;

/**
 * 学生和成绩类
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class StudentAndScore extends Student {
    private List<Score> scoreList;

    @Override
    public String toString() {
        return "StudentAndScore{" +
                "scoreList=" + scoreList +
                "} " + super.toString();
    }
}
// mapper接口定义
public interface StudentMapper {
    List<StudentAndScore> getStudentWithScoreById(@Param("id") long id);
}
```

mapper文件定义

```xml
<select id="getStudentWithScoreById" resultMap="studentWithScoreMap">
    select stu.id s_id, stu.s_name, stu.s_birthday, stu.s_sex, sc.s_id, sc.c_id, sc.score
    from student stu,
    score sc
    where stu.id = sc.s_id and stu.id = #{id}
</select>
```

测试类

```java
/**
 * 获取学生id所有的学生信息以及该学生的所有课程成绩（一对多）
 */
@Test
public void testGetStudentWithScoreById() {
    SqlSession sqlSession = SqlSessionUtil.getSqlSession();
    StudentMapper mapper = sqlSession.getMapper(StudentMapper.class);
    List<StudentAndScore> students = mapper.getStudentWithScoreById(1L);
    students.forEach(System.out::println);
    sqlSession.close();
}
```



## 4、一对一映射关系

> 一个成绩对应一个学生信息，一个学生信息可以对应多个成绩
>
> 根据成绩信息获取学生信息，就是一对一

```java
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Score {
    private Long sId;
    private Long cId;
    private String score;
}

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * 成绩类中有一个学生信息
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ScoreAndStudent extends Score {
    private Student student;

    @Override
    public String toString() {
        return "ScoreAndStudent{" +
                "student=" + student +
                "} " + super.toString();
    }
}

// mapper接口定义
import com.zds.entity.ScoreAndStudent;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface ScoreMapper {
    List<ScoreAndStudent> getScoreWithStudentByStuIdAndCourseId(@Param("sId") long sId, @Param("cId") long cId);
}


```

mapper文件定义

```xml
<?xml version="1.0" encoding="UTF-8" ?>
<!DOCTYPE mapper PUBLIC "-//mybatis.org//DTD Config 3.0//EN"
        "http://mybatis.org/dtd/mybatis-3-mapper.dtd">
<mapper namespace="com.zds.mapper.ScoreMapper">
    <resultMap id="scoreMap" type="com.zds.entity.Score">
        <result property="sId" column="s_id"/>
        <result property="tId" column="t_id"/>
        <result property="score" column="score"/>
    </resultMap>
    <resultMap id="scoreAndStudent" type="com.zds.entity.ScoreAndStudent" extends="scoreMap">
        <association property="student" javaType="com.zds.entity.Student">
            <id property="id" column="id"/>
            <result property="sName" column="s_name"/>
            <result property="sBirthday" column="s_birthday"/>
            <result property="sSex" column="s_sex"/>
        </association>
    </resultMap>
    <select id="getScoreWithStudentByStuIdAndCourseId" resultMap="scoreAndStudent">
        select sc.s_id, sc.c_id, sc.score, stu.id, stu.s_name, stu.s_birthday, stu.s_sex
        from score sc,
        student stu
        where sc.s_id = stu.id
        and sc.c_id = #{cId}
        and sc.s_id = #{sId}
    </select>
</mapper>
```

测试类

```java
import com.zds.mapper.ScoreMapper;
import com.zds.entity.ScoreAndStudent;
import com.zds.util.SqlSessionUtil;
import org.apache.ibatis.session.SqlSession;
import org.junit.Test;

import java.util.List;

public class ScoreMapperTest {
    /**
     * 测试根据学生id,课程id查询该学生信息以及课程分数(一对一)
     */
    @Test
    public void testGetScoreWithStudentByStuIdAndCourseId() {
        SqlSession sqlSession = SqlSessionUtil.getSqlSession();
        ScoreMapper mapper = sqlSession.getMapper(ScoreMapper.class);
        List<ScoreAndStudent> scores = mapper.getScoreWithStudentByStuIdAndCourseId(1L,1L);
        scores.forEach(System.out::println);
        sqlSession.close();
    }

}
```

## 5、浅谈MySql中的事务

> 事务四大特性

- 原子性：同一事务中的所有操作，要么全部成功，要么全部失败。
- 一致性：事务完成后，数据库中的数据总体不会发生改变。
- 持久性：事务一旦提交后，对数据库的改变就是永久的。
- 隔离性：事务并发时，不同事务之间所做的操作互不影响。

> 事务隔离级别

- READ_UNCOMMITTED：读未提交内容，一个事务A可以读取别的事务B未提交的数据。一旦事务B发生回滚，则事务A产生**脏读**。
- READ_COMMITTED：读已提交内容，一个事务A只能读取别的事务B已提交的数据。如果事务A第一次读取后，事务B更新了该数据，事务A第二次读取时就会发现与第一次读取的数据不一致，从而导致**不可重复读**。
- REPEATABLE_READ：可重复读（MySql默认隔离级别）
- SERIALIZABLE：串行化

| 隔离级别     | 脏读   | 不可重复读 | 幻读   |
| ------------ | ------ | ---------- | ------ |
| **读未提交** | **有** | **有**     | **有** |
| **读已提交** | 无     | **有**     | **有** |
| **可重复读** | 无     | 无         | **有** |
| **串行化**   | 无     | 无         | 无     |

> 事务传播行为

| 事务传播行为类型 | 说明                                                         |
| :--------------- | :----------------------------------------------------------- |
| REQUIRED         | 若是有事务在运行，当前的方法就在这个事务内运行；不然，就启动一个新的事务，并在本身的事务内运行； |
| REQUIRES_NEW     | 当前的方法必须启动新事务，并在它本身的事务内运行；若是有事务正在运行，应该将它挂起。 |
| MANDATORY        | 当前的方法必须运行在事务内部，若是没有正在运行的事务，将抛出异常。 |
| SUPPORTS         | 若是有事务在运行，当前的方法就在这个事务内运行；不然它能够不运行在事务中。 |
| NOT_SUPPORTED    | 当前的方法不该该运行在事务中，若是有运行的事务，将它挂起。   |
| NEVER            | 当前的方法不该该运行在事务中，若是有运行的事务，就抛出异常。 |
| NESTED           | 若是有事务在运行，当前的方法就应该在这个事务的嵌套事务内运行。不然，就启动一个新的事务，并在它本身的事务内运行。 |

> 代码演示脏读、不可重复读、幻读场景

- 脏读

```java
// 事务A进行插入一条数据操作，但最终回抛异常进行回滚
@Override
@Transactional(rollbackFor = Exception.class)
public void saveCustomer(Customer customer) throws Exception {
    customerMapper.insert(customer);
    throw new Exception("");
}
// 事务B设置事务的隔离级别为READ_UNCOMMITTED，且查询事务A插入的数据。该场景下事务B可以读取到数据。但最终事务A会回滚，数据库中不会存在该记录，这就是脏读。
@Transactional(isolation = Isolation.READ_UNCOMMITTED, rollbackFor = Exception.class)
public void useDefaultIsolationAndRequiredPropagation(Long id) {
    // 1415300753928499216
    Customer customer = customerMapper.selectOneCustomById(id);
    System.out.println(customer);
}
```

脏读解决：设置事务隔离级别为READ_COMMITTED

```java
// 事务A进行插入一条数据操作，但最终回抛异常进行回滚
@Override
@Transactional(rollbackFor = Exception.class)
public void saveCustomer(Customer customer) throws Exception {
    customerMapper.insert(customer);
    throw new Exception("");
}
// 事务B设置事务的隔离级别为READ_COMMITTED，且查询事务A插入的数据。该场景下事务B无法读取到数据。就算事务A会回滚，数据库中不会存在该记录，但别的事务也不会受到影响。
@Transactional(isolation = Isolation.READ_COMMITTED, rollbackFor = Exception.class)
public void useDefaultIsolationAndRequiredPropagation(Long id) {
    // 1415300753928499216
    Customer customer = customerMapper.selectOneCustomById(id);
    System.out.println(customer);
}
```

- 不可重复读（强调update操作）

操作前提：关闭mybatis的一级缓存

```properties
mybatis-plus.configuration.local-cache-scope=statement
```

```java
// 事务A进行更新数据操作，更新完成后正常提交事务
@Transactional(rollbackFor = Exception.class)
public void updateCustomer() {
    Customer customer = new Customer();
    customer.setId(1415300753928499216L);
    customerMapper.updateById(customer);
}
// 事务B设置事务的隔离级别为READ_COMMITTED，先执行一次查询操作，随后等待事务A将数据进行更新后，再次进行相同的查询操作，最终发现两次查询结果不一致，导致不可重复读。
@Transactional(isolation = Isolation.READ_UNCOMMITTED, rollbackFor = Exception.class)
public void useDefaultIsolationAndRequiredPropagation(Long id) throws InterruptedException {
    // 1415300753928499216L
    Customer customer = customerMapper.selectOneCustomById(id);
    // 模拟另一个事务进行更新操作耗时
    Thread.sleep(5000L);
    // 更新数据库中的updateTime字段
    Customer newCustomer = customerMapper.selectOneCustomById(id);
    System.out.println(newCustomer.getUpdateTime().equals(customer.getUpdateTime()));// false
}
```

不可重复读解决：设置事务隔离级别为REPEATABLE_READ

```java
// 事务A进行更新数据操作，更新完成后正常提交事务
@Transactional(rollbackFor = Exception.class)
public void updateCustomer() {
    Customer customer = new Customer();
    customer.setId(1415300753928499216L);
    customerMapper.updateById(customer);
}
// 事务B设置事务的隔离级别为READ_COMMITTED，先执行一次查询操作，随后等待事务A将数据进行更新后，再次进行相同的查询操作，最终发现两次查询结果不一致，导致不可重复读。
@Transactional(isolation = Isolation.REPEATABLE_READ, rollbackFor = Exception.class)
public void useDefaultIsolationAndRequiredPropagation(Long id) throws InterruptedException {
    // 1415300753928499216L
    Customer customer = customerMapper.selectOneCustomById(id);
    // 模拟另一个事务进行更新操作耗时
    Thread.sleep(5000L);
    // 更新数据库中的updateTime字段
    Customer newCustomer = customerMapper.selectOneCustomById(id);
    System.out.println(newCustomer.getUpdateTime().equals(customer.getUpdateTime()));// true
}
```

- 幻读（强调insert）

  > 1.  在可重复读隔离级别下，普通的查询是快照读，同一事务中读取到的数据是一样的，无法读取别的事务插入的数据的。因此，幻读在“当前读”下才会出现。
  > 2. **当前读**操作中索引失效时，会造成全表扫描，导致全表数据被锁住。

  可重复读隔离级别下，保证了一个事务内查询到的数据的一致性，但也正是因为该一致性，导致这个事务中无法读取到别的事务中插入的数据。如果该事务也进行了数据插入操作，会报主键冲突

  ```sql
  -- 开启事务A
  BEGIN; -- T1
  -- 快照读
  SELECT * FROM customer WHERE customer.customer_name = 'zhongdongsheng'; -- T2
  SELECT * FROM customer WHERE customer.customer_name = 'zhongdongsheng'; -- T4
  -- T2,T4查询结果一致（可重复读），T5执行（当前读）会报主键冲突
  INSERT INTO customer(id, customer_name) VALUE(1415300753928499217, 'zhongdongsheng'); -- T5
  -- 事务A提交
  COMMIT; -- T6
  
  -- 事务B，直接插入数据并提交（默认就是自动提交事务）
  INSERT INTO customer(id, customer_name) VALUE(1415300753928499217, 'zhongdongsheng'); -- T3
  ```

