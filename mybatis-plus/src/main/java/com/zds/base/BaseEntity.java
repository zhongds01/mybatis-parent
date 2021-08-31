package com.zds.base;

import com.baomidou.mybatisplus.annotation.*;
import lombok.Data;
import lombok.experimental.Accessors;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

/**
 * @author zhouliang
 * @since 2021-8-31下午 9:37
 */
@Data
@Accessors(chain = true)
public class BaseEntity {

    @TableId(
            value = "id",
            type = IdType.ASSIGN_ID
    )
    private Long id;

    @DateTimeFormat(
            pattern = "yyyy-MM-dd HH:mm:ss"
    )
    private Date createTime;

    @DateTimeFormat(
            pattern = "yyyy-MM-dd HH:mm:ss"
    )
    private Date updateTime;

    @TableField(fill = FieldFill.INSERT)
    @TableLogic()
    private Integer isDeleted;
}
