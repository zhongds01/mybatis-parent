package com.zds;

import com.zds.mapper.TeacherMapper;
import com.zds.pojo.Teacher;
import com.zds.pojo.TeacherAndStudent;
import com.zds.util.SqlSessionUtil;
import org.apache.ibatis.session.SqlSession;
import org.junit.Test;

import java.util.List;

public class TeacherMapperTest {
    /**
     * 测试根据学生id查询该学生所学课程的教师信息（包含该学生自身信息）
     */
    @Test
    public void testGetTeacherWithStudentsByStuId() {
        SqlSession sqlSession = SqlSessionUtil.getSqlSession();
        TeacherMapper mapper = sqlSession.getMapper(TeacherMapper.class);
        List<TeacherAndStudent> teachers = mapper.getTeacherWithStudentByStuId(1L);
        teachers.forEach(System.out::println);
        sqlSession.close();
    }

    /**
     * 查询所有教师的信息（包含该教师所教授的所有学生信息）
     */
    @Test
    public void testGetTeacherWithStudents() {
        SqlSession sqlSession = SqlSessionUtil.getSqlSession();
        TeacherMapper mapper = sqlSession.getMapper(TeacherMapper.class);
        List<TeacherAndStudent> teachers = mapper.getTeacherWithStudent();
        teachers.forEach(System.out::println);
        sqlSession.close();
    }
}
