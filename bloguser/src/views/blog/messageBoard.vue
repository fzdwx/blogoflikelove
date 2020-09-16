<template>
    <div>
        <div>
            <!--添加留言-->
            <el-button type="primary" @click="show3 = !show3">添加留言</el-button>
            <!--输入狂-->
            <transition name="el-zoom-in-center">
                <div v-show="show3">
                    <!--留言的名字-->
                    <el-input v-model="blogc.userName" placeholder="请输入你的名字" style="width: 150px;"/>
                    <br>
                    <el-input
                        v-model="inputComment"
                        type="textarea"
                        :rows="3"
                        autofocus
                        placeholder="写下你的留言">
                    </el-input>
                    <!--提交评论-->
                    <div align="right" style="padding: 10px">
                        <!--等用户模块-->
                        <el-button class="btn" type="success" round @click="commitComment">确定</el-button>
                        <el-button @click="show3 = !show3">取消</el-button>
                    </div>
                </div>
            </transition>
        </div>
        <div>
            <el-row :offset="2">
                <el-col :span="8" v-for="message in messageList">
                    <el-card>
                        <span>{{ message.content }}</span>
                        <div align="right">
                            <time class="time">by: {{ message.userName + '  ' + message.createBy }}</time>
                        </div>
                    </el-card>
                </el-col>
            </el-row>
        </div>
    </div>
</template>
<script>
import blogApi from "@/api/blog/blogApi";

export default {
    name: "messageBoard",
    created() {
        this.getData();
    },
    data() {
        return {
            messageList: [],
            inputComment: '',
            showItemId: '',
            show3: true,
            blogc: {
                topicId: '',
                userName: '',
                topicCategory: 2 //留言
            }
        }
    },
    methods: {
        getData() {
            blogApi.getMessages().then(response => {
                this.messageList = response.data.blogComments
            });
        },
        /**
         * 点击取消按钮
         */
        cancel() {
            this.showItemId = ''
        },
        /**
         * 提交评论
         */
        commitComment() {
            //对评论做初始化
            this.blogc.content = this.inputComment;
            blogApi.saveComments(this.blogc)
            console.log(1);
            //页面刷新
            this.$router.go(0)
        },
    },
}


</script>

<style scoped>

.time {
    font-size: 13px;
    color: #999;
}


</style>
