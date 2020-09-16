import request from '@/network/request'


//所有关于博客的api
export default {
    /**
     * 定义获取博客分页方法
     *
     * @param page 当前页数
     * @param limit 每页多少条数据
     * @param blogVo 博客条件查询对象
     */
    getBlogByPage(page, limit, blogVo) {
        return request({
            url: '/blog/'+page+'/'+limit,
            method: 'get',
            params:blogVo,
        })
    },
    //根据博客分类名字返回博客
    getBlogByCategoryName(categoryName) {
        return request({
            url: '/blog/Category/' + categoryName,
            method: 'get'
        })
    },
    //获取所有博客分类
    getAllBlogCategory() {
        return request({
            url: '/blog/category/list',
            method: 'get'
        })
    },
    //获取留言
    getMessages() {
        return request({
            url: '/blog/comment',
            method: 'GET',
        });
    },
    //根据博客标题搜索博客
    searchBlogByTitle(blogTitle) {
        return request({
            url: 'blog/searchBlog/' + blogTitle,
            method: 'get'
        })
    },
    //修改博客点赞数量
    updateBlogLoveNum(blogId, status) {
        return request({
            url: '/blog/' + blogId + '/' + status,
            method: 'put',
            /* data:blog,*/
        })
    },
    //修改博客评论点赞数量
    updateCommentLoveNum(blogCommentId, status) {
        return request({
            url: '/blog/comment/' + blogCommentId + '/' + status,
            method: 'put'
        })
    },

    //获取评论
    getComments(blogId) {
        return request({
            url: '/blog/comment/' + blogId,
            method: 'GET',
        });
    },
    //保存评论
    saveComments(blogComment) {
        return request({
            url: '/blog/comment/save',
            method: 'POST',
            data: blogComment
        })
    },
    //获取博客排序
    getBlogListSort(status) {
        return request({
            url: '/blog/' + status,
            method: 'get'
        })
    },
    //博客修改
    editMyBlog(blog) {
        return request({
            method: 'put',
            url: '/blog/edit',
            data: blog
        })
    },
    //博客数据回显
    getEditBlog(id) {
        return request({
            url: '/blog/edit/' + id
        });
    },
    //获取博客列表
    getBlogList() {
        return request({
            url: '/blog',
            method: 'get'
        })
    },
    //阅读博客
    read(id) {
        return request({
            url: '/blog/read/' + id,
            method: 'get'
        })
    },
    saveMyBlog(blog) {
        return request({
            url: '/blog/save',
            method: 'post',
            data: blog,
        })
    }
}
