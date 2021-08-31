package com.zds;

import com.zds.mapper.CustomMapper;
import com.zds.entity.Custom;
import com.zds.util.SqlSessionUtil;
import org.apache.ibatis.session.SqlSession;
import org.junit.Test;

import static org.junit.Assert.assertTrue;

/**
 * CustomMapperTest
 */
public class CustomMapperTest {

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
}
