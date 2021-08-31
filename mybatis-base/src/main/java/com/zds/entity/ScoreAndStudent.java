package com.zds.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 学生和成绩类
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
