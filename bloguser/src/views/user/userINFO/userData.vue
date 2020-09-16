<template>
    <div>
        <div slot="header" class="clearfix">
            <span>个人中心</span>
        </div>
        <div class="name-role">
            <span class="sender">{{ user.username }}</span>
        </div>
        <div class="registe-info">
            <span class="registe-info">
                注册时间：
                <i class="my-icon-ishijian"/>
                {{ user.createBy }}
            </span>
        </div>
        <!-- <el-divider>-->
        <div class="personal-relation">
            <div class="relation-item">
                真实姓名
                <div style="float: right; padding-right:20px;">
                    {{ userInfo.realname }}
                </div>
            </div>
        </div>
        <div class="personal-relation">
            <div class="relation-item">qq
                <div style="float: right; padding-right:20px;">{{ userInfo.qq }}</div>
            </div>
        </div>
        <div class="personal-relation">
            <div class="relation-item">微信
                <div style="float: right; padding-right:20px;">{{ userInfo.wechat }}</div>
            </div>
        </div>
        <div class="personal-relation">
            <div class="relation-item">邮箱
                <div style="float: right; padding-right:20px;">{{ userInfo.email }}</div>
            </div>
        </div>
        <div class="personal-relation">
            <div class="relation-item">
                手机号:
                <div style="float: right; padding-right:20px;">
                    {{ userInfo.phone }}
                </div>
            </div>
        </div>
        <div class="personal-relation">
            <div class="relation-item">工作
                <div style="float: right; padding-right:20px;">{{ userInfo.work }}</div>
            </div>
        </div>
        <div class="personal-relation">
            <div class="relation-item">住址
                <div style="float: right; padding-right:20px;">{{ userInfo.address }}</div>
            </div>
        </div>
        <div class="personal-relation">
            <div class="relation-item">兴趣
                <div style="float: right; padding-right:20px;">{{ userInfo.hobby }}</div>
            </div>
        </div>
        <div class="personal-relation">
            <div class="relation-item">自我介绍
                <div style="float: right; padding-right:20px;">{{ userInfo.intro }}</div>
            </div>
        </div>
        <!--   </el-divider>-->
    </div>

</template>

<script>
import userApi from "../../../api/user/userApi";

export default {
    name: "userData",
    data() {
        return {
            user: {},
            userInfo: {},
        }
    },
    methods: {
        //获取用户信息
        getUserInfo() {
            //如果cookie中的token不存在
            /* if (this.token === undefined) {
                 return
             }*/
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

<style lang="scss" scoped>
//卡片样式
.text {
    font-size: 14px;
}

.item {
    margin-bottom: 18px;
}

.clearfix:before,
.clearfix:after {
    display: table;
    content: "";
}

.clearfix:after {
    clear: both
}

.box-card {
    width: 100%;
}

//文本样式区
.name-role {
    font-size: 16px;
    padding: 5px;
    text-align: center;
}

.sender {
    text-align: center;
}

.registe-info {
    text-align: center;
    padding-top: 10px;
}

.personal-relation {
    font-size: 16px;
    padding: 0px 5px 15px;
    margin-right: 1px;
    width: 100%
}

.relation-item {
    padding: 12px;

}

.dialog-footer {
    padding-top: 10px;
    padding-left: 10%;
}

//布局样式区
.el-row {
    margin-bottom: 20px;

    &:last-child {
        margin-bottom: 0;
    }
}

.el-col {
    border-radius: 4px;
}

.bg-purple-dark {
    background: #99a9bf;
}

.bg-purple {
    background: #d3dce6;
}

.bg-purple-light {
    background: #e5e9f2;
}

.grid-content {
    border-radius: 4px;
    min-height: 36px;
}

.row-bg {
    padding: 10px 0;
    background-color: #f9fafc;
}
</style>
