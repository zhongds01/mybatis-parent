package com.zds;

import com.zds.mapper.CustomMapper;
import com.zds.entity.Custom;
import com.zds.util.SqlSessionUtil;
import org.apache.ibatis.session.SqlSession;
import org.junit.Test;

public class CustomMapperTest {
    @Test
    public void testSelectOneCustomById() {
        SqlSession sqlSession = SqlSessionUtil.getSqlSession();
        CustomMapper mapper = sqlSession.getMapper(CustomMapper.class);
        Custom custom = mapper.selectOneCustomById(1415300753928499202L);
        System.out.println(custom);
        sqlSession.close();
    }
}
