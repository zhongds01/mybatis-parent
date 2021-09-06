package com.zds.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * @author zhongdongsheng
 * @datetime 2021/9/6 22:27
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class TeacherAndStudent extends Teacher{
    /**
     * 学生和教师为多对多的关系，一个学生可以选择多个老师的课程，一个老师也可以教授多个学生
     */
    private List<Student> studentList;

    @Override
    public String toString() {
        return "TeacherAndStudent{" +
                "studentList=" + studentList +
                "} " + super.toString();
    }
}
