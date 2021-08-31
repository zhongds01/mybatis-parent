package com.zds;

import com.zds.mapper.StudentMapper;
import com.zds.entity.Student;
import com.zds.entity.StudentAndScore;
import com.zds.entity.StudentAndTeacher;
import com.zds.util.SqlSessionUtil;
import org.apache.ibatis.session.SqlSession;
import org.junit.Test;

import java.util.Date;
import java.util.List;

public class StudentMapperTest {
    /**
     * 插入学生的信息
     */
    @Test
    public void testInsertStudent() {
        SqlSession sqlSession = SqlSessionUtil.getSqlSession();
        StudentMapper mapper = sqlSession.getMapper(StudentMapper.class);
        Student student = new Student();
        student.setId(1415300753928499202L);
        student.setSName("zhongdongsheng");
        student.setSBirthday(new Date());
        student.setSSex("男");
        Integer rows = mapper.insertStudent(student);
        System.out.println(rows);
        sqlSession.close();
    }

    /**
     * 根据学生的id删除学生的信息
     */
    @Test
    public void testDeleteStudentById() {
        SqlSession sqlSession = SqlSessionUtil.getSqlSession();
        StudentMapper mapper = sqlSession.getMapper(StudentMapper.class);
        Integer integer = mapper.deleteStudentById(1415300753928499203L);
        System.out.println(integer);
        sqlSession.close();
    }

    /**
     * 根据学生的id批量删除学生的信息
     */
    @Test
    public void testBatchDeleteStudentByIds() {
        SqlSession sqlSession = SqlSessionUtil.getSqlSession();
//        List<Long> ids = new ArrayList();
//        ids.add(1415300753928499203L);
//        ids.add(1415300753928499202L);
        StudentMapper mapper = sqlSession.getMapper(StudentMapper.class);
        Integer integer = mapper.batchDeleteStudentByIds(new Long[]{1415300753928499202L, 1415300753928499203L});
        System.out.println(integer);
        sqlSession.close();
    }

    /**
     * 根据学生的id更新学生的信息
     */
    @Test
    public void testUpdateStudentById() {
        SqlSession sqlSession = SqlSessionUtil.getSqlSession();
        StudentMapper mapper = sqlSession.getMapper(StudentMapper.class);
        Student student = new Student();
        student.setId(1415300753928499203L);
        student.setSName("lidongyun");
        student.setSBirthday(new Date());
        student.setSSex("女");
        Integer rows = mapper.updateStudent(student);
        System.out.println(rows);
        sqlSession.close();
    }

    /**
     * 根据学生的id获取学生信息
     */
    @Test
    public void testSelectStudentById() {
        SqlSession sqlSession = SqlSessionUtil.getSqlSession();
        StudentMapper mapper = sqlSession.getMapper(StudentMapper.class);
        Student student = mapper.selectStudentById(1L);
        System.out.println(student);
        sqlSession.close();
    }

    /**
     * 获取所有的学生信息
     */
    @Test
    public void testSelectStudents() {
        SqlSession sqlSession = SqlSessionUtil.getSqlSession();
        StudentMapper mapper = sqlSession.getMapper(StudentMapper.class);
        List<Student> students = mapper.selectStudents();
        students.forEach(System.out::println);
        sqlSession.close();
    }

    /**
     * 获取所有的学生信息（包含该学生所学课程的教师信息,多对多案例）
     */
    @Test
    public void testGetStudentWithTeacher() {
        SqlSession sqlSession = SqlSessionUtil.getSqlSession();
        StudentMapper mapper = sqlSession.getMapper(StudentMapper.class);
        List<StudentAndTeacher> students = mapper.getStudentsWithTeachers();
        students.forEach(System.out::println);
        sqlSession.close();
    }

    /**
     * 获取所有的学生信息以及该学生的所有课程成绩（一对多）
     */
    @Test
    public void testGetStudentWithScore() {
        SqlSession sqlSession = SqlSessionUtil.getSqlSession();
        StudentMapper mapper = sqlSession.getMapper(StudentMapper.class);
        List<StudentAndScore> students = mapper.getStudentWithScore();
        students.forEach(System.out::println);
        sqlSession.close();
    }

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
}
