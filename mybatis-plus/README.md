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

> 编写基类，存放主键id，创建时间，更新时间，逻辑删除等字段

```java
@Data
@Accessors(chain = true)
public class BaseEntity {
    @TableId(
        value = "id",
        type = IdType.ASSIGN_ID
    )
    private Long id;

    @DateTimeFormat(
        pattern = "yyyy-MM-dd HH:mm:ss"
    )
    @TableField(fill = FieldFill.INSERT)
    private Date createTime;

    @DateTimeFormat(
        pattern = "yyyy-MM-dd HH:mm:ss"
    )
    @TableField(fill = FieldFill.INSERT_UPDATE)
    private Date updateTime;

    @TableField(fill = FieldFill.INSERT)
    @TableLogic()
    private Integer isDeleted;
}
```

> 创建数据库实体类，继承基类BaseEntity

```java
@Data
@AllArgsConstructor
@NoArgsConstructor
@Accessors(chain = true)
public class Customer extends BaseEntity implements Serializable {
    private static final long serialVersionUID = 9157528864358633663L;
    private String customerName;
    private String customerPassword;
    private String customerSex;
    private String customerTel;
    private String customerEmail;
    private String customerAddress;
}
```



## 4、编写mapper接口

> 自定义定义接口，继承mybatis-plus提供的BaseMapper接口。

```java
public interface CustomMapper extends BaseMapper<Custom> {
}
```

## 5、在springboot启动类上加上mapper接口包路径扫描，或在自定义配置类上加

```java
@Configuration
@MapperScan(value = "com.zds.mapper")
public class MybatisPlusConfig {
}
```

## 6、编写测试类

```java
@Test
void testInsert() {
    Customer customer = new Customer().setCustomerName("zhouniang").setCustomerPassword("123456").setCustomerSex("男").setCustomerTel("13260900000").setCustomerEmail("zhongds01@163.com").setCustomerAddress("Wuxi");
    int rows = customMapper.insert(customer);
    System.out.println("insert rows = " + rows);
}
```

## 7、条件构造器

### 7.1、AbstractWrapper

#### 7.1.1、allEq

> 全部等于或如果为null，可以为is null 也可以不存在

```java
public <V> Children allEq(boolean condition, BiPredicate<R, V> filter, Map<R, V> params, boolean null2IsNull)
// condition：当前条件是否生效（true：生效，false：不生效）
// filter：过滤params中符合要求的键值对
// params：查询条件，封装在map中，key为列名，value为列值
// null2IsNull：如果map中存在key对应的value为null的情况。（true：查询条件变为key对应的列名 is null，false：查询条件没有该key对应的列名）
map.put("customer_name", "zhouliang");
map.put("customer_sex", "男");
map.put("customer_password", null);
new QueryWrapper<Customer>().allEq(true, (k, v) -> k.equals("customer_name"), map, true);
// 由于使用了过滤规则，过滤出当map的key为customer_name作为查询条件，因此生成的sql为
// SELECT id,customer_name,customer_password,customer_sex,customer_tel,customer_email,customer_address,create_time,update_time,is_deleted FROM customer WHERE is_deleted=0 AND (customer_name = ?)
new QueryWrapper<Customer>().allEq(true, map, true);
// 移除过滤规则，但设置了null2IsNull为true，因此sql为
// SELECT id,customer_name,customer_password,customer_sex,customer_tel,customer_email,customer_address,create_time,update_time,is_deleted FROM customer WHERE is_deleted=0 AND (customer_password IS NULL AND customer_sex = ? AND customer_name = ?)
new QueryWrapper<Customer>().allEq(true, map, false);
// 移除过滤规则，但设置了null2IsNull为false，因此sql为
// SELECT id,customer_name,customer_password,customer_sex,customer_tel,customer_email,customer_address,create_time,update_time,is_deleted FROM customer WHERE is_deleted=0 AND (customer_sex = ? AND customer_name = ?)
new QueryWrapper<Customer>().allEq(false, (k, v) -> k.equals("customer_name"), map, true);
// 设置condition为false，sql为
// SELECT id,customer_name,customer_password,customer_sex,customer_tel,customer_email,customer_address,create_time,update_time,is_deleted FROM customer WHERE is_deleted=0
```

#### 7.1.2、eq

> 等于

