package com.zds.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * 学生和成绩类
 *
 * @author zhongdongsheng
 * @datetime 2021/9/6 22:27
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
