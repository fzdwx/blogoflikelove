package com.lk.blog.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.lk.blog.entity.BlogComment;
import com.lk.blog.mapper.BlogCommentMapper;
import com.lk.blog.service.BlogCommentService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * <p>
 * 服务实现类
 * </p>
 *
 * @author likelove
 * @since 2020-09-04
 */
@Service
public class BlogCommentServiceImpl extends ServiceImpl<BlogCommentMapper, BlogComment> implements BlogCommentService {

    /**
     * 根据博客id获取相关的评论
     *
     * @param topicId topicId 博客id
     * @param columns 需要查询的列
     *
     * @return List<BlogComment>
     */
    @Override
    public List<BlogComment> getBlogByTopicId(String topicId, String... columns) {
        QueryWrapper<BlogComment> query = new QueryWrapper<>();
        query.eq("topic_id", topicId)
             .orderByDesc("update_by")
             .select(columns);
        return baseMapper.selectList(query);
    }

    /**
     * 获取传入博客的评论
     *
     * @param blogComment 需要获取评论的博客
     *                    -   直接对文章发表评论，pid与replyid为空；
     *                    -   对一级评论进行回复，pid与replyid均为一级评论的id;
     *                    -   对楼中楼进行回复，pid为一级评论的id，replyid为你回复的评论的id
     *
     * @return map
     */
    @Override
    public Map<String, Object> getBlogReplay(BlogComment blogComment) {
        return null;
    }

    /**
     * 保存博客
     *
     * @param blogComment -   直接对文章发表评论，pid与replyid为空；
     *                    -   对一级评论进行回复，pid与replyid均为一级评论的id;
     *                    -   对楼中楼进行回复，pid为一级评论的id，replyid为你回复的评论的id
     *
     * @return
     */
    @Override
    public Boolean saveComment(BlogComment blogComment) {
        return null;
    }

}
