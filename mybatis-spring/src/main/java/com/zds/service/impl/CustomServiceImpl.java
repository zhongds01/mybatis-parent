package com.zds.service.impl;

import com.zds.mapper.CustomMapper;
import com.zds.entity.Custom;
import com.zds.service.CustomService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service(value = "customServiceImpl")
public class CustomServiceImpl implements CustomService {
    @Autowired
    CustomMapper customMapper;

    @Override
    public List<Custom> selectOneCustomById(Long id) {
        return customMapper.selectOneCustomById(id);
    }


    /**
     * 开启事务,验证该方法执行过程中报错是否会自动回滚
     * 如果不使用事务注解开启事务管理，该方法会成功插入数据
     * 如果使用注解开启事务，则会自动回滚之前的插入操作
     *
     * @param custom custom
     * @return int
     */
    @Transactional
    @Override
    public int insertCustom(Custom custom) {
        int rows = customMapper.insertCustom(custom);
        int i = 1 / 0;
        return rows;
    }

    @Override
    public int updateCustomById(Custom custom) {
        return customMapper.updateCustomById(custom);
    }

    @Override
    public List<Custom> selectOneCustom() {
        return customMapper.selectCustom();
    }
}
