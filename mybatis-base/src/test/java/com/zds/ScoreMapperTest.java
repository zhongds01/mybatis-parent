package com.zds;

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