```java
customerMapper.selectList(new QueryWrapper<Customer>().eq(true, "id", 1432873878224494594L)).forEach(System.out::println);
// sql为
// SELECT id,customer_name,customer_password,customer_sex,customer_tel,customer_email,customer_address,create_time,update_time,is_deleted FROM customer WHERE is_deleted=0 AND (id = ?)
```

#### 7.1.3、ne

> 不等于

```java
customerMapper.selectList(new QueryWrapper<Customer>().ne(true, "id", 1432873878224494594L)).forEach(System.out::println);
// SELECT id,customer_name,customer_password,customer_sex,customer_tel,customer_email,customer_address,create_time,update_time,is_deleted FROM customer WHERE is_deleted=0 AND (id <> ?)
```

#### 7.1.4、gt

> 大于

```java
customerMapper.selectList(new QueryWrapper<Customer>().gt(true, "id", 1432873878224494594L)).forEach(System.out::println);
// SELECT id,customer_name,customer_password,customer_sex,customer_tel,customer_email,customer_address,create_time,update_time,is_deleted FROM customer WHERE is_deleted=0 AND (id > ?)
```

#### 7.1.5、ge

> 大于等于

```java
customerMapper.selectList(new QueryWrapper<Customer>().ge(true, "id", 1432873878224494594L)).forEach(System.out::println);
// SELECT id,customer_name,customer_password,customer_sex,customer_tel,customer_email,customer_address,create_time,update_time,is_deleted FROM customer WHERE is_deleted=0 AND (id >= ?)
```

#### 7.1.6、lt

> 小于

```java
customerMapper.selectList(new QueryWrapper<Customer>().lt(true, "id", 1432873878224494594L)).forEach(System.out::println);
// SELECT id,customer_name,customer_password,customer_sex,customer_tel,customer_email,customer_address,create_time,update_time,is_deleted FROM customer WHERE is_deleted=0 AND (id < ?)
```

#### 7.1.7、le

> 小于等于

```java
customerMapper.selectList(new QueryWrapper<Customer>().le(true, "id", 1432873878224494594L)).forEach(System.out::println);
// SELECT id,customer_name,customer_password,customer_sex,customer_tel,customer_email,customer_address,create_time,update_time,is_deleted FROM customer WHERE is_deleted=0 AND (id <= ?)
```

#### 7.1.8、between

> BETWEEN 值1 AND 值2

```java
customerMapper.selectList(new QueryWrapper<Customer>().between(true, "id", 1432873878224494593L, 1432873878224494594L)).forEach(System.out::println);
// SELECT id,customer_name,customer_password,customer_sex,customer_tel,customer_email,customer_address,create_time,update_time,is_deleted FROM customer WHERE is_deleted=0 AND (id BETWEEN ? AND ?)
```

#### 7.1.9、notbetween

> NOT BETWEEN 值1 AND 值2

```java
customerMapper.selectList(new QueryWrapper<Customer>().notBetween(true, "id", 1432873878224494593L, 1432873878224494594L)).forEach(System.out::println);
// SELECT id,customer_name,customer_password,customer_sex,customer_tel,customer_email,customer_address,create_time,update_time,is_deleted FROM customer WHERE is_deleted=0 AND (id NOT BETWEEN ? AND ?)
```

#### 7.1.10、like

> 模糊查询，自动在模糊查询值左右两侧添加%

```java
customerMapper.selectList(new QueryWrapper<Customer>().like(true, "id", "11")).forEach(System.out::println);
// Preparing: SELECT id,customer_name,customer_password,customer_sex,customer_tel,customer_email,customer_address,create_time,update_time,is_deleted FROM customer WHERE is_deleted=0 AND (id LIKE ?)
// Parameters: %11%(String)
```

#### 7.1.11、notlike

```java
customerMapper.selectList(new QueryWrapper<Customer>().notLike(true, "id", "11")).forEach(System.out::println);
// SELECT id,customer_name,customer_password,customer_sex,customer_tel,customer_email,customer_address,create_time,update_time,is_deleted FROM customer WHERE is_deleted=0 AND (id NOT LIKE ?)
// Parameters: %11%(String)
```

#### 7.1.12、likeLeft

> 在模糊查询值左侧添加%，即以指定值结尾的模糊查询

