package com.lk.blog.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.lk.blog.entity.BlogComment;

import java.util.List;
import java.util.Map;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author likelove
 * @since 2020-09-04
 */
public interface BlogCommentService extends IService<BlogComment> {
    /**
     * 根据博客id获取相关的评论
     * @param topicId topicId 博客id
     * @param columns 需要查询的列
     * @return List<BlogComment>
     */
    List<BlogComment> getBlogByTopicId(String topicId, String... columns);

    /**
     * 获取传入博客的评论
     * @param blogComment 需要获取评论的博客
     * @return  map
     */
    Map<String, Object> getBlogReplay(BlogComment blogComment);

    /**
     * 保存博客
     * @param blogComment
     * @return
     */
    Boolean saveComment(BlogComment blogComment);

}
