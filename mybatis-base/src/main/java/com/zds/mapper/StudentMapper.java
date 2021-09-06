package com.zds.mapper;

import com.zds.entity.Student;
import com.zds.entity.StudentAndScore;
import com.zds.entity.StudentAndTeacher;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @author zhongdongsheng
 * @datetime 2021/9/6 22:27
 */
public interface StudentMapper {
    /**
     * 插入学生信息
     *
     * @param student 学生信息
     * @return 影响的记录条数
     */
    Integer insertStudent(Student student);

    /**
     * 根据id查询学生信息
     *
     * @param id 学生id
     * @return 学生信息
     */
    Student selectStudentById(Long id);

    /**
     * 查询所有学生信息
     *
     * @return 学生集合
     */
    List<Student> selectStudents();

    /**
     * 根据学生id删除学生信息
     *
     * @param id 学生id
     * @return 记录条数
     */
    Integer deleteStudentById(Long id);

//    Integer batchDeleteStudentByIds(@Param("ids") List<Long> ids);

    /**
     * 根据学生id批量删除学生信息
     *
     * @param ids 学生id集合
     * @return 记录条数
     */
    Integer batchDeleteStudentByIds(@Param("ids") Long[] ids);

    /**
     * 更新学生信息
     *
     * @param student 学生信息
     * @return 记录条数
     */
    Integer updateStudent(Student student);

    /**
     * 查询所有的学生以及对应的教师信息
     *
     * @return StudentAndTeacher集合
     */
    List<StudentAndTeacher> getStudentsWithTeachers();

    /**
     * 查询所有的学生以及成绩信息
     *
     * @return StudentAndScore集合
     */
    List<StudentAndScore> getStudentWithScore();

    /**
     * 根据id查询学生以及成绩信息
     *
     * @param id 学生id
     * @return StudentAndScore集合
     */
    List<StudentAndScore> getStudentWithScoreById(@Param("id") long id);
}