```java
customerMapper.selectList(new QueryWrapper<Customer>().likeLeft(true, "id", "11")).forEach(System.out::println);
// SELECT id,customer_name,customer_password,customer_sex,customer_tel,customer_email,customer_address,create_time,update_time,is_deleted FROM customer WHERE is_deleted=0 AND (id LIKE ?)
// Parameters: %11(String)
```

#### 7.1.13、likeRight

> 在模糊查询值右侧添加%，即以指定值开头的模糊查询

```java
customerMapper.selectList(new QueryWrapper<Customer>().likeRight(true, "id", "11")).forEach(System.out::println);
// SELECT id,customer_name,customer_password,customer_sex,customer_tel,customer_email,customer_address,create_time,update_time,is_deleted FROM customer WHERE is_deleted=0 AND (id LIKE ?)
// Parameters: 11%(String)
```

#### 7.1.14、isNull

> 判断字段为null

```java
customerMapper.selectList(new QueryWrapper<Customer>().isNull(true, "id")).forEach(System.out::println);
// SELECT id,customer_name,customer_password,customer_sex,customer_tel,customer_email,customer_address,create_time,update_time,is_deleted FROM customer WHERE is_deleted=0 AND (id IS NULL)
```

#### 7.1.15、isNotNull

> 判断字段不为null

```java
customerMapper.selectList(new QueryWrapper<Customer>().isNotNull(true, "id")).forEach(System.out::println);
// SELECT id,customer_name,customer_password,customer_sex,customer_tel,customer_email,customer_address,create_time,update_time,is_deleted FROM customer WHERE is_deleted=0 AND (id IS NOT NULL)
```

#### 7.1.16、in

> in (xx,xx,xx)

```java
customerMapper.selectList(new QueryWrapper<Customer>().in(true, "id", 1432873878224494594L, 1432873878224494595L, 1432873878224494596L)).forEach(System.out::println);
customerMapper.selectList(new QueryWrapper<Customer>().in(true, "id", Arrays.asList(1432873878224494594L, 1432873878224494595L, 1432873878224494596L))).forEach(System.out::println);
// SELECT id,customer_name,customer_password,customer_sex,customer_tel,customer_email,customer_address,create_time,update_time,is_deleted FROM customer WHERE is_deleted=0 AND (id IN (?,?,?))
```

#### 7.1.17、not in

> not in (xx,xx,xx)

```JAVA
customerMapper.selectList(new QueryWrapper<Customer>().notIn(true, "id", 1432873878224494594L, 1432873878224494595L, 1432873878224494596L)).forEach(System.out::println);
customerMapper.selectList(new QueryWrapper<Customer>().notIn(true, "id", Arrays.asList(1432873878224494594L, 1432873878224494595L, 1432873878224494596L))).forEach(System.out::println);
// SELECT id,customer_name,customer_password,customer_sex,customer_tel,customer_email,customer_address,create_time,update_time,is_deleted FROM customer WHERE is_deleted=0 AND (id NOT IN (?,?,?))
```

#### 7.1.18、inSql

> in子查询

```java
customerMapper.selectList(new QueryWrapper<Customer>().inSql(true, "id", "select id from customer")).forEach(System.out::println);
customerMapper.selectList(new QueryWrapper<Customer>().inSql(true, "id", "1,2,3,4,5,6")).forEach(System.out::println);
// SELECT id,customer_name,customer_password,customer_sex,customer_tel,customer_email,customer_address,create_time,update_time,is_deleted FROM customer WHERE is_deleted=0 AND (id IN (select id from customer))
```

#### 7.1.19、notInSql

```java
customerMapper.selectList(new QueryWrapper<Customer>().notInSql(true, "id","select id from customer")).forEach(System.out::println);
customerMapper.selectList(new QueryWrapper<Customer>().notInSql(true, "id", "1,2,3,4,5,6")).forEach(System.out::println);
// SELECT id,customer_name,customer_password,customer_sex,customer_tel,customer_email,customer_address,create_time,update_time,is_deleted FROM customer WHERE is_deleted=0 AND (id NOT IN (select id from customer))
```

#### 7.1.20、exists

> 子查询

