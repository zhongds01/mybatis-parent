package com.zds.mapper;

import com.zds.entity.Custom;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface CustomMapper {
    List<Custom> selectOneCustomById(@Param("id") Long id);

    int insertCustom(@Param("custom") Custom custom);
}
