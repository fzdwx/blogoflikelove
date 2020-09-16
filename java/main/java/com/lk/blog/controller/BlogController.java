package com.lk.blog.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.lk.blog.entity.Blog;
import com.lk.blog.entity.User;
import com.lk.blog.entity.back.Result;
import com.lk.blog.entity.vo.BlogVo;
import com.lk.blog.service.*;
import com.lk.blog.utils.JWTUtils;
import com.lk.blog.utils.MarkdownUtils;
import com.lk.blog.utils.ResultUtils;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.List;

/**
 * <p>
 * blog前端控制器
 * </p>
 *
 * @author likelove
 * @since 2020-09-04
 */
@RestController
@RequestMapping ("/blog")
@Api (tags = "博客管理")
@CrossOrigin
@Slf4j
public class BlogController {

    @Resource
    private BlogService blogService;//博客主类
    @Resource
    private BlogCategoryService blogCategoryService;//博客分类
    @Resource
    private BlogCommentService blogCommentService;//博客评论

    @Resource
    private CommentreplayService commentreplayService; //博客回复信息

    @GetMapping ("hello")
    public String goHello() {
        return "hello";
    }

    /**
     * 根据博客分类返回对应的博客
     * @param categoryName 博客分类名字
     * @return Result
     */
    @ApiOperation (value = "根据博客分类返回对应的博客")
    @GetMapping ("/Category/{categoryName}")
    public Result getBlogByCategoryName(@ApiParam ("博客分类名字") @PathVariable String categoryName) {
        log.info("==========BlogController====getBlogByCategoryName:categoryName:" + categoryName);
        List<Blog> blogList = blogService.getByCategoryName(categoryName);
        HashMap<String, Object> data = ResultUtils.objPutInMap("blogList", blogList);
        return Result.ok().setData(data).setMessage("获取成功");
    }


    /**
     * 根据博客名查找博客
     *
     * @param blogTitle 需要查询的博客的标题
     *
     * @return result
     */
    @GetMapping ("/searchBlog/{blogTitle}")
    @ApiOperation (value = "根据博客名搜索博客")
    public Result searchBlogByTitle(@ApiParam ("需要搜索的博客") @PathVariable String blogTitle) {
        log.info("==========BlogController====searchBlogByTitle:blogTitle:" + blogTitle);
        QueryWrapper<Blog> blogQueryWrapper = new QueryWrapper<>();
        QueryWrapper<Blog> title = blogQueryWrapper.like("title", blogTitle);
        List<Blog> blogList = blogService.list(title);
        for (Blog blog : blogList) {//markdown转化
            blog.setContent(MarkdownUtils.markdownToHtml(blog.getContent()));
        }
        HashMap<String, Object> data = ResultUtils.objPutInMap("blogList", blogList);
        return ResultUtils.returnObjectDataIsNull("查询成功", "查询失败", data);
    }

    /**
     * 修改博客点赞数量，默认加一
     *
     * @param status 为1是加，其他是减
     * @param blogId 需要修改点赞数量的博客
     *
     * @return result
     */
    @PutMapping ("/{blogId}/{status}")
    @ApiOperation (value = "修改当前博客点赞数量")
    public Result loveBlog(@ApiParam ("点赞是加还是减") @PathVariable Integer status,
                           @ApiParam ("需要修改点赞数量的博客") @PathVariable String blogId) {
        log.info("==========BlogController====loveBlog:blogid:" + blogId + "----status:" + status);
        Blog blog = blogService.getById(blogId);
        Integer loveNum = blog.getLove();
        if (status == 1) {
            blog.setLove(loveNum + 1);
        } else {
            blog.setLove(loveNum - 1);
        }
        boolean b = blogService.updateById(blog);
        return ResultUtils.returnBooleanDataIsNull(b, "修改成功", "修改失败");
    }

    /**
     * 博客分页查询
     * 1.定义一个pageParam，封装从前台传入的page，limit
     * 2.png.调用自定义的selectPage，封装pageParam，博客条件查询对象
     * 3.png.返回查询的数据
     *
     * @param page   当前页数
     * @param limit  每页多少条数据
     * @param blogVo 博客条件查询对象
     *
     * @return Result
     */
    @GetMapping ("/{page}/{limit}")
    @ApiOperation (value = "博客分页查询")
    public Result pageList(@ApiParam ("当前页数") @PathVariable long page,
                           @ApiParam ("每页多少条数据") @PathVariable long limit,
                          /* @RequestBody*/ @ApiParam ("博客条件查询对象") BlogVo blogVo) {
        log.info("==========BlogController====pageList:page:" + page + "---limit:" + limit + "---BlogVo" + blogVo);
        Page<Blog> pageParam = new Page<>(page, limit);
        IPage<Blog> iPage = blogService.selectPage(pageParam, blogVo);
        List<Blog> blogList = iPage.getRecords();
        for (Blog blog : blogList) {//markdown转化
            blog.setContent(MarkdownUtils.markdownToHtml(blog.getContent()));
        }
        long current = iPage.getCurrent();//当前页码数
        long pages = iPage.getPages();//总页数
        long total = iPage.getTotal();//总数据量
        long size = iPage.getSize();//每页多少条数据量
        HashMap<String, Object> data = ResultUtils.objPutInMap("blogList", blogList);
        data.put("current", current);
        data.put("pages", pages);
        data.put("total", total);
        data.put("size", size);
        return ResultUtils.returnObjectDataIsNull("查询成功", "查询失败", data);
    }

