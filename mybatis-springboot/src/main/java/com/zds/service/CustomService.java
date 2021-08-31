package com.zds.service;

import com.zds.entity.Custom;

import java.util.List;

public interface CustomService {
    List<Custom> selectOneCustomById(Long id);

    int insertCustom(Custom custom);

    int updateCustomById(Custom custom);

    List<Custom> selectOneCustom();
}
