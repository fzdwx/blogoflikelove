<template>
    <div>
        <el-row>
            <el-col :span="4">
                <div class="grid-content bg-purple"><br></div>
            </el-col>
            <el-col :span="16" >
                <el-row :gutter="20">
                    <el-col :span="8">
                        <el-input v-model="blog.title" placeholder="请输入标题"></el-input>
                    </el-col>
                    <el-col :span="8">
                        <div>
                            <el-select v-model="blog.categoryName" placeholder="请选择博客分类" >
                               <!-- <el-input v-model="blog.categoryId" type="hidden">{{ item.id }}</el-input>-->
                                <el-option
                                    v-for="item in blogCategoryList"
                                    :key="item.value"
                                    :label="item.category"
                                    :value="item.category">
                                </el-option>
                            </el-select>
                        </div>
                    </el-col>
                    <el-col :span="8">
                        <div class="grid-content bg-purple"></div>
                    </el-col>
                </el-row>
                <mavon-editor v-model="blog.content" style="height: 1200px;width: 100%;"/>
            </el-col>
            <el-col :span="4">
                <div class="grid-content bg-purple"></div>
            </el-col>
        </el-row>
        <div class=" r">
            <el-menu class="el-menu-demo" mode="horizontal" background-color="#f1f1f1" text-color="#303133">
                <el-submenu index="1" show-timeout="1"  >
                    <template slot="title">
                        <i class="el-icon-s-tools"></i>
                        <span slot="title">操作</span>
                    </template>
                    <el-menu-item-group align="center">
                        <el-button type="success" @click="onSubmit">提交</el-button>
                        <el-button type="warning" @click="goBack">取消</el-button>
                    </el-menu-item-group>
                </el-submenu>
            </el-menu>
        </div>
    </div>
</template>
<script>
import blogApi from "@/api/blog/blogApi";

export default {
    components: {},
    created() {
        this.saveOrEdit()
        this.getBlogCategoryList()
    },
    data() {
        return {
            blogCategoryList:[],
            blogId: this.$route.params.id,
            blog: {
                /* bid:'123',*/
                title: '',
                content: 'hello,welcome to blog of likelove !',
                authorId: 1,
                authorName: '游客',
                authorAvatar: 1,
                categoryName: '',
                categoryId:'0',
                deleted: false
            },
        }
    },
    methods: {
        //获取所有博客分类
        getBlogCategoryList() {
            blogApi.getAllBlogCategory().then(res => {
                this.blogCategoryList = res.data.blogCategoryList
            });
        },
        //判断是保存还是编辑
        saveOrEdit() {
            if (this.blogId !== undefined) {
                blogApi.getEditBlog(this.blogId).then(res => {
                    this.blog = res.data.blog
                });
            }
        },
        //返回阅读页面
        goBack() {
            this.$router.go(-1)
        },
        //保存博客
        save() {
            if (this.blog.categoryName==='') {
                this.$message.error("请选择博客分类")
                return
            }
            blogApi.saveMyBlog(this.blog)
                .then(res => {
                this.$message({
                    message: '保存成功',
                    type: 'success'
                })
                this.$router.push({path: '/blog/show'})
            })
                .catch(reason => {
                this.$message({
                    message: '保存失败',
                    type: 'error'
                })
            });
        },
        edit() {
            /*console.log(this.blog)*/
            blogApi.editMyBlog(this.blog).then(res => {
                this.$message({
                    message: '修改成功',
                    type: 'success'
                })
                this.$router.push({path: '/blog/show'})
            }).catch(reason => {
                this.$message({
                    message: reason.data,
                    type: 'error'
                })
            });
        },
        onSubmit() {
            if (this.blog.authorName === '') {
                this.$notify({
                    title: '保存博客',
                    dangerouslyUseHTMLString: true,
                    message: '<strong>博客<i style="color: red;">作者</i>不能为空</strong>',
                    offset: 100
                });
                return false
            }
            if (this.blog.title === '') {
                this.$notify({
                    title: '保存博客',
                    dangerouslyUseHTMLString: true,
                    message: '<strong>博客<i style="color: red;">标题</i>不能为空</strong>',
                    offset: 100
                });
                return false
            }
            //路由参数为null 保存
            if (this.$route.name === 'blogSave') {
                this.save();
            } else {//修改
                this.edit();
            }
        }
    },
}
</script>

<style>

.r {
    position: absolute;
    top: 8px;
    left: 38%;
}
</style>
