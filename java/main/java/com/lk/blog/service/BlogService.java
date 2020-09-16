package com.lk.blog.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.lk.blog.entity.Blog;
import com.lk.blog.entity.User;
import com.lk.blog.entity.vo.BlogVo;

import java.util.List;

/**
 * <p>
 * 服务类
 * </p>
 *
 * @author likelove
 * @since 2020-09-04
 */
public interface BlogService extends IService<Blog> {
    /**
     * 根据博客条件查询对象 查询分页
     *
     * @param pageParam 分页信息-当前多少页，每页多少条信息
     * @param blogVo    博客条件查询对象
     *
     * @return IPage<Blog>
     */
    IPage<Blog> selectPage(Page<Blog> pageParam, BlogVo blogVo);

    /**
     * 根据传入的数字，返回特点的条件博客排序列表
     * <br>
     * 1:根据浏览数升序排列 <br>
     * 2:根据浏览数据降序排列<br>
     * 3:根据修改时间升序排列<br>
     * 4:根据修改时间降序排列<br>
     *
     * @param status integer
     *
     * @return 博客列表
     */
    List<Blog> getBlogListSort(Integer status);

    /**
     * 根据用户获取他所有的博客
     *
     * @param user 需要获取博客的用户
     *
     * @return list
     */
    List<Blog> getByAuthorId(User user);

    /**
     * 根据博客分类名字返回属于分类的博客
     *
     * @param categoryName 博客分类名字
     *
     * @return list
     */
    List<Blog> getByCategoryName(String categoryName);
}
