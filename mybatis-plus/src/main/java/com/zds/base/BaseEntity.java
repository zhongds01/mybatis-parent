package com.zds.base;

import com.baomidou.mybatisplus.annotation.*;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

/**
 * @author zhouliang
 * @since 2021-8-31下午 9:37
 */
@Data
public class BaseEntity {
    @TableId(
            value = "id",
            type = IdType.ASSIGN_ID
    )
    private Long id;

    @DateTimeFormat(
            pattern = "yyyy-MM-dd HH:mm:ss"
    )
    @TableField(fill = FieldFill.INSERT)
    private Date createTime;

    @DateTimeFormat(
            pattern = "yyyy-MM-dd HH:mm:ss"
    )
    @TableField(fill = FieldFill.INSERT_UPDATE)
    private Date updateTime;

    @TableField(fill = FieldFill.INSERT)
    @TableLogic()
    private Integer isDeleted;

    @Version
    @TableField(fill = FieldFill.INSERT)
    private Integer version;
}
