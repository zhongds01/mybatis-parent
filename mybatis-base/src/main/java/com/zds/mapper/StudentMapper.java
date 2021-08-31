package com.zds.mapper;

import com.zds.entity.Student;
import com.zds.entity.StudentAndScore;
import com.zds.entity.StudentAndTeacher;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface StudentMapper {
    Integer insertStudent(Student student);

    Student selectStudentById(Long id);

    List<Student> selectStudents();

    Integer deleteStudentById(Long id);

//    Integer batchDeleteStudentByIds(@Param("ids") List<Long> ids);

    Integer batchDeleteStudentByIds(@Param("ids") Long[] ids);

    Integer updateStudent(Student student);

    List<StudentAndTeacher> getStudentsWithTeachers();

    List<StudentAndScore> getStudentWithScore();

    List<StudentAndScore> getStudentWithScoreById(@Param("id") long id);
}
