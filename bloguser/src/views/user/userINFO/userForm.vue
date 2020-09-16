<template>
    <div>
        <div class="grid-content bg-purple">

            <div slot="header" class="clearfix">
                <span>基本资料</span>
            </div>
            <div>
                <el-form label-width="80px" v-model="dataFrom" size="small" label-position="right">
                    <el-form-item label="真实姓名" prop="nickName">
                        <el-input auto-complete="off" v-model="userInfo.realname"></el-input>
                    </el-form-item>
                    <el-form-item label="手机号" prop="phone">
                        <el-input auto-complete="off" v-model="userInfo.phone"></el-input>
                    </el-form-item>
                    <el-form-item label="qq" prop="qq">
                        <el-input auto-complete="off" v-model="userInfo.qq"></el-input>
                    </el-form-item>
                    <el-form-item label="微信" prop="wechat">
                        <el-input auto-complete="off" v-model="userInfo.wechat"></el-input>
                    </el-form-item>
                    <el-form-item label="邮箱" prop="email">
                        <el-input auto-complete="off" v-model="userInfo.email"></el-input>
                    </el-form-item>
                    <el-form-item label="工作" prop="qq">
                        <el-input auto-complete="off" v-model="userInfo.work"></el-input>
                    </el-form-item>
                    <el-form-item label="兴趣" prop="qq">
                        <el-input auto-complete="off" v-model="userInfo.hobby"></el-input>
                    </el-form-item>

                    <el-form-item label="自我介绍" prop="qq">
                        <el-input type="textarea" auto-complete="off" v-model="userInfo.intro"></el-input>
                    </el-form-item>

                </el-form>
                <div slot="footer" class="dialog-footer" align="center">
                    <el-button  @click="save()" type="primary">提交</el-button>
                </div>
            </div>

        </div>
    </div>
</template>

<script>
import userApi from "../../../api/user/userApi";

export default {
    name: "userForm",
    data() {
        return {
            user: {},
            userInfo: {},
        }
    },
    methods: {
        save() {
            userApi.updateUserInfo(this.userInfo).then(res => {
                this.$message.success("修改成功")
                this.$router.push({
                    path: '/user/info'
                })
            }).cache(res => {
                this.$message.error("修改失败，请稍后再试")
            });
        },
        //获取用户信息
        getUserInfo() {
            //如果cookie中的token不存在

            userApi.getLoginUserInfo(2).then(res => {
                this.user = res.data.user
                this.userInfo = res.data.userInfo
                this.isLogin = !this.isLogin
            });
        },
    },
    created() {
        this.getUserInfo()
    }
}
</script>

<style scoped>

</style>
