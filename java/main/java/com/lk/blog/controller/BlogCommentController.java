package com.lk.blog.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.lk.blog.entity.BlogComment;
import com.lk.blog.entity.User;
import com.lk.blog.entity.back.Result;
import com.lk.blog.service.BlogCommentService;
import com.lk.blog.service.UserService;
import com.lk.blog.utils.JWTUtils;
import com.lk.blog.utils.ResultUtils;
import io.swagger.annotations.ApiParam;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.List;

/**
 * <p>
 * 前端控制器
 * </p>
 *
 * @author likelove
 * @since 2020-09-04
 */
@RestController
@RequestMapping ("/blog/comment")
@CrossOrigin
@Slf4j
public class BlogCommentController {
    @Autowired
    private BlogCommentService blogCommentService;

    /**
     * 获取留言
     *
     * @return result
     */
    @GetMapping ("")
    public Result getMessages() {
        log.info("==========BlogCommentService--- getMessages:");
        QueryWrapper<BlogComment> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("topic_category", 2);
        List<BlogComment> blogMessages = blogCommentService.list(queryWrapper);
        HashMap<String, Object> data = ResultUtils.objPutInMap("blogComments", blogMessages);
        return ResultUtils.returnObjectDataIsNull("查询留言成功", "查询留言失败", data);
    }

    /**
     * 根据博客id获取评论
     *
     * @param blogid
     *
     * @return
     */
    @GetMapping ("/{blogid}")
    public Result getComments(@ApiParam ("需要获取评论的博客的id") @PathVariable Integer blogid) {
        log.info("==========BlogCommentService--- getComments:" + blogid);
        QueryWrapper<BlogComment> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("topic_category", 1)//1.表示博客，2表示留言
                    .eq("topic_id", blogid)//根据博客的id查找对应的博客
                    .orderByDesc("create_by");
        List<BlogComment> blogComments = blogCommentService.list(queryWrapper);
        HashMap<String, Object> data = ResultUtils.objPutInMap("blogComments", blogComments);
        return ResultUtils.returnObjectDataIsNull("查询评论成功", "查询评论失败", data);
    }
    @Autowired
    private UserService userService;
    /**
     * 保存评论
     *
     * @param blogComment 需要保存的评论
     *
     * @return result
     */
    @PostMapping ("/save")
    public Result saveComment(@ApiParam ("需要保存博客的评论") @RequestBody BlogComment blogComment, HttpServletRequest request) {
        log.info("==========BlogCommentService--- saveComment:" + blogComment);
        //把这个评论和用户绑定
        User clientUser = JWTUtils.getTokenInfo(request);
        User user = userService.getByUid(clientUser);
        if (user != null) {
            blogComment.setUserAvatar(user.getAvatar());
            blogComment.setUserId(user.getUid());
            blogComment.setUserName(user.getUsername());
        }
        boolean save = blogCommentService.save(blogComment);
        return ResultUtils.returnBooleanDataIsNull(save, "保存成功", "保存失败");
    }

    /**
     * 修改博客评论点赞数量，默认加一
     *
     * @param blogCommentId 需要修改点赞数量的博客
     * @param status        为1是加，其他是减
     *
     * @return result
     */
    @PutMapping ("/{blogCommentId}/{status}")
    public Result updateLoveNum(@ApiParam ("需要修改点赞人数评论") @PathVariable Integer blogCommentId,
                                @ApiParam ("是加还是减") @PathVariable Integer status) {
        log.info("==========BlogCommentService--- updateLoveNum:" + blogCommentId);
        BlogComment comment = blogCommentService.getById(blogCommentId);
        if (status == 1) {
            comment.setLove(comment.getLove() + 1);
        } else {
            comment.setLove(comment.getLove() - 1);
        }
        boolean b = blogCommentService.updateById(comment);
        return ResultUtils.returnBooleanDataIsNull(b, "修改成功", "修改失败");
    }

}

