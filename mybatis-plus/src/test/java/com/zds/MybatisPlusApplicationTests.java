package com.zds;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.zds.entity.Customer;
import com.zds.mapper.CustomerMapper;
import com.zds.service.ICustomerService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ApplicationContext;
import org.springframework.transaction.annotation.Transactional;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@SpringBootTest
class MybatisPlusApplicationTests {
    @Autowired
    @Qualifier(value = "customerMapper")
    private CustomerMapper customerMapper;

    @Autowired
    @Qualifier("customerServiceImpl")
    private ICustomerService customerService;

    @Autowired
    private ApplicationContext applicationContext;

    @Test
    void testInsert() {
        Customer customer = new Customer().setCustomerName("zhongdongsheng").setCustomerPassword("123456").setCustomerSex("男").setCustomerTel("13260900000").setCustomerEmail("zhongds01@163.com").setCustomerAddress("Wuxi");
        int rows = customerMapper.insert(customer);
        System.out.println("insert rows = " + rows);
    }

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

    @Test
    void testUpdate() {
        // 方式1、使用updateById方法
        Customer customer = new Customer().setCustomerName("zhongdongsheng").setCustomerAddress("Nanjing").setCustomerSex("男");
        customer.setId(1433405065368035330L);
        System.out.println("update rows is " + customerMapper.updateById(customer));
        // updateWrapper不会自动更新update_time字段
        // System.out.println("update rows is " + customerMapper.update(null, Wrappers.<Customer>lambdaUpdate().eq(Customer::getId, 1432971951265148930L).set(Customer::getCustomerName, "zhouliang")));
    }

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
        Page<Map<String, Object>> pageMap = new Page<>(1, 5);
        customerMapper.selectMapsPage(pageMap, Wrappers.<Customer>lambdaQuery().eq(Customer::getCustomerSex, "男").like(Customer::getCustomerName, "dong"));
        pageMap.getRecords().forEach(System.out::println);
    }

    @Test
    void testSelectById() {
        Customer customer = customerMapper.selectById(1415300753928499202L);
        HashMap<String, Object> map = new HashMap<>();
        map.put("customer_name", "zhouliang");
        map.put("customer_sex", "男");
        map.put("customer_password", null);
//        customerMapper.selectList(new QueryWrapper<Customer>().allEq(false, (k, v) -> k.equals("customer_name"), map, true)).forEach(System.out::println);
//        customerMapper.selectList(new QueryWrapper<Customer>().eq(true, "id", 1432873878224494594L)).forEach(System.out::println);
//        customerMapper.selectList(new QueryWrapper<Customer>().ne(true, "id", 1432873878224494594L)).forEach(System.out::println);
//        customerMapper.selectList(new QueryWrapper<Customer>().gt(true, "id", 1432873878224494594L)).forEach(System.out::println);
//        customerMapper.selectList(new QueryWrapper<Customer>().ge(true, "id", 1432873878224494594L)).forEach(System.out::println);
//        customerMapper.selectList(new QueryWrapper<Customer>().lt(true, "id", 1432873878224494594L)).forEach(System.out::println);
//        customerMapper.selectList(new QueryWrapper<Customer>().le(true, "id", 1432873878224494594L)).forEach(System.out::println);
//        customerMapper.selectList(new QueryWrapper<Customer>().between(true, "id", 1432873878224494593L, 1432873878224494594L)).forEach(System.out::println);
//        customerMapper.selectList(new QueryWrapper<Customer>().notBetween(true, "id", "1432873878224494593L", 1432873878224494594L)).forEach(System.out::println);
//        customerMapper.selectList(new QueryWrapper<Customer>().like(true, "id", "11")).forEach(System.out::println);
//        customerMapper.selectList(new QueryWrapper<Customer>().notLike(true, "id", "11")).forEach(System.out::println);
//        customerMapper.selectList(new QueryWrapper<Customer>().likeLeft(true, "id", "11")).forEach(System.out::println);
//        customerMapper.selectList(new QueryWrapper<Customer>().likeRight(true, "id", "11")).forEach(System.out::println);
//        customerMapper.selectList(new QueryWrapper<Customer>().isNull(true, "id")).forEach(System.out::println);
//        customerMapper.selectList(new QueryWrapper<Customer>().isNotNull(true, "id")).forEach(System.out::println);
//        customerMapper.selectList(new QueryWrapper<Customer>().in(true, "id", 1432873878224494594L, 1432873878224494595L, 1432873878224494596L)).forEach(System.out::println);
//        customerMapper.selectList(new QueryWrapper<Customer>().notIn(true, "id", Arrays.asList(1432873878224494594L, 1432873878224494595L, 1432873878224494596L))).forEach(System.out::println);
//        customerMapper.selectList(new QueryWrapper<Customer>().inSql(true, "id","select id from customer")).forEach(System.out::println);
//        customerMapper.selectList(new QueryWrapper<Customer>().inSql(true, "id", "1,2,3,4,5,6")).forEach(System.out::println);
//        customerMapper.selectList(new QueryWrapper<Customer>().notInSql(true, "id", "select id from customer")).forEach(System.out::println);
//        customerMapper.selectList(new QueryWrapper<Customer>().notInSql(true, "id", "1,2,3,4,5,6")).forEach(System.out::println);
//        customerMapper.selectList(new QueryWrapper<Customer>().exists(true, "select id from customer where id = 1")).forEach(System.out::println);
//        customerMapper.selectList(new QueryWrapper<Customer>().notExists(true, "select id from customer where id = 1")).forEach(System.out::println);
//        customerMapper.selectList(new QueryWrapper<Customer>().allEq(true, map, true).orderBy(true,true, Arrays.asList("id","customer_name"))).forEach(System.out::println);
//        customerMapper.selectList(new QueryWrapper<Customer>().orderBy(true, true, Arrays.asList("id", "customer_name"))).forEach(System.out::println);
//        customerMapper.selectList(new QueryWrapper<Customer>().orderByAsc(true, Arrays.asList("id", "customer_name"))).forEach(System.out::println);
//        customerMapper.selectList(new QueryWrapper<Customer>().orderByDesc(true, Arrays.asList("id", "customer_name"))).forEach(System.out::println);
//        customerMapper.selectList(new QueryWrapper<Customer>().select("id", "customer_name").groupBy(true, Arrays.asList("id", "customer_name"))).forEach(System.out::println);
//        customerMapper.selectList(new QueryWrapper<Customer>().select("id", "customer_name").groupBy(true, Arrays.asList("id", "customer_name")).having("count(1) >= {0}", 1)).forEach(System.out::println);
//        customerMapper.selectList(new QueryWrapper<Customer>().orderByDesc(true, Arrays.asList("id", "customer_name"))).forEach(System.out::println);
//        customerMapper.selectList(new QueryWrapper<Customer>().eq("id", 3L).func(true, customerQueryWrapper -> {
//            if (true) {
//                customerQueryWrapper.eq("id", 1L);
//            } else {
//                customerQueryWrapper.eq("id", 2L);
//            }
//        })).forEach(System.out::println);
//        customerMapper.selectList(new QueryWrapper<Customer>().eq("id", 1L).or(true, customerQueryWrapper -> {
//            customerQueryWrapper.eq("id", 2L);
//        })).forEach(System.out::println);
//        customerMapper.selectList(new QueryWrapper<Customer>().eq("id", 1L).and(true, customerQueryWrapper -> {
//            customerQueryWrapper.eq("id", 2L);
//        })).forEach(System.out::println);
//        customerMapper.selectList(new QueryWrapper<Customer>().eq("id", 1L).nested(true, customerQueryWrapper -> {
//            customerQueryWrapper.eq("id", 2L).ge("id",3L);
//        })).forEach(System.out::println);
//        customerMapper.selectList(new QueryWrapper<Customer>().apply("id = {0} and customer_name = {1}", 1L, "zhongdongsheng")).forEach(System.out::println);
        customerMapper.selectList(new QueryWrapper<Customer>().select(Customer.class, tableFieldInfo -> true).apply("id = {0} and customer_name = {1}", 1L, "zhongdongsheng")).forEach(System.out::println);

    }

    @Test
    void testTransaction() {
        Customer customer = new Customer().setCustomerName("zhongdongsheng").setCustomerPassword("123456").setCustomerSex("男").setCustomerTel("13260900000").setCustomerEmail("zhongds01@163.com").setCustomerAddress("Wuxi");
        customerMapper.insert(customer);
        int i = 1 / 0;
    }

    @Test
    void testPaginationSelect() {
        Page<Customer> page = new Page<>(2, 5);
        Page<Customer> customerPage = customerMapper.selectPage(page, Wrappers.<Customer>lambdaQuery().eq(Customer::getCustomerName, "zhouliang").orderByDesc(Customer::getId));
        customerPage.getRecords().forEach(System.out::println);
    }

    @Test
    @Transactional
    void testInsertService() {
        // 1、使用save()插入数据
        Customer customer = new Customer().setCustomerName("tutu").setCustomerPassword("123456").setCustomerSex("男").setCustomerTel("13266000000").setCustomerEmail("tutuxiaotaoqi@163.com");
        boolean isSuccess = customerService.save(customer);
        System.out.println("insert isSuccess = " + isSuccess);

        // 2、使用saveBatch()批量插入数据。
        Customer customer1 = new Customer().setCustomerName("taotao").setCustomerPassword("123456").setCustomerSex("女").setCustomerTel("13266000000").setCustomerEmail("tutuxiaotaoqi@163.com");
        customer.setId(null);
        List<Customer> customers = Arrays.asList(customer, customer1);
        // customers.size()为批量插入的记录数，不传也行，默认值为1000，但是建议传
        // TIPS:测试工程中发现，批量插入的数据中包含之前插入的customer对象时，会报错，原因在于第一次插入时，会自动生成主键id，第一次插入成功后，customer中已经有了id，因此不会重新生成，所以批量插入时就会报主键冲突。
        System.out.println("insert isSuccess = " + customerService.saveBatch(customers, customers.size()));

        // 3、使用saveOrUpdate()插入更新数据，如果插入的数据存在，就更新，不存在就插入。
        // 测试插入之前插入的customer1对象
        customerService.saveOrUpdate(customer1);
        // 测试插入之前插入一个新对象，设置id为null
        customer1.setId(null);
        customerService.saveOrUpdate(customer1);

        // 判断存在不存在的依据就是@TableId主键修饰的字段是否存在
        // 4、如果saveOrUpdate()中还传入了UpdateWrapper 条件构造器，默认执行update(customer1,wrapper)操作，update()返回false，否则执行saveOrUpdate(customer1)
        customerService.saveOrUpdate(customer1, Wrappers.<Customer>lambdaUpdate().set(Customer::getCustomerName, "dszhong").eq(Customer::getId, 1L));


        // 5、saveOrUpdateBatch批量插入更新操作


    }

    @Test
    @Transactional
    void testServiceUpdate() {
        Customer customer = new Customer().setCustomerName("tutu").setCustomerSex("男").setCustomerTel("13266000000").setCustomerEmail("tutuxiaotaoqi@163.com");
        Customer customer1 = new Customer().setCustomerName("taotao").setCustomerPassword("123123").setCustomerSex("女").setCustomerTel("13266000000").setCustomerEmail("tutuxiaotaoqi@163.com");
        customer.setId(1111L);
        customer1.setId(1432971951265148930L);
//        System.out.println("saveOrUpdateBatch isSuccess = " + customerService.saveOrUpdateBatch(Arrays.asList(customer, customer1), 333));
//        boolean result = customerService.update(customer, Wrappers.<Customer>lambdaUpdate().eq(Customer::getId, 1432972277472821249L));
        customerService.updateById(customer1);
//        customerService.updateBatchById(Arrays.asList(customer, customer1));
//        customerService.update(Wrappers.<Customer>lambdaUpdate().set(Customer::getCustomerName, "zhongdongsheng").set(Customer::getCustomerAddress, "Nanjing").eq(Customer::getId, 1433250516598562817L));
//        customerService.update(customer, Wrappers.<Customer>lambdaUpdate().eq(Customer::getId, 1432971951265148930L));
    }

    @Test
    @Transactional
    void testServiceDelete() {
//        System.out.println("delete rows = " + customerService.removeById(1433252388331868161L));
//        System.out.println("delete rows = " + customerService.removeByIds(Arrays.asList(1433252388331868161L, 1433252388331868162L)));
//        HashMap<String, Object> map = new HashMap<>();
//        map.put("customer_name","zhouliang");
//        map.put("id",1433250516598562817L);
//        map.put("customer_sex","女");
//        System.out.println("delete rows = " + customerService.removeByMap(map));
        System.out.println("delete rows = " + customerService.remove(Wrappers.<Customer>lambdaQuery().eq(Customer::getCustomerName, "zhouliang").eq(Customer::getId, 1433250516598562817L).eq(Customer::getCustomerSex, "女")));
    }

    @Test
    void testServiceSelect() {
//        List<Customer> list = customerService.list();
//        List<Customer> customers = customerService.listByIds(Arrays.asList(1432971951265148930L, 1432971971968184322L));
//        List<Customer> list = customerService.list(Wrappers.<Customer>lambdaQuery().select(Customer::getId, Customer::getCustomerName).eq(Customer::getId, 1432971951265148930L));
//        List<Map<String, Object>> maps = customerService.listMaps();
//        List<Map<String, Object>> maps = customerService.listMaps(Wrappers.<Customer>lambdaQuery().in(Customer::getId, Arrays.asList(1432971951265148930L, 1432971971968184322L)));
//        HashMap<String, Object> map = new HashMap<>();
//        map.put("customer_name", "zhouliang");
//        map.put("id", 1433250516598562817L);
//        map.put("customer_sex", "女");
//        List<Customer> customers = customerService.listByMap(map);
//        customerService.listObjs().forEach(System.out::println);
        customerService.listObjs(Wrappers.<Customer>lambdaQuery().select(Customer::getCustomerName, Customer::getCustomerPassword)).forEach(System.out::println);
    }

    @Test
    void testServiceGet() {
        Customer customer = customerService.getById(1433250516598562817L);
        Customer one = customerService.getOne(Wrappers.<Customer>lambdaQuery().eq(Customer::getCustomerName, "zhouliang").last("limit 1"));
        Customer two = customerService.getOne(Wrappers.<Customer>lambdaQuery().eq(Customer::getCustomerName, "zhouliang"), false);
        Map<String, Object> map = customerService.getMap(Wrappers.<Customer>lambdaQuery().eq(Customer::getCustomerName, "zhouliang"));
    }

    @Test
    void testServicePage() {
        Page<Customer> page = new Page<>(1, 5);
        customerService.page(page).getRecords().forEach(System.out::println);
        Page<Map<String,Object>> mapPage = new Page<>(1,5);
        customerService.pageMaps(mapPage).getRecords().forEach(System.out::println);
    }

    @Test
    void testServiceChain() {

    }

    @Test
    void testVersion() {
        Customer customer = customerService.getById(1433405065368035330L);
        customer.setCustomerName("zhouliang");
        System.out.println("before update version :" + customer.getVersion());
        customerService.updateById(customer);
        System.out.println("after update version :" + customer.getVersion());
    }

    @Test
    void testSelfSelectById() {
        Customer customer = customerService.selectOneCustomById(1433405065368035330L);
        System.out.println(customer);
    }

    @Test
    void testListCustomer() {
        List<Customer> customer = customerService.listCustomer();
        customer.forEach(System.out::println);
    }
}
