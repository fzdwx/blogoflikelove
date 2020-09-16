<template>
    <div class="big1" style="width: 100%;height: 1000px;">
        <!--背景-->
        <img src="/img/bg/1.png" class="img_forfont">
        <div class="login_box">
            <!--logo-->
            <div class="avatar_box">
                <img src="/static/logo.png" alt="">
            </div>
            <div class="title">
                <el-button type="text" @click="goLogin">登录</el-button>
                <span>·</span>
                <el-button type="text" @click="goRegister">注册</el-button>
            </div>
            登录
            <!--表单登录区域-->
            <el-form class="login_form">
                <!--用户名-->
                <el-form-item prop="uid" label="请输入用户名">
                    <el-input prefix-icon="my-icon-iicon-username" v-model="user.uid"/>
                </el-form-item>
                <!--密码-->
                <el-form-item label="请输入密码" prop="password">
                    <el-input prefix-icon="my-icon-imima" v-model="user.password"/>
                </el-form-item>
                <!--按钮-->
                <el-form-item class="bnts">
                    <el-button type="primary" @click="login()">登录</el-button>
                    <el-button type="info" @click="reset">重置</el-button>
                </el-form-item>
            </el-form>
        </div>
    </div>
</template>

<script>
import userApi from "../../api/user/userApi";
import cookie from "js-cookie"

export default {
    name: "login",
    data() {
        return {
            user: {
                uid: '',
                password: '',
            },
        }
    },
    methods: {
        loginMe() {
            this.loginMe;
        },
        login() {
            //执行调用远程登录接口
            userApi.loginUser(this.user)
                .then(res => {
                    this.$message.success("登陆成功")
                    //写入cookie
                    cookie.set("blog-token", res.data.token);
                    //跳转到查看所有博客页面
                    this.$router.push({path: '/blog/show'})
                })
                .catch(response => {
                    this.$message.error("登录失败，用户名或密码错误")
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
        //重置
        reset() {
            this.user.uid = ''
            this.user.username = ''
        }
    },
}
</script>

<style lang="less" scoped>

.img_forfont {
    position: absolute;
    left: 50%;
    transform: translate(-50%, -50%);
}

.big1 {

}

.login_box {
    width: 650px;
    height: 450px;
    background-color: #ddd;
    border-radius: 3px;
    position: absolute;
    left: 50%;
    top: 50%;
    transform: translate(-50%, -20%);
}

.avatar_box {
    height: 120px;
    width: 120px;
    border: 1px solid #eee;
    border-radius: 50%;
    padding: 10px;
    box-shadow: 0 0 10px #ddd;
    position: absolute;
    left: 50%;
    transform: translate(-50%, -50%);
    background-color: #ffffff;

    img {
        height: 120px;
        width: 120px;
        border-radius: 50%;
        background-color: #eeeeee;
    }

}

.login_form {
    position: absolute;
    bottom: 20%;
    width: 100%;
    padding: 0 10px;
    box-sizing: border-box;
}

.bnts {
    display: flex;
    justify-content: flex-end;
}
</style>
