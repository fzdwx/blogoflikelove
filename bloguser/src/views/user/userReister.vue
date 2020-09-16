<template>
    <div class="big1" style="width: 100%;height: 1000px;">
        <!--背景-->
        <img src="/img/bg/1.png" class="img_forfont">
        <div class="login_box">
            <!--logo-->
            <div class="avatar_box">
                <img src="/favicon.ico" alt="">
            </div>
            <div class="title">
                <el-button type="text" @click="goLogin">登录</el-button>
                <span>·</span>
                <el-button type="text" @click="goRegister">注册</el-button>
            </div>
            注册
            <el-form action="register" class="login_form">
                <!--用户名-->
                <el-form-item label="用户账号(可以为任意数字或者字母,在2~12位之间)" prop="uid">
                    <el-input maxlength="12" prefix-icon="my-icon-iicon-username" v-model="user.uid"/>
                </el-form-item>
                <!--密码-->
                <el-form-item label="密码(6~16位之间)" prop="password">
                    <el-input type="password" maxlength="16" minlength="6" prefix-icon="my-icon-imima" v-model="user.password"/>
                </el-form-item>
                <!--确认密码-->
                <el-form-item label="确认密码" prop="password">
                    <el-input type="password"  maxlength="16" minlength="6" prefix-icon="my-icon-imima" v-model="checkPassword"/>
                </el-form-item>
                <!--按钮-->
                <el-form-item class="bnts">
                    <el-button type="primary" @click="submitRegister">注册</el-button>
                    <el-button type="info" @click="reset">重置</el-button>
                </el-form-item>
            </el-form>
        </div>
    </div>
</template>

<script>
import userApi from "../../api/user/userApi";

export default {
    data() {
        return {
            user: {
                uid: '',//用户登录账号
                password: '',//用户密码
                username: '',
                avatar:'https://oss.aliyuncs.com/aliyun_id_photo_bucket/default_handsome.jpg'//默认用户头像
            },
            checkPassword: '',
            sending: false, // 是否发送验证码
            second: 60, // 倒计时间
            codeText: '获取验证码'
        }
    },
    methods: {
        reset() {
            this.user.uid = ''
            this.checkPassword = ''
            this.user.password = ''
        },
        // 注册
        submitRegister() {
            if (this.user.uid.length < 2) {
                this.$message.error("用户名长度过短")
                return false;
            }
            if (this.user.password !== this.checkPassword) {
                this.$message.error("两次密码不同请重新输入")
                return false;
            }
            if (this.user.password.length<6) {
                this.$message.error("密码长度过短")
                return false;
            }
            this.user.username = this.user.uid
            userApi.registerUser(this.user).then(response => {
                // 提示注册成功
                this.$message.success(response.message)
                this.$router.push({path: 'login'})
            }).catch(response => {
                this.$message.error("注册失败，用户名可能已经存")
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
    }
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
    bottom: 0;
    width: 100%;
    padding: 0 10px;
    box-sizing: border-box;
}

.bnts {
    display: flex;
    justify-content: flex-end;
}
</style>
