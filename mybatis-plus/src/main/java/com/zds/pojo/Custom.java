package com.zds.pojo;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Custom implements Serializable {
    private static final long serialVersionUID = 9157528864358633663L;
    @TableId("custom_id")
    private Long id;
    private String customName;
    private String customPwd;
    private String customSex;
    private String customTel;
    private String customEmail;
    private String customAddress;
    @TableField(fill = FieldFill.INSERT)
    private LocalDateTime createDate;
    @TableField(fill = FieldFill.INSERT_UPDATE)
    private LocalDateTime modifyDate;
    private String status;
}
