package com.zds.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.util.List;

/**
 * 学生和成绩类
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class StudentAndScore extends Student {
    private List<Score> scoreList;

    @Override
    public String toString() {
        return "StudentAndScore{" +
                "scoreList=" + scoreList +
                "} " + super.toString();
    }
}
