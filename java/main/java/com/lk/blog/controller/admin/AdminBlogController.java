package com.lk.blog.controller.admin;

import com.lk.blog.entity.back.Result;
import com.lk.blog.service.BlogService;
import com.lk.blog.utils.ResultUtils;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

/**
 * @author likeLove
 * @time 2020-09-05  8:57
 */
@RestController
@RequestMapping ("/admin")
@Api (tags = "博客后台管理")
@Slf4j
@CrossOrigin
public class AdminBlogController {
    @Resource
    private BlogService blogService;//博客主类

    //根据主键删除博客
    @DeleteMapping ("/blog/{id}")
    @ApiOperation (value = "根据主键删除博客")
    public Result removeBlogById(@PathVariable String id) {
        boolean b = blogService.removeById(id);
        return ResultUtils.returnBooleanDataIsNull(b, "删除成功", "删除失败");
    }


        
}
