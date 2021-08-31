package com.zds.mapper;

import com.zds.entity.ScoreAndStudent;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface ScoreMapper {
    List<ScoreAndStudent> getScoreWithStudentByStuIdAndCourseId(@Param("sId") long sId, @Param("cId") long cId);
}
