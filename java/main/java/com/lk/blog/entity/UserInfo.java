package com.lk.blog.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableLogic;
import com.lk.blog.entity.base.BaseEntity;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;

/**
 * <p>
 *  用戶资料
 * </p>
 *
 * @author likelove
 * @since 2020-09-04
 */
@Data
@EqualsAndHashCode (callSuper = true)
@ApiModel (value="UserInfo", description="用户信息")
public class UserInfo extends BaseEntity implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty (value = "用户id")
    private String uid;

    @ApiModelProperty(value = "用户昵称")
    private String nickname;

    @ApiModelProperty(value = "真实姓名")
    private String realname;

    @ApiModelProperty(value = "QQ")
    private String qq;

    @ApiModelProperty(value = "WeChat")
    private String wechat;

    @ApiModelProperty(value = "邮箱")
    private String email;

    @ApiModelProperty(value = "手机")
    private String phone;

    @ApiModelProperty(value = "工作")
    private String work;

    @ApiModelProperty(value = "地址")
    private String address;

    @ApiModelProperty(value = "爱好")
    private String hobby;

    @ApiModelProperty(value = "自我介绍")
    private String intro;

    @ApiModelProperty(value = "逻辑删除 1（true）已删除， 0（false）未删除")
    @TableField ("is_deleted")
    @TableLogic
    private Boolean deleted;


}
