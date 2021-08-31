package com.zds.entity;

import com.zds.base.BaseEntity;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Customer extends BaseEntity implements Serializable {
    private static final long serialVersionUID = 9157528864358633663L;
    private String customerName;
    private String customerPassword;
    private String customerSex;
    private String customerTel;
    private String customerEmail;
    private String customerAddress;
}
