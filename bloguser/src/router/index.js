/*import Vue from 'vue'
import VueRouter from 'vue-router'

Vue.use(VueRouter)*/

const routes = [

    //用户个人信息
    {
        path: '/user/info',
        name: 'info',
        component: resolve => require(['@/views/user/userINFO/userInfo'], resolve),
        children: [
            //个人信息
            {
                path: '',
                name: 'userData',
                component: resolve => require(['@/views/user/userINFO/userData'], resolve),
                meta: {
                    title: 'myInfo - Blog of likelove',
                },
            },
            //个人信息编辑页面
            {
                path: 'edit',
                name: 'edit',
                component: resolve => require(['@/views/user/userINFO/userForm'], resolve),
                meta: {
                    title: '编辑我的资料 - Blog of likelove',
                },
            },
            //查看我的博客
            {
                path: 'myBlog',
                name:'myBlog',
                component: resolve => require(['@/views/user/userINFO/userMyBlog'], resolve),
                meta: {
                    title: '查看我的博客 - Blog of likelove',
                },
            }
        ]
    },
    //用户相关
    {
        path: '/user',
        name: "user",
        meta: {
            title: '用户 - Blog of likelove',
        },
        component: resolve => require(['@/views/user/user'], resolve),
        children: [
            //登录
            {
                path: 'login',
                name: 'login',
                meta: {
                    title: '登录 - Blog of likelove',
                },
                component: resolve => require(['@/views/user/userLogin'], resolve),
            },
            //注册
            {
                path: 'register',
                name: 'register',
                component: resolve => require(['@/views/user/userReister'], resolve),
                meta: {
                    title: '注册 - Blog of likelove',
                }
            },
        ]
    },
    //博客页面
    {
        path: '/blog',
        name: 'blog',
        component: resolve => require(['@/views/blog/blog'], resolve),
        children: [
            //阅读博客
            {
                path: 'read/:id',
                name: 'blogRead',
                component: resolve => require(['@/views/blog/BlogRead'], resolve),
                meta: {
                    title: '博客阅读 - Blog of likelove'
                }
            },
            {
                path: 'show',
                name: 'blogShow',
                component: resolve => require(['@/views/blog/BlogShow'], resolve),
                meta: {
                    title: '博客 - Blog of likelove'
                }
            },
            //博客编辑
            {
                path: 'edit/:id',
                name: 'blogEdit',
                component: resolve => require(['@/views/blog/BlogForm'], resolve),
                meta: {
                    title: '博客编辑 - Blog of likelove'
                }
            },
            //博客添加
            {
                path: 'edit',
                name: 'blogSave',
                component: resolve => require(['@/views/blog/BlogForm'], resolve),
                meta: {
                    title: '博客编辑 - Blog of likelove'
                }
            },
        ],
        meta: {
            title: '博客 - Blog of likelove',
        },
    },
    //首页
    {
        path: '/',
        name: 'Home',
        component: resolve => require(['@/views/home/home'], resolve),
        meta: {
            title: '首页 - Blog of likelove',
        },
        children: [
            //关于
            {
                path: 'about',
                name: 'About',
                component: resolve => require(['@/views/home/about'], resolve),
                meta: {
                    title: '关于 - Blog of likelove',
                }
            },
            {
                path: 'messageBoard',
                name: 'messageBoard',
                component: resolve => require(['@/views/blog/messageBoard'], resolve),
                meta: {
                    title: '留言板 - Blog of likelove'
                }
            }
        ]
    },
];
const router = new VueRouter({
    mode: 'history',
    base: process.env.BASE_URL,
    routes
})
//导航首位 动态修改标题 前置钩子
router.beforeEach((to, from, next) => {
    //from => to
    document.title = to.meta.title
    if (to.path === '/user/login') {
        next();
    } else {
        let token = localStorage.getItem('Authorization');
        if (token === 'null' || token === '') {
            next('/user/login');
        } else {
            next();
        }
    }
})
export default router
