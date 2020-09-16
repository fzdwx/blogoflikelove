<template>
    <el-row class="headbar">
        <!--logo-->
        <el-col :span="4">
            <div align="center" class="grid-content bg-purple">
                <img src="/static/logo.png" width="60px" height="60px" alt="logo">
            </div>
        </el-col>
        <!--btn-->
        <el-col :span="16">
            <div class="grid-content bg-purple-light" align="center">
                <el-menu class="el-menu-demo" mode="horizontal" background-color="#f1f1f1" text-color="#303133">
                    <el-menu-item index="1" @click="goHome">
                        <i class="el-icon-s-home"></i>
                        首页
                    </el-menu-item>
                    <el-submenu index="2">
                        <template slot="title">
                            <i class="el-icon-edit-outline"></i>
                            博客
                        </template>
                        <el-menu-item index="2-1" @click="goBlog">
                            <i class="el-icon-view"></i>
                            show
                        </el-menu-item>

                        <el-menu-item index="2-2" @click="goBlogEdit">
                            <i class="el-icon-edit"></i>
                            add
                        </el-menu-item>
                    </el-submenu>
                    <el-menu-item index="3" @click="goMessageBord" name="messageBord">
                        <i class="el-icon-data-board"/>
                        留言板
                    </el-menu-item>
                    <!--搜索框-->
                    <slot></slot>
                </el-menu>
            </div>
        </el-col>
        <!--用户栏-->
        <el-col :span="4">
            <!--没有登录的时候-->
            <el-menu v-show="isLogin" class="el-menu-demo" mode="horizontal" background-color="#f1f1f1"
                     text-color="#303133">
                <el-menu-item index="1" @click="goLogin">
                    <i class="my-icon-idenglu-copy"></i>
                    登录
                </el-menu-item>
                <el-menu-item index="1" @click="goRegister">
                    <i class="my-icon-izhuce"></i>
                    注册
                </el-menu-item>
            </el-menu>
            <!--登录后-->
            <div align="right" v-show="!isLogin">
                <el-dropdown>
                    <!--用户头像-->
                    <el-avatar :src="user.avatar"></el-avatar>
                    <el-dropdown-menu slot="dropdown" class="user-dropdown">
                        <span>hello <i>{{ user.username }}</i></span>
                        <router-link class="inlineBlock" :to=" userInfoUrl" style="text-decoration: none">
                            <el-dropdown-item>
                                个人主页
                            </el-dropdown-item>
                        </router-link>
                        <el-dropdown-item>
                            选项1
                        </el-dropdown-item>
                        <el-dropdown-item divided>
                            <span style="display:block;" @click="logout">注销</span>
                        </el-dropdown-item>
                    </el-dropdown-menu>
                </el-dropdown>
            </div>
        </el-col>
    </el-row>
</template>
<script>
import userApi from "../../api/user/userApi";
import cookie from "js-cookie";

export default {
    name: "homeBar",
    created() {
        this.getUserInfo();
    },
    /*mounted() {
        this.getUserInfo()
    },*/
    data() {
        return {
            userInfoUrl: '',
            activeIndex: '1',
            searchValue: '',
            blogList: [],
            isLogin: true,
            user: {},
            token: cookie.get("blog-token"),
        }
    },
    methods: {
        logout() {
            //清空token
            cookie.remove("blog-token")
            //跳转页面
            window.location.href = '/'
        },
        //获取用户信息
        getUserInfo() {
            //如果cookie中的token不存在
            if (this.token === undefined) {
                return
            }
            userApi.getLoginUserInfo(1).then(res => {
                this.user = res.data.user
                this.userInfoUrl = '/user/info'
                this.isLogin = !this.isLogin
            });
        },
        goLogin() {
            this.$router.push({
                path: '/user/login'
            })
        },
        goRegister() {
            this.$router.push({
                path: '/user/register'
            })
        },
        goBlogEdit() {
            this.$router.push({
                path: '/blog/edit'
            })
        },
        goHome() {
            this.$router.push({
                path: '/'
            })

        },
        goBlog() {
            this.$router.push({
                path: '/blog/show'
            })
        },
        goMessageBord() {
            this.$router.push({
                path: '/messageBoard'
            })
        }
    },
}
</script>
<style>

</style>