    /**
     * 获取所有博客列表
     *
     * @return Result
     */
    @GetMapping ("")
    @ApiOperation (value = "返回所有博客")
    public Result getList() {
        log.info("==========BlogController===getList");
        List<Blog> blogList = blogService.list();
        for (Blog blog : blogList) {//markdown转化
            blog.setContent(MarkdownUtils.markdownToHtml(blog.getContent()));
        }
        HashMap<String, Object> data = ResultUtils.objPutInMap("blogList", blogList);
        return ResultUtils.returnObjectDataIsNull("查询成功", "查询失败", data);
    }

    /**
     * 获取博客排序列表
     * - 1:根据浏览数升序排列<br>
     * - 2:根据浏览数据降序排列<br>
     * - 3:根据修改时间升序排列<br>
     * - 4:根据修改时间降序排列<br>
     *
     * @return Result
     */
    @GetMapping ("/{status}")
    @ApiOperation (value = "所有博客排序状态")
    public Result getListSort(@ApiParam ("排序状态") @PathVariable Integer status) {
        log.info("==========BlogController====getListSort:status:" + status);
        List<Blog> blogList = blogService.getBlogListSort(status);
        for (Blog blog : blogList) {//markdown转化
            blog.setContent(MarkdownUtils.markdownToHtml(blog.getContent()));
        }
        HashMap<String, Object> data = ResultUtils.objPutInMap("blogList", blogList);
        return ResultUtils.returnObjectDataIsNull("排序查询成功", "排序查询成功", data);
    }

    /**
     * 阅读博客
     * 1.获取这个博客
     * 2.需要查询相关的评论
     * 3.博客的浏览数要+1,写进数据库
     *
     * @param id 用户读取的博客
     *
     * @return Result
     */
    @GetMapping ("/read/{id}")
    @ApiOperation (value = "阅读博客")
    public Result read(@PathVariable Integer id) {
        log.info("==========BlogController====read:id:" + id);
        //1.获取这个博客
        Blog byId = blogService.getById(id);
        //2.读取数据库中相关的评论
        //3.博客的浏览数要+1
        byId.setViews(byId.getViews() + 1);
        //更新到数据库
        blogService.updateById(byId);
        //4.返回数据
        HashMap<String, Object> data = ResultUtils.objPutInMap("blog", byId);//保存博客
        return ResultUtils.returnObjectDataIsNull("查询成功", "查询失败，数据库中可能没有这个博客", data);
    }

    @Autowired
    private UserService userService;

    /**
     * 保存博客
     *
     * @param blog 需要保存的博客
     *
     * @return Result
     */
    @PostMapping ("/save")
    @ApiOperation (value = "保存博客")
    public Result save(@RequestBody @ApiParam ("需要保存的博客") Blog blog, HttpServletRequest request) {
        //当前登录的用户
        User user = JWTUtils.getTokenInfo(request);
        //当前用户的数据
        if (user != null) {
            User byUid = userService.getByUid(user);
            //保存给提交进来的博客
            blog.setAuthorId(byUid.getUid());
            blog.setAuthorName(byUid.getUsername());
            blog.setAuthorAvatar(byUid.getAvatar());
        }
        log.info("======BlogController: save" + blog);
        boolean status = blogService.save(blog);
        return ResultUtils.returnBooleanDataIsNull(status, "保存成功", "保存失败，blog-save");
    }

    /**
     * 修改博客的时候 博客数据回显
     *
     * @param id 需要回显的博客的id
     *
     * @return Result
     */
    @GetMapping ("/edit/{id}")
    @ApiOperation (value = "博客数据回显")
    public Result echoBlog(@PathVariable Integer id) {
        log.info("======BlogController: echoBlog:id" + id);
        Blog byId = blogService.getById(id);
        HashMap<String, Object> data = ResultUtils.objPutInMap("blog", byId);
        return ResultUtils.returnObjectDataIsNull("查询成功", "查询失败，数据库中可能没有这个博客", data);
    }

    /**
     * 修改博客内容
     *
     * @param blog 需要修改的博客
     *
     * @return Result
     */
    @ApiOperation (value = "编辑博客")
    @PutMapping ("/edit")
    public Result edit(@RequestBody Blog blog) {
        log.info("======BlogController: edit:blog" + blog);
        boolean status = blogService.updateById(blog);
        return ResultUtils.returnBooleanDataIsNull(status, "修改成功", "修改失败，数据库中可能没有这个博客");
    }
}

