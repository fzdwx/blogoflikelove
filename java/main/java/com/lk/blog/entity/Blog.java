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
 *  博客实体对象
 * </p>
 *
 * @author likelove
 * @since 2020-09-04
 */
@Data
@EqualsAndHashCode (callSuper = true)
@ApiModel (value = "Blog", description = "博客的主要内容")
public class Blog extends BaseEntity implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty (value = "博客id")
    private String bid;

    @ApiModelProperty (value = "博客标题")
    private String title;

    @ApiModelProperty (value = "博客内容")
    private String content;

    @ApiModelProperty (value = "排序 越小越优先")
    private Integer sort;

    @ApiModelProperty (value = "浏览量")
    private Integer views;
    //authorId = uid
    @ApiModelProperty (value = "作者id")
    private String authorId;

    @ApiModelProperty (value = "作者名")
    private String authorName;

    @ApiModelProperty (value = "作者头像")
    private String authorAvatar;
    @ApiModelProperty (value = "喜欢的人数")
    private Integer love;

    @ApiModelProperty (value = "问题分类id")
    private Integer categoryId;

    @ApiModelProperty (value = "问题分类名称")
    private String categoryName;

    @ApiModelProperty (value = "逻辑删除 1（true）已删除， 0（false）未删除")
    @TableField ("is_deleted")
    @TableLogic
    private Boolean deleted;


}
