package com.zds;

import com.zds.entity.Customer;
import com.zds.mapper.CustomerMapper;
import com.zds.util.SqlSessionUtil;
import org.apache.ibatis.session.SqlSession;
import org.junit.Test;

public class CustomerMapperTest {
    @Test
    public void testSelectOneCustomById() {
        SqlSession sqlSession = SqlSessionUtil.getSqlSession();
        CustomerMapper mapper = sqlSession.getMapper(CustomerMapper.class);
        Customer customer = mapper.selectOneCustomerById(1415300753928499202L);
        System.out.println(customer);
        sqlSession.close();
    }
}
