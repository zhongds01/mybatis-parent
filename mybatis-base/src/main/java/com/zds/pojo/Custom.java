package com.zds.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Custom implements Serializable {
    private static final long serialVersionUID = 9157528864358633663L;
    private Long id;
    private String customName;
    private String customPwd;
    private String customSex;
    private String customTel;
    private String customEmail;
    private String customAddress;
    private Date createDate;
    private Date modifyDate;
    private String status;
}
