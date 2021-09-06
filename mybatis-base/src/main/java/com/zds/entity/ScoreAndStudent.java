package com.zds.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 学生和成绩类
 *
 * @author zhongdongsheng
 * @datetime 2021/9/6 22:27
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ScoreAndStudent extends Score {
    private Student student;

    @Override
    public String toString() {
        return "ScoreAndStudent{" +
                "student=" + student +
                "} " + super.toString();
    }
}