```java
customerMapper.selectList(new QueryWrapper<Customer>().exists(true, "select id from customer where id = 1")).forEach(System.out::println);
// SELECT id,customer_name,customer_password,customer_sex,customer_tel,customer_email,customer_address,create_time,update_time,is_deleted FROM customer WHERE is_deleted=0 AND (EXISTS (select id from customer where id = 1))
```

#### 7.1.21、notExists

```java
customerMapper.selectList(new QueryWrapper<Customer>().notExists(true, "select id from customer where id = 1")).forEach(System.out::println);
// SELECT id,customer_name,customer_password,customer_sex,customer_tel,customer_email,customer_address,create_time,update_time,is_deleted FROM customer WHERE is_deleted=0 AND (NOT EXISTS (select id from customer where id = 1))
```

#### 7.1.22、orderBy

> 排序，可指定排序规则

```java
// orderBy(boolean condition, boolean isAsc, List<R> columns)
// condition：true/false，sql是否生效
// isAsc：true/false，是否升序排列
// columns：指定根据排序的列
customerMapper.selectList(new QueryWrapper<Customer>().orderBy(true, true, Arrays.asList("id", "customer_name"))).forEach(System.out::println);
// SELECT  id,customer_name,customer_password,customer_sex,customer_tel,customer_email,customer_address,create_time,update_time,is_deleted FROM customer WHERE is_deleted=0 ORDER BY id ASC,customer_name ASC
```

#### 7.1.23、orderByAsc

> 升序排序

```java
customerMapper.selectList(new QueryWrapper<Customer>().orderByAsc(true, Arrays.asList("id","customer_name"))).forEach(System.out::println);
// SELECT id,customer_name,customer_password,customer_sex,customer_tel,customer_email,customer_address,create_time,update_time,is_deleted FROM customer WHERE is_deleted=0 ORDER BY id ASC,customer_name ASC
```

#### 7.1.24、orderByDesc

> 降序排序

```java
customerMapper.selectList(new QueryWrapper<Customer>().orderByDesc(true, Arrays.asList("id", "customer_name"))).forEach(System.out::println);
// SELECT id,customer_name,customer_password,customer_sex,customer_tel,customer_email,customer_address,create_time,update_time,is_deleted FROM customer WHERE is_deleted=0 ORDER BY id DESC,customer_name DESC
```

#### 7.1.25、groupBy

> 分组查询

```java
customerMapper.selectList(new QueryWrapper<Customer>().select("id","customer_name").groupBy(true, Arrays.asList("id", "customer_name"))).forEach(System.out::println);
// SELECT id,customer_name FROM customer WHERE is_deleted=0 GROUP BY id,customer_name
```

#### 7.1.26、having

> 分组查询条件

```java
having(boolean condition, String sqlHaving, Object... params);
// condition：true/false，sql是否生效
// sqlHaving：分组查询条件sql
// 动态传入分组查询条件sql的值，使用方式在sqlHaving中使用{0},{1}...
customerMapper.selectList(new QueryWrapper<Customer>().select("id","customer_name").groupBy(true, Arrays.asList("id", "customer_name")).having("count(1) >= 0")).forEach(System.out::println);
customerMapper.selectList(new QueryWrapper<Customer>().select("id","customer_name").groupBy(true, Arrays.asList("id", "customer_name")).having("count(1) >= {0}",1)).forEach(System.out::println);
// SELECT id,customer_name FROM customer WHERE is_deleted=0 GROUP BY id,customer_name HAVING count(1) >= 0
```

#### 7.1.27、func

> func 方法(主要方便在出现if...else下调用不同方法能不断链)

```java
customerMapper.selectList(new QueryWrapper<Customer>().eq("id", 3L).func(true, customerQueryWrapper -> {
    if (true) {
        customerQueryWrapper.eq("id",1L);
    } else {
        customerQueryWrapper.eq("id",2L);
    }
})).forEach(System.out::println);
// SELECT id,customer_name,customer_password,customer_sex,customer_tel,customer_email,customer_address,create_time,update_time,is_deleted FROM customer WHERE is_deleted=0 AND (id = ? AND id = ?)
```

#### 7.1.28、or

> 使用or嵌套sql

```java
customerMapper.selectList(new QueryWrapper<Customer>().eq("id",1L).or(true, customerQueryWrapper -> {
    customerQueryWrapper.eq("id",2L);
})).forEach(System.out::println);
// SELECT id,customer_name,customer_password,customer_sex,customer_tel,customer_email,customer_address,create_time,update_time,is_deleted FROM customer WHERE is_deleted=0 AND (id = ? OR (id = ?))
```

