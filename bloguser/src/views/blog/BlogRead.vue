<template>
    <div>
        <el-row>
            <el-col :span="4">
                <div><br></div>
            </el-col>
            <el-col :span="16">
                <!--中间页面-->
                <div>
                    <el-row>
                        <el-col :span="4"><br></el-col>
                        <el-col :span="16"><h1 align="center">{{ blog.title }}</h1></el-col>
                        <el-col :span="4"><div align="right"></div></el-col>
                    </el-row>
                    <mavon-editor :toolbars="toolbars" :editable="false" :navigation="true"
                                  :subfield="false" :defaultOpen="'preview'" :value="blog.content"
                    style="height: 680px"
                    />
                    <div align="right">
                        <el-button type="text">by:{{ blog.authorName + '   ' + blog.updateBy }}</el-button>
                    </div>
                </div>
                <!--显示评论-->
                <h2 align="left">相关评论</h2>
                <blogcomment :comments="blogComment" ></blogcomment>
            </el-col>
            <el-col :span="2">
                <div></div>
            </el-col>
        </el-row>
        <div class="z">
            <el-menu class="el-menu-demo" mode="horizontal" background-color="#f1f1f1" text-color="#303133">
                <el-submenu index="1">
                    <template slot="title">
                        <i class="el-icon-s-tools"></i>
                        <span slot="title">操作</span>
                    </template>
                    <el-menu-item-group align="center">
                      <!--  <el-button type="success" @click="onSubmit">修改</el-button>-->
                        <el-button type="warning" @click="goBack">返回</el-button>
                    </el-menu-item-group>
                </el-submenu>
            </el-menu>
        </div>
    </div>
</template>
<script>
import blogApi from "@/api/blog/blogApi";

const blogcomment = () => import('@/components/blog/blogComment')
export default {
    name: "BlogRead",
    created() {
        this.read(this.$route.params.id)
        this.getComment()
    },
    data() {
        return {
            blog: {},
            blogComment: [],


            toolbars: {
                readmodel: true, // 沉浸式阅读
                htmlcode: true, // 展示html源码
                help: true, // 帮助
                navigation: true, // 导航目录
                subfield: true, // 单双栏模式
                preview: true, // 预览
            }

        }
    },
    components:{
      blogcomment
    },
    methods: {
        getComment() {
            blogApi.getComments(this.$route.params.id).then(res => {
                this.blogComment = res.data.blogComments;
            });
        },
        goBack() {
            this.$router.go(-1)
        },
        onSubmit() {
            console.log("1");
            this.$router.push({
                path:'/blog/edit/'+this.$route.params.id
            })
        },
        read(id) {
            blogApi.read(id).then(res => {
                this.blog = res.data.blog
                /*获取博客评论*/
            })
        }
    }
}
</script>

<style >

.z {
    position: absolute;
    top: 8px;
    left: 38%;
}
</style>
