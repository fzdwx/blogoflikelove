<template>
    <el-container>
        <!--header-->
        <el-header style="height: 100px;">
            <headerBar/>
        </el-header>
        <!--main-->
        <el-main>
            <router-view/>
        </el-main>
        <el-footer>
            Footer
        </el-footer>
    </el-container>
</template>
<script>
const headerBar = () => import('@/components/bar/headerBar')
export default {
    name: "blog",
    components: {
        headerBar
    },
    data() {
        return {
            activeIndex: '2',
        }
    },
}
</script>

<style scoped>
</style>
