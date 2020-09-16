<template>
    <el-container>
        <!--header-->
        <el-header style="height: 100px;">
            <headerBar/>
        </el-header>
        <!--main-->
        <div class="userBtn">
            <el-button @click="goSelfInfo" type="primary">个人中心</el-button>
            <el-button @click="goEditInfo" type="primary">编辑我的资料</el-button>
            <el-button @click="goMyBlog" type="primary">查看我的博客</el-button>
        </div>
        <el-main>
            <div>
                <el-row :gutter="20" style="margin-top:10px;">
                    <el-col :span="4">
                        <div>
                            <br>
                        </div>
                    </el-col>
                    <el-col :span="16">
                        <el-card class="box-card">
                            <router-view/>
                        </el-card>
                    </el-col>
                    <el-col :span="4">
                        <div>
                            <br>
                        </div>
                    </el-col>
                </el-row>
            </div>
        </el-main>
        <el-footer>
            Footer
        </el-footer>
    </el-container>
</template>

<script>


const headerBar = () => import('@/components/bar/headerBar')
export default {
    name: 'userInfo',
    components: {headerBar},
    methods:{
        //去我的博客页面
        goMyBlog() {
            this.$router.push({
                path: '/user/info/myBlog'
            })
        },
        //查看我的信息
        goSelfInfo() {
            this.$router.push({
                path:'/user/info/'
            })
        },
        //编辑我的信息
        goEditInfo() {
            this.$router.push({
                path: '/user/info/edit'
            })
        }
    }
}

</script>
<style scoped>
.userBtn {
    position: absolute;
    top: 10%;
    left: 18%;
}
</style>

