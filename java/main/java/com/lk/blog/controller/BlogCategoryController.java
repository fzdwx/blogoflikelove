package com.lk.blog.controller;


import com.lk.blog.entity.BlogCategory;
import com.lk.blog.entity.back.Result;
import com.lk.blog.service.BlogCategoryService;
import com.lk.blog.utils.ResultUtils;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;

/**
 * <p>
 *  博客分类控制
 * </p>
 *
 * @author likelove
 * @since 2020-09-04
 */
@RestController
@RequestMapping("/blog/category")
@CrossOrigin
@Api (tags = "博客分类管理")
@Slf4j
public class BlogCategoryController {
    @Autowired
    BlogCategoryService blogCategoryService;

    /**
     * 查看当前有哪些博客分类
     * @return result
     */
    @GetMapping("/list")
    @ApiOperation (value = "查询所有博客分类")
    public Result getAllBlogCategoryList() {
        log.info("==========BlogCategoryController--- getAllBlogCategoryList");
        List<BlogCategory> blogCategoryList = blogCategoryService.list();
        HashMap<String, Object> data = ResultUtils.objPutInMap("blogCategoryList", blogCategoryList);
        return Result.ok().setData(data).setMessage("获取成功");
    }
}

