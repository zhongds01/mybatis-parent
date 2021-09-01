package com.zds;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.zds.entity.Customer;
import com.zds.mapper.CustomerMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.HashMap;

@SpringBootTest
class MybatisPlusApplicationTests {
    @Autowired
    @Qualifier(value = "customerMapper")
    private CustomerMapper customerMapper;

    @Test
    void testInsert() {
        Customer customer = new Customer().setCustomerName("zhouliang").setCustomerPassword("123456").setCustomerSex("男").setCustomerTel("13260900000").setCustomerEmail("zhongds01@163.com").setCustomerAddress("Wuxi");
        int rows = customerMapper.insert(customer);
        System.out.println("insert rows = " + rows);
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
    void testUpdate() {
        customerMapper.update(null, new UpdateWrapper<Customer>().set("customer_name", "zhongdongsheng").set("customer_sex", "女").eq("id", 1L));
        customerMapper.update(null, new UpdateWrapper<Customer>().setSql("customer_name = 'zhongdongsheng'").set("customer_sex", "女").eq("id", 1L));
    }


}
