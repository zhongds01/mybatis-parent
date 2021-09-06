package com.zds.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Date;

/**
 * @author zhongdongsheng
 * @datetime 2021/9/1 09:50
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Customer implements Serializable {
    private static final long serialVersionUID = 9157528864358633663L;
    private Long id;
    private String customerName;
    private String customerPassword;
    private String customerSex;
    private String customerTel;
    private String customerEmail;
    private String customerAddress;
    private Date createTime;
    private Date updateTime;
    private Integer isDeleted;
    private Integer version;
}
