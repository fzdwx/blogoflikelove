import request from '@/network/request'

export default {
    //获取当前登录用户的博客信息：
    getMyBlog() {
        return request({
            url:'/blog/user/myBlog',
            method:'get',
        });
    },
    //修改资料
    updateUserInfo(userInfo) {
        return request({
            url:'/blog/user/edit',
            method:'put',
            data:userInfo,
        })
    },
    //获取已经登录的用户数据
    getLoginUserInfo(status) {
        return request({
            url:'/blog/user/getUserInfo/'+status,
            method:'get',
            //在请求头中定义一个字段：token
        })
    },
    //注册用户
    registerUser(user) {
        return request({
            url:'/blog/user/register',
            data:user,
            method:'post'
        })
    },
    //登录
    loginUser(user) {
        return request({
            url:'/blog/user/login',
            data: user,
            method:'Post'
        });
    }
}
