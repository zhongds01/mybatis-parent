package com.zds.mapper;

import com.zds.entity.ScoreAndStudent;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @author zhongdongsheng
 * @datetime 2021/9/6 22:27
 */
public interface ScoreMapper {
    /**
     * 根据学生id和课程id查询学生信息以及改课程的成绩
     *
     * @param sId 学生id
     * @param cId 课程id
     * @return ScoreAndStudent集合
     */
    List<ScoreAndStudent> getScoreWithStudentByStuIdAndCourseId(@Param("sId") long sId, @Param("cId") long cId);
}
