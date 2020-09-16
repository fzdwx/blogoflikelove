package com.lk.blog.entity;


import com.baomidou.mybatisplus.annotation.TableName;
import com.lk.blog.entity.base.BaseEntity;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;

/**
 * <p>
 *  博客分类
 * </p>
 *
 * @author likelove
 * @since 2020-09-04
 */
@Data
@EqualsAndHashCode (callSuper = true)
@TableName ("blog_category")
@ApiModel (value="BlogCategory", description = "博客的全部分类")
public class BlogCategory extends BaseEntity implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty (value = "博客分类")
    private String category;


}
