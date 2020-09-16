<template>
    <el-container>
        <!--header-->
        <el-header style="height: 100px;">
            <headerBar/>
        </el-header>

        <!--main-->
        <el-main>
            <router-view v-if="isRouterAlive"/>
        </el-main>
        <el-footer>
            Footer
        </el-footer>
    </el-container>
</template>
<script>
const headerBar = () => import('@/components/bar/headerBar')
export default {
    data() {
        return {
            isRouterAlive: true

        }
    },
    methods: {
        reload() {
            this.isRouterAlive = false
            this.$nextTick(() => (this.isRouterAlive = true))
        }
    }, mounted() {
        this.reload()
    },
    name: "user",
    components: {
        headerBar
    }
}
</script>

<style scoped>

</style>
