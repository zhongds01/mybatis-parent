package com.zds.mapper;

import com.zds.pojo.Custom;
import org.apache.ibatis.annotations.Param;

public interface CustomMapper {
    Custom selectOneCustomById(@Param("id") Long id);
}
