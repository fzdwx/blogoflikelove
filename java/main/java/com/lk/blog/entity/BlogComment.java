package com.lk.blog.entity;


import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableLogic;
import com.baomidou.mybatisplus.annotation.TableName;
import com.lk.blog.entity.base.BaseEntity;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;

/**
 * <p>
 * 博客评论
 * </p>
 *
 * @author likelove
 * @since 2020-09-04
 */
@Data
@EqualsAndHashCode (callSuper = true)
@TableName ("blog_comment")
@ApiModel (value = "BlogComment", description = "博客评论")
public class BlogComment extends BaseEntity implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty (value = "评论唯一id")
    private String commentId;

    @ApiModelProperty (value = "1博客 2留言")
    private Integer topicCategory;

    @ApiModelProperty (value = "评论主题id")
    private String topicId;

    @ApiModelProperty (value = "评论者id")
    private String userId;

    @ApiModelProperty (value = "评论者昵称")
    private String userName;

    @ApiModelProperty (value = "评论者头像")
    private String userAvatar;

    @ApiModelProperty (value = "评论内容")
    private String content;

    @ApiModelProperty (value = "喜欢的人数")
    private Integer love;

    @ApiModelProperty (value = "回复的哪一条评论就保存哪一条评论的id")
    private Integer replyid;

  /*  @ApiModelProperty (value = "博客预览图片")
    private String commentPhoto;*/
    @ApiModelProperty (value = "一级评论的id")
    private Integer pid;
    @ApiModelProperty (value = "逻辑删除 1（true）已删除， 0（false）未删除")
    @TableField ("is_deleted")
    @TableLogic
    private Boolean deleted;


}
