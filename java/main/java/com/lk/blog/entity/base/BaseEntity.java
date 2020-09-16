package com.lk.blog.entity.base;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.util.Date;

/**
 * <p>
 * base实体对象
 * </p>
 *
 * @author likeLove
 * @since 2020-09-04  12:14
 */
@Data
@EqualsAndHashCode (callSuper = false)
@Accessors (chain = true)
public class BaseEntity implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty (value = "自增ID")
    @TableId (value = "id", type = IdType.AUTO)
    private String id;

    @ApiModelProperty (value = "创建时间")
    @TableField (fill = FieldFill.INSERT)
   /* @JsonFormat (timezone = "GMT+8", pattern = "yyyy-MM-dd")*/
    private Date createBy;

    @ApiModelProperty (value = "更新时间")
    @TableField (fill = FieldFill.INSERT_UPDATE)
    /*@JsonFormat (timezone = "GMT+8", pattern = "yyyy-MM-dd")*/
    private Date updateBy;
}
