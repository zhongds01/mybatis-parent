package com.zds.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Student {
    private Long id;
    private String sName;
    private Date sBirthday;
    private String sSex;
}
