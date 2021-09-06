package com.zds.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Date;

/**
 * @author zhongdongsheng
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
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
