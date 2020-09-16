package com.lk.blog.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.lk.blog.entity.base.BaseEntity;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * <p>
 *
 * </p>
 *
 * @author likelove
 * @since 2020-09-11
 */
@Data
@EqualsAndHashCode(callSuper = true)
@TableName("blog_commentreplay")
@ApiModel(value="Commentreplay对象", description="")
public class Commentreplay extends BaseEntity {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "被评论的评论的id")
    private Integer beCommented;

    @ApiModelProperty(value = "评论内容")
    private String comment;

    @ApiModelProperty(value = "评论者名字")
    private String userName;

    @ApiModelProperty(value = "评论者头像")
    private String userAvatar;

    @ApiModelProperty(value = "被评论的人的名字")
    private String beuserName;

}
