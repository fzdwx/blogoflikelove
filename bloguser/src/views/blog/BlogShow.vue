<template>
    <div>
        <div align="center">
            <el-autocomplete style="position: absolute;top: 20px"
                             v-model="searchValue"
                             class="inline-input"
                             :fetch-suggestions="querySearch"
                             placeholder="请输入要查找的博客标题..."
                             :trigger-on-focus="false"
                             value-key="name"
            ></el-autocomplete>
        </div>
        <el-row>
            <el-col :span="4">
                <br>
            </el-col>
            <el-col :span="16">
                <br>
                <div v-for="blog in  blogList" style="margin: 20px;">
                    <el-card>
                        <div slot="header" align="left">
                            <!--博客标题-->
                            <b>{{ blog.title }}</b>
                            <el-button style="float:right;padding:3px 10px;font-size: 18px" @click="goBlogRead(blog.id)"
                                       type="text">
                                阅读
                            </el-button>
                        </div>
                        <!--显示博客内容-->
                        <div v-html="blog.content.substr(0,200) "/>
                        ......
                        <div>
                            <el-row :gutter="20">
                                <el-col :span="6">
                                    <div align="left">
                                        <!--分类信息-->
                                        <el-button class="el-button--text"
                                                   @click="getBlogByCategory(blog.categoryName)">
                                            <i class="my-icon-ileimupinleifenleileibie"></i>
                                            {{ blog.categoryName }}
                                        </el-button>
                                        <!--被浏览次数-->
                                        <el-button type="text">
                                            <i class="my-icon-ichakan"></i>
                                            <p style="display: inline">{{ blog.views > 0 ? blog.views : '' }}</p>
                                        </el-button>
                                        <!--点赞数量-->
                                        <el-button type="text" :class="{active: blog.islove}" @click="loveClick(blog)">
                                            <i class="my-icon-izan-copy"/>
                                            <p style="display: inline">{{ blog.love > 0 ? blog.love + '次赞' : '赞' }}</p>
                                        </el-button>
                                    </div>
                                </el-col>
                                <el-col :span="6">
                                    <div><br></div>
                                </el-col>
                                <el-col :span="6">
                                    <div><br></div>
                                </el-col>
                                <el-col :span="6">
                                    <!--显示作者和创建时间-->
                                    <div align="right">
                                        <el-button type="text">by: {{ blog.authorName + ' ;   ' + blog.createBy }}
                                        </el-button>
                                    </div>
                                </el-col>
                            </el-row>
                        </div>
                    </el-card>
                </div>
            </el-col>
            <el-col :span="4">
                <!--博客分类-->
                <div>
                    <div align="center">
                        <el-button @click="goThis" type="success" index="1-3">显示所有</el-button>
                        <div v-for="blogCategory in blogCategoryList">
                            <el-button type="text"
                                       @click="getBlogPage(null, null,blogCategory.category,null,null ) ">
                                {{ blogCategory.category }}
                            </el-button>
                        </div>
                    </div>
                </div>
            </el-col>
        </el-row>
        <!--分页方法-->
        <el-pagination
            :current-page="this.page"
            :total="this.total"
            :page-size="this.limit"
            :page-sizes="[5,10,20,50]"
            layout="sizes, prev, pager, next, jumper, ->, total, slot"
            style="padding: 20px 0;text-align: center"
            background
            @current-change="pageChange"
            @size-change="pageSizeChange"
        />
        <div class="x">
            <!--排序  -->
            <el-menu class="el-menu-demo" mode="horizontal" background-color="#f1f1f1" text-color="#303133">
                <el-submenu index="1">
                    <template slot="title">
                        <i class="my-icon-ipaixu"></i>
                        排序
                    </template>
                    <el-menu-item-group title="根据浏览数排序">
                        <div>
                            <el-button circle @click="sortBlog(2)" type="primary" class="el-icon-top" index="1-1"/>
                            <el-button circle @click="sortBlog(1)" type="primary" class="el-icon-bottom" index="1-2"/>
                            <!--1:根据浏览数升序排列-->
                        </div><!--2:根据浏览数据降序排列-->
                    </el-menu-item-group>
                    <el-menu-item-group title="根据创建时间">
                        <el-button circle @click="sortBlog(4)" type="primary" class="el-icon-top" index="1-3"/>
                        <!--4:根据修改时间降序排列-->
                        <el-button circle @click="sortBlog(3)" type="primary" class="el-icon-bottom" index="1-4"/>
                        <!--3:根据修改时间升序排列<br>-->
                    </el-menu-item-group>
                </el-submenu>
            </el-menu>
            <div class="y">
                <!--博客分类-->
            </div>
        </div>
    </div>
