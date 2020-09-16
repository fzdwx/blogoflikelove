package com.lk.blog.entity.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;

/**
 * 博客条件查询对象
 *
 * @author likeLove
 * @time 2020-09-05  12:16
 */
@Data
@ApiModel (value = "Blog条件查询对象", description = "博客条件查询对象")
public class BlogVo implements Serializable {
    @ApiModelProperty (value = "博客标题")
    private String title;
    @ApiModelProperty (value = "作者名")
    private String authorName;
    @ApiModelProperty (value = "问题分类名称")
    private String categoryName;
    @ApiModelProperty (value = "最小浏览量")
    private Integer viewsStart;
    @ApiModelProperty (value = "最大浏览量")
    private Integer viewsEnd;
}
