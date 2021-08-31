package com.zds.mapper;

import com.zds.entity.Custom;
import org.apache.ibatis.annotations.Param;
import org.mybatis.spring.annotation.MapperScan;

import java.util.List;

//@Mapper
public interface CustomMapper {
    List<Custom> selectOneCustomById(@Param("id") Long id);

    int insertCustom(@Param("custom") Custom custom);

    int updateCustomById(@Param("custom") Custom custom);

    List<Custom> selectCustom();
}