#### 7.1.29、and

> 使用and嵌套sql，与func区分下

```java
customerMapper.selectList(new QueryWrapper<Customer>().eq("id", 1L).and(true, customerQueryWrapper -> {
    customerQueryWrapper.eq("id", 2L);
})).forEach(System.out::println);
// SELECT id,customer_name,customer_password,customer_sex,customer_tel,customer_email,customer_address,create_time,update_time,is_deleted FROM customer WHERE is_deleted=0 AND (id = ? AND (id = ?))
```

#### 7.1.30、nested

> 正常嵌套，不使用and或or

```java
customerMapper.selectList(new QueryWrapper<Customer>().eq("id", 1L).nested(true, customerQueryWrapper -> {
    customerQueryWrapper.eq("id", 2L).ge("id",3L);
})).forEach(System.out::println);
// SELECT id,customer_name,customer_password,customer_sex,customer_tel,customer_email,customer_address,create_time,update_time,is_deleted FROM customer WHERE is_deleted=0 AND (id = ? AND (id = ? AND id >= ?))
```

#### 7.1.31、apply

> sql拼接
>
> 该方法可用于数据库**函数** 动态入参的`params`对应前面`applySql`内部的`{index}`部分.这样是不会有sql注入风险的,反之会有!

```java
customerMapper.selectList(new QueryWrapper<Customer>().apply("id = {0} and customer_name = {1}", 1L, "zhongdongsheng")).forEach(System.out::println);
// SELECT id,customer_name,customer_password,customer_sex,customer_tel,customer_email,customer_address,create_time,update_time,is_deleted FROM customer WHERE is_deleted=0 AND (id = ? and customer_name = ?)
```

#### 7.1.32、last

> 无视优化规则直接拼接到 sql 的最后
>
> 只能调用一次,多次调用以最后一次为准 有sql注入的风险,请谨慎使用

```java
last("limit 1")
```

### 7.2、QueryWrapper

#### 7.2.1、select

> 指定查询的字段

```java
/**
     * Query.java
     * 过滤查询的字段信息(主键除外!)
     * <p>例1: 只要 java 字段名以 "test" 开头的             -> select(i -> i.getProperty().startsWith("test"))</p>
     * <p>例2: 只要 java 字段属性是 CharSequence 类型的     -> select(TableFieldInfo::isCharSequence)</p>
     * <p>例3: 只要 java 字段没有填充策略的                 -> select(i -> i.getFieldFill() == FieldFill.DEFAULT)</p>
     * <p>例4: 要全部字段                                   -> select(i -> true)</p>
     * <p>例5: 只要主键字段                                 -> select(i -> false)</p>
     *
     * @param predicate 过滤方式
     * @return children
     */
customerMapper.selectList(new QueryWrapper<Customer>().select(Customer.class, tableFieldInfo -> true).apply("id = {0} and customer_name = {1}", 1L, "zhongdongsheng")).forEach(System.out::println);
// SELECT id,customer_name,customer_password,customer_sex,customer_tel,customer_email,customer_address,create_time,update_time,is_deleted FROM customer WHERE is_deleted=0 AND (id = ? and customer_name = ?)

customerMapper.selectList(new QueryWrapper<Customer>().select("id","customer_name").apply("id = {0} and customer_name = {1}", 1L, "zhongdongsheng")).forEach(System.out::println);
// SELECT id,customer_name FROM customer WHERE is_deleted=0 AND (id = ? and customer_name = ?)
```



### 7.3、UpdateWrapper

#### 7.3.1、set

```java
customerMapper.update(null, new UpdateWrapper<Customer>().set("customer_name", "zhongdongsheng").set("customer_sex", "女").eq("id", 1L));
// UPDATE customer SET customer_name=?,customer_sex=? WHERE is_deleted=0 AND (id = ?)

customerMapper.update(null, new UpdateWrapper<Customer>().setSql("customer_name = 'zhongdongsheng'").set("customer_sex", "女").eq("id", 1L));
// UPDATE customer SET customer_name = 'zhongdongsheng',customer_sex=? WHERE is_deleted=0 AND (id = ?)
```

