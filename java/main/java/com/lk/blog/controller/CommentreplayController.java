package com.lk.blog.controller;


import com.lk.blog.entity.Commentreplay;
import com.lk.blog.entity.back.Result;
import com.lk.blog.service.CommentreplayService;
import com.lk.blog.utils.ResultUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;

/**
 * <p>
 * 前端控制器
 * </p>
 *
 * @author likelove
 * @since 2020-09-11
 */
@RestController
@RequestMapping ("/blog/commentreplay")
public class CommentreplayController {

    @Autowired
    private CommentreplayService commentreplayService;
    @GetMapping("")
    public Result getList() {
        List<Commentreplay> list = commentreplayService.list();
        HashMap<String, Object> map = ResultUtils.objPutInMap("commentReplay", list);
        return ResultUtils.returnObjectDataIsNull("查询成功", "查询识别", map);
    }



}

