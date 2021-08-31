package com.zds.service.impl;

import com.zds.base.BaseServiceImpl;
import com.zds.entity.Custom;
import com.zds.mapper.CustomMapper;
import com.zds.service.ICustomService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

/**
 * @author zhouliang
 * @since 2021-8-31下午 9:19
 */
@Slf4j
@Service
@AllArgsConstructor
public class CustomServiceImpl extends BaseServiceImpl<CustomMapper, Custom> implements ICustomService {

}