## 8、分页插件

### 8.1、配置分页插件

> 在自定义的配置类中，配置分页插件。只需要在mybatisplus提供的拦截器链中加入分页插件的拦截器类

```java
@Bean
public MybatisPlusInterceptor mybatisPlusInterceptor() {
    MybatisPlusInterceptor interceptor = new MybatisPlusInterceptor();
    interceptor.addInnerInterceptor(new PaginationInnerInterceptor(DbType.MYSQL));
    return interceptor;
}
```

### 8.2、编写测试类测试

```java
@Test
void testPaginationSelect() {
    Page<Customer> page = new Page<>(2,5);
    Page<Customer> customerPage = customerMapper.selectPage(page, Wrappers.<Customer>lambdaQuery().eq(Customer::getCustomerName,"zhouliang").orderByDesc(Customer::getId));
    customerPage.getRecords().forEach(System.out::println);
}
```

执行sql语句前，会先执行查询所有数量的sql。

执行sql语句后，会返回分页对象，该对象中的records属性就是封装的我们要查询的对象List集合。此外，分页对象中还会保存所有记录数以及当前返回的记录数

## 9、主键生成策略

### 9.1、Mybatis-plus默认的机制

| 主键生成方式       | 说明                                                         |
| ------------------ | ------------------------------------------------------------ |
| IdType.AUTO        | 主键自增，需在表结构设计时设置为主键为自增                   |
| IdType.NONE        | 无状态（测试发现，若有手动设置主键值，则使用手动设置的值，如果没有，则使用雪花算法IdType.ASSIGN_ID） |
| IdType.INPUT       | 插入前手动设置主键的值                                       |
| IdType.ASSIGN_ID   | 分配ID(主键类型为Number(Long和Integer)或String)(since 3.3.0),使用接口`IdentifierGenerator`的方法`nextId`(默认实现类为`DefaultIdentifierGenerator`雪花算法) |
| IdType.ASSIGN_UUID | 分配UUID,主键类型为String(since 3.3.0),使用接口`IdentifierGenerator`的方法`nextUUID`(默认default方法) |

### 9.2、自定义主键生成策略

#### 9.2.1、声明为Bean供Spring扫描注入

```java
@Slf4j
@Component
public class CustomIdGenerator implements IdentifierGenerator {

    @Override
    public String nextUUID(Object entity) {
        log.info("使用自定义主键生成策略ASSIGN_UUID");
        return "111";
    }

    @Override
    public Number nextId(Object entity) {
        log.info("使用自定义主键生成策略ASSIGN_ID");
        return 123L;
    }
}
```

#### 9.2.2、使用配置类

```java
/**
 * 自定义主键生成策略方式2，覆盖掉mybatis-plus中的默认策略
 */   
@Bean
public IdentifierGenerator identifierGenerator() {
    return new CustomIdGenerator();
}
```



#### 9.2.3、通过MybatisPlusPropertiesCustomizer自定义

```java
@Bean
public MybatisPlusPropertiesCustomizer plusPropertiesCustomizer() {
    return plusProperties -> plusProperties.getGlobalConfig().setIdentifierGenerator(new CustomIdGenerator());
}
```

## 10、内置CRUD接口

### 10.1、dao层（mapper层）

> 实现方式：自定义接口继承mp提供的BaseMapper接口。就可以通过实例化自定义接口调用内置的CRUD方法

#### 10.1.1、insert

```java
@Test
void testInsert() {
    Customer customer = new Customer().setCustomerName("zhongdongsheng").setCustomerPassword("123456").setCustomerSex("男").setCustomerTel("13260900000").setCustomerEmail("zhongds01@163.com").setCustomerAddress("Wuxi");
    int rows = customerMapper.insert(customer);
    System.out.println("insert rows = " + rows);
}
```

#### 10.1.2、delete

```java
@Test
void testDelete() {
    // 方式1、使用delete方法
    System.out.println("delete rows is " + customerMapper.delete(Wrappers.<Customer>lambdaQuery().eq(Customer::getId, 1432900627591094273L)));
    // 方式2、使用deleteById方法
    System.out.println("delete rows is " + customerMapper.deleteById(1432900627591094273L));
    // 方式3、使用deleteBatchIds方法批量删除
    System.out.println("delete rows is " + customerMapper.deleteBatchIds(Arrays.asList(1432900627591094273L, 1432900627591094274L)));
    // 方式4、使用deleteByMap删除
    Map<String, Object> map = new HashMap<>();
    map.put("id", 1432900627591094273L);
    map.put("customer_name", "zhouliang");
    System.out.println("delete rows is " + customerMapper.deleteByMap(map));
}
```

