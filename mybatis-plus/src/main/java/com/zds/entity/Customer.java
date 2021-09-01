package com.zds.entity;

import com.zds.base.BaseEntity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import java.io.Serializable;

/**
 * @author zhongdongsheng
 * @datetime 2021/9/1 09:50
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Accessors(chain = true)
public class Customer extends BaseEntity implements Serializable {
    private static final long serialVersionUID = 9157528864358633663L;
    private String customerName;
    private String customerPassword;
    private String customerSex;
    private String customerTel;
    private String customerEmail;
    private String customerAddress;
}
