package com.lk.blog.entity;


import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableLogic;
import com.lk.blog.entity.base.BaseEntity;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;
import java.util.Date;

/**
 * <p>
 * 用户状态
 * </p>
 *
 * @author likelove
 * @since 2020-09-04
 */
@Data
@EqualsAndHashCode (callSuper = true)
@ApiModel (value = "User", description = "用户状态")
public class User extends BaseEntity implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty (value = "用户编号")
    private String uid;

    @ApiModelProperty (value = "用户名")
    private String username;

    @ApiModelProperty (value = "密码")
    private String password;

    @ApiModelProperty (value = "用户头像")
    private String avatar;

    @ApiModelProperty (value = "登录时间")
    private Date loginDate;

    @TableField ("is_online")
    @ApiModelProperty ("是否在线 1（true）在线， 0（false）离线")
    private Boolean online;

    @ApiModelProperty (value = "逻辑删除 1（true）已删除， 0（false）未删除")
    @TableField ("is_deleted")
    @TableLogic
    private Boolean deleted;


}
