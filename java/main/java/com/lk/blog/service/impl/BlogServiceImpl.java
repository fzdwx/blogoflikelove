package com.lk.blog.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.lk.blog.entity.Blog;
import com.lk.blog.entity.User;
import com.lk.blog.entity.vo.BlogVo;
import com.lk.blog.mapper.BlogMapper;
import com.lk.blog.service.BlogService;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.List;

/**
 * <p>
 * 服务实现类
 * </p>
 *
 * @author likelove
 * @since 2020-09-04
 */
@Service
public class BlogServiceImpl extends ServiceImpl<BlogMapper, Blog> implements BlogService {

    /**
     * 根据博客条件查询对象 查询分页
     * 1.通过queryWrapper按照表中的sort和views字段排序
     * 2.png.按照条件查询对象查询
     * 3.png.使用baseWrapper返回
     *
     * @param pageParam 分页信息-当前多少页，每页多少条信息
     * @param blogVo    博客条件查询对象
     *
     * @return IPage<Blog>
     */
    @Override
    public IPage<Blog> selectPage(Page<Blog> pageParam, BlogVo blogVo) {
        //1.通过queryWrapper按照表中的sort和views字段排序
        QueryWrapper<Blog> blogQw = new QueryWrapper<>();
        blogQw.orderByAsc("sort");
        blogQw.orderByAsc("views");
        if (blogVo != null) {
            //2.png.按照条件查询对象查询
            String title = blogVo.getTitle(); //博客标题
            String authorName = blogVo.getAuthorName(); //博客作者
            Integer viewsStart = blogVo.getViewsStart();//博客访问量
            Integer viewsEnd = blogVo.getViewsEnd();
            String categoryName = blogVo.getCategoryName(); //博客分类名字
            if (title != null) {
                blogQw.like("title", title);
            }
            if (authorName != null) {
                blogQw.like("author_name", authorName);
            }
            if (categoryName != null) {
                blogQw.eq("category_name", categoryName);
            }
            if (!StringUtils.isEmpty(viewsStart)) {
                blogQw.ge("views", viewsStart);
            }
            if (!StringUtils.isEmpty(viewsEnd)) {
                blogQw.le("views", viewsEnd);
            }
        }
        return baseMapper.selectPage(pageParam, blogQw);
    }

    /**
     * 根据传入的数字，返回特点的条件博客排序列表
     * <br>
     * 1:根据浏览数升序排列<br>
     * 2:根据浏览数据降序排列<br>
     * 3:根据修改时间升序排列<br>
     * 4:根据修改时间降序排列<br>
     *
     * @param status integer
     *
     * @return 博客列表
     */
    @Override
    public List<Blog> getBlogListSort(Integer status) {
        QueryWrapper<Blog> query = new QueryWrapper<>();
        if (status == 1) {//1:根据浏览数升序排列
            query.orderByAsc("views");
        }
        else if (status == 2) {//2:根据浏览数据降序排列
            query.orderByDesc("views");
        }
        else if (status == 3) {//3:根据创建时间升序排列
            query.orderByAsc("create_by");
        }
        else if (status == 4) {//4:根据创建时间降序排列
            query.orderByDesc("create_by");
        }
        return baseMapper.selectList(query);
    }

    /**
     * 根据用户获取他所有的博客
     *
     * @param user 需要获取博客的用户
     *
     * @return list
     */
    @Override
    public List<Blog> getByAuthorId(User user) {
        QueryWrapper<Blog> query = new QueryWrapper<>();
        query.eq("author_id", user.getUid())
             //id主键，标题，浏览次数，点赞次数，作者uid，博客所属分类，创建时间
             .select("id","title","views","love","author_id","category_name","create_by");
        return baseMapper.selectList(query);
    }

    /**
     * 根据博客分类名字返回属于分类的博客
     *
     * @param categoryName 博客分类名字
     *
     * @return list
     */
    @Override
    public List<Blog> getByCategoryName(String categoryName) {
        QueryWrapper<Blog> query = new QueryWrapper<>();
        query.eq("category_name",categoryName);
        return baseMapper.selectList(query);
    }
}
