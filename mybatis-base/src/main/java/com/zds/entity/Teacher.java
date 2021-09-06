package com.zds.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author zhongdongsheng
 * @datetime 2021/9/6 22:27
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Teacher {
    private Long id;
    private String tName;
}
