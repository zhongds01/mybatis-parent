package com.zds.mapper;

import com.zds.pojo.Custom;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Service;

import java.util.List;

public interface CustomMapper {
    List<Custom> selectOneCustomById(@Param("id") Long id);

    int insertCustom(@Param("custom") Custom custom);

    int updateCustomById(@Param("custom") Custom custom);

    List<Custom> selectCustom();
}