</template>
<script>
import blogApi from "@/api/blog/blogApi";

export default {
    name: "BlogShow",
    created() {
        this.getBlogPage()
        //获取博客分页有哪些
        this.getBlogCategoryList()
    },
    data() {
        return {
            list: [],//讲师列表
            total: 0,//总记录数
            page: 1,//当前页码
            limit: 5,//每页的记录数
            blogCategoryList: [],//博客分类的名字
            blog: {
                categoryName: '',
            },
            blogList: [],
            bc: '',
            searchValue: '',
            blogVo: {
                /*  title:'',
                  authorName:'',
                  categoryName:'',
                  viewsStart:'',
                  viewsEnd:''*/
            }
        }
    },
    methods: {
        //调用分页请求
        getBlogPage(title, authorName, categoryName, viewsStart, viewsEnd) {
            this.blogVo.categoryName = categoryName;
            this.blogVo.title=title;
            this.blogVo.authorName= authorName;
            this.blogVo.viewsStart= viewsStart;
            this.blogVo.viewsEnd= viewsEnd;
            blogApi.getBlogByPage(this.page, this.limit, this.blogVo).then(res => {
                this.blogList = res.data.blogList
                this.total = res.data.total
            });
        },
        //每页记录数量改变时间
        pageSizeChange(size) {
            this.limit = size
            this.getBlogPage()
        },
        //页码改变事件
        pageChange(page) {
            this.page = page
            this.getBlogPage()
        },
        //刷新页面
        goThis() {
            this.$router.go(0)
        },
        //根据博客分类名字，返回对应的博客
        getBlogByCategory(n) {
            if (n) {
                blogApi.getBlogByCategoryName(n).then(res => {
                    this.blogList = res.data.blogList
                });
            }
        },
        //获取所有博客分类
        getBlogCategoryList() {
            blogApi.getAllBlogCategory().then(res => {
                this.blogCategoryList = res.data.blogCategoryList
            });
        },
        //搜索请求数据
        querySearch(queryString, callback) {
            blogApi.searchBlogByTitle(queryString).then(response => {
                callback(response.data.blogList)
                //判断当前从后端请求的博客列表是否为0
                if (response.data.blogList.length !== 0) {
                    //如果不为0
                    this.blogList = response.data.blogList;
                } else {

                    this.$notify({
                        type: "warning",
                        title: '搜索博客',
                        dangerouslyUseHTMLString: true,
                        message: '<strong>没有你要找的博客,标题：<i style="color: red;" >' + this.searchValue + '</i></strong>',
                        offset: 100
                    });
                }
            })
        },
        loveClick(item) {
            if (item.islove === null) {
                Vue.$set(item, "islove", true);
                item.love++
                blogApi.updateBlogLoveNum(item.id, 1)
            } else {
                if (item.islove) {
                    item.love--
                    blogApi.updateBlogLoveNum(item.id, 2)
                } else {
                    item.love++
                    blogApi.updateBlogLoveNum(item.id, 1)
                }
                item.islove = !item.islove;
            }
        },
        //调用blogApi中的方法
        sortBlog(status) {
            blogApi.getBlogListSort(status).then(res => {
                this.blogList = res.data.blogList
            });
        },
        blogContent(text) {
            this.bc = text.substr(0, 300)
        },
        goBlogRead(id) {
            this.$router.push({
                path: '/blog/read/' + id,
            })
        },
        //获取博客列表
        getData() {
            blogApi.getBlogList().then(res => {
                this.blogList = res.data.blogList
            });
        }
    }
}
</script>

<style>
.y {
    position: absolute;
    top: 0px;
    right: -100%;
}

.x {
    position: absolute;
    top: 8px;
    left: 37%;
}
</style>
