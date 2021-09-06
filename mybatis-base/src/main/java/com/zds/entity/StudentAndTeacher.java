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
public class StudentAndTeacher extends Student{
    private List<Teacher> teacherList;
}
