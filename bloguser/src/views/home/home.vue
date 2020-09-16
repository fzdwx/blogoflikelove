<template>
    <el-container>
        <!--header-->
        <el-header style="height: 100px;">
            <headerBar/>
        </el-header>
        <!--main-->
        <el-main>
            <div v-if="this.$route.path==='/'">
                <div style="text-align: center"> 这是首页
                    hello this is blog,welcome
                    <div align="center">
                        <img src="/static/message/wanyihao.jpg"/>
                    </div>
                    <!--<homeCarousel/>-->
                </div>
            </div>
            <div v-else>
                <router-view v-if="isRouterAlive"/>
            </div>
        </el-main>
        <el-footer>

        </el-footer>
    </el-container>
</template>
<script>

const headerBar = () => import('@/components/bar/headerBar')
const homeCarousel = () => import('@/components/home/homeCarousel')
export default {
    data() {
        return {
            isRouterAlive: true
        }
    },
    components: {
        headerBar,
        homeCarousel
    },
    created() {

    },
    methods: {
        reload() {
            this.isRouterAlive = false
            this.$nextTick(() => (this.isRouterAlive = true))
        }
    },
    mounted() {
        this.reload()
    }
}
</script>
<style lang="scss">

</style>