#### 10.1.3、update

>  **在使用UpdateWrapper条件构造器时，不会自动更新填充字段update_time字段**

```java
@Test
void testUpdate() {
    // 方式1、使用updateById方法
    Customer customer = new Customer().setCustomerName("zhongdongsheng").setCustomerAddress("Nanjing").setCustomerSex("男");
    // customer.setId(1432971951265148930L);
    System.out.println("update rows is " + customerMapper.updateById(customer));
    // updateWrapper不会自动更新update_time字段
    System.out.println("delete rows is " + customerMapper.update(null, Wrappers.<Customer>lambdaUpdate().eq(Customer::getId, 1432971951265148930L).set(Customer::getCustomerName, "zhouliang")));
}
```

#### 10.1.4、select

```java
@Test
void testSelect() {
    // 方式1、使用selectOne方法查询单条记录，如果返回多条，抛异常。
    Customer customer = customerMapper.selectOne(Wrappers.<Customer>lambdaQuery().eq(Customer::getId, 1432971951265148930L).like(Customer::getCustomerName, "dong"));
    System.out.println(customer);
    // 方式2、使用selectById方法查询单条记录。
    Customer customerOne = customerMapper.selectById(1432971951265148930L);
    System.out.println(customerOne);
    // 方式3、使用selectList方法查询多条记录。
    List<Customer> customers = customerMapper.selectList(Wrappers.<Customer>lambdaQuery().eq(Customer::getCustomerSex, "男").like(Customer::getCustomerName, "dong"));
    customers.forEach(System.out::println);
    // 方式4、使用selectBatchIds方法查询多条记录。
    List<Customer> customersByBatchIds = customerMapper.selectBatchIds(Arrays.asList(1432971951265148930L, 1432971951265148931L));
    customersByBatchIds.forEach(System.out::println);
    // 方式5、使用selectCount方法查询记录总数。
    Integer count = customerMapper.selectCount(Wrappers.<Customer>lambdaQuery().eq(Customer::getCustomerSex, "男").like(Customer::getCustomerName, "dong"));
    System.out.println("count = " + count);
    // 方式6、使用selectByMap方法查询多条记录。
    HashMap<String, Object> map = new HashMap<>();
    map.put("id", 1432971951265148930L);
    map.put("customer_name", "zhouliang");
    List<Customer> customersByMap = customerMapper.selectByMap(map);
    customersByMap.forEach(System.out::println);
    // 方式7、使用selectMaps方法查询记录总数,结果集以map形式封装，而不是实体类。
    List<Map<String, Object>> maps = customerMapper.selectMaps(Wrappers.<Customer>lambdaQuery().eq(Customer::getCustomerSex, "男").like(Customer::getCustomerName, "dong"));
    maps.forEach(System.out::println);
    // 方式8、使用selectObjs方法查询记录总数,指挥返回查询的第一列。
    List<Object> objects = customerMapper.selectObjs(Wrappers.<Customer>lambdaQuery().eq(Customer::getCustomerSex, "男").like(Customer::getCustomerName, "dong"));
    objects.forEach(System.out::println);
    // 方式9、使用selectPage方法分页查询。
    Page<Customer> page = new Page<>(1, 5);
    customerMapper.selectPage(page, Wrappers.<Customer>lambdaQuery().eq(Customer::getCustomerSex, "男").like(Customer::getCustomerName, "dong"));
    page.getRecords().forEach(System.out::println);
    // 方式10、使用selectMapsPage方法分页查询，结果集使用map形式保存。
    Page<Map<String,Object>> pageMap = new Page<>(1,5);
    customerMapper.selectMapsPage(pageMap, Wrappers.<Customer>lambdaQuery().eq(Customer::getCustomerSex, "男").like(Customer::getCustomerName, "dong"));
    pageMap.getRecords().forEach(System.out::println);
}
```

### 10.2、service层
