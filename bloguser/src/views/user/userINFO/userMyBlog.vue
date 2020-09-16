<template>
    <div>
        <div>总数量：{{ blogCount }}</div>
        <div v-for="blog in blogList">
            <div align="center" style="padding: 20px">
                <div style="width: 30px;">
                    <el-button style="padding:3px 10px;font-size: 17px" @click="goBlogRead(blog.id)" type="text">
                        {{ blog.title }}
                    </el-button>
                </div>
                <span style="padding: 20px;" class="el-button--text">
                    <i class="my-icon-ileimupinleifenleileibie">  {{ blog.categoryName }}</i>
                </span>
                <span style="padding: 20px" class="el-button--text"><i class="my-icon-ichakan">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;{{ blog.views }}</i></span>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
                <span style="padding: 20px" class="el-button--text"><i class="my-icon-izan-copy">{{ blog.love }}</i></span>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
                <span style="padding: 10px" class="el-button--text"><i class="my-icon-ishijian">  {{ blog.createBy }}</i></span>
                <el-button style="font-size: 16px;" text-color="red" type="text" @click="onSubmit(blog.id)">修改</el-button>
            </div>
        </div>
    </div>
</template>
<script>
import userApi from "../../../api/user/userApi";
export default {
    name: "userMyBlog",
    created() {
        this.getMyBlog()
    },
    data() {
        return {
            blogList: [],
            blogCount: '',
        }
    },
    methods: {
        onSubmit(id) {
            console.log(id);
            this.$router.push({
                path: '/blog/edit/' + id
            })
        },
        goBlogRead(id) {
            this.$router.push({
                path: '/blog/read/' + id,
            })
        },
        getMyBlog() {
            userApi.getMyBlog().then(res => {
                this.blogList = res.data.blogList
                this.blogCount = res.data.blogCount
            });
        }
    }
}
</script>

<style scoped>

</style>
