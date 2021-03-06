package com.zds.mapper;

import com.zds.entity.TeacherAndStudent;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @author zhongdongsheng
 * @datetime 2021/9/6 22:27
 */
public interface TeacherMapper {
    /**
     * 根据学生id获取教师的信息,已经教师教授的学生信息（教师和学生为多对多，但是学生id已经指定，所以学生的信息只有一个）
     *
     * @param id 学生id
     * @return 教师信息集合
     */
    List<TeacherAndStudent> getTeacherWithStudentByStuId(@Param("id") Long id);

    /**
     * 查询每位教师的信息，以及该教师教授的学生信息
     *
     * @return 教师信息集合
     */
    List<TeacherAndStudent> getTeacherWithStudent();
}
