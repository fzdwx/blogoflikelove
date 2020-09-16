<!--评论模块-->
<template>
    <div class="container">
        <!--添加评论-->
        <el-button @click="show3 = !show3">添加评论</el-button>
        <div>
            <transition name="el-zoom-in-center">
                <div v-show="show3">
                    <el-input
                        v-model="inputComment"
                        type="textarea"
                        :rows="3"
                        autofocus
                        placeholder="写下你的评论">
                    </el-input>
                    <!--提交评论-->
                    <div align="right" style="padding: 10px">
                        <el-button @click="show3 = !show3">取消</el-button>
                        <!--等用户模块-->
                        <el-button class="btn" type="success" round @click="commitComment">确定</el-button>
                    </div>
                </div>
            </transition>
        </div>
        <div class="comment" v-for="item in comments">
            <div class="info">
                <img class="avatar" :src="item.userAvatar" width="36" height="36"/>
                <div class="right">
                    <div class="name">{{ item.userName }}</div>
                    <div class="date">{{ item.content }}</div>
                </div>
            </div>
            <div class="content">{{ item.content }}</div>
            <div class="control">
                <!--有多少人赞-->
                <span class="love" :class="{active: item.islove}" @click="loveClick(item)">
                    <i class="my-icon-izan-copy"/>
                    <span class="love-num">{{ item.love > 0 ? item.love + '次赞' : '赞' }}</span>
                </span>
                <!--创建时间-->
                <span>by:  {{ item.createBy }}</span>
                <!-- <span class="comment-reply" @click="showCommentInput(item)">
                     <i class="iconfont icon-comment"></i>
                     <span>回复</span>
                 </span>-->
            </div>
            <!--回复-->
            <!--<div class="reply">
                <div class="item" v-for="reply in commentsReplay">
                    <div class="reply-content">
                        <span class="from-name">{{ reply.userName }}</span><span>: </span>
                        <span class="to-name">@{{ reply.beuserName }}</span>
                        <span>{{ reply.comment }}</span>
                    </div>
                    <div class="reply-bottom">
                        <span>{{ reply.updateBy }}</span>
                        <span class="reply-text" @click="showCommentInput(item, reply)">
                              <i class="iconfont icon-comment"></i>
                              <span>回复</span>
                          </span>
                    </div>
                </div>
                <div class="write-reply" @click="showCommentInput(item)">
                    <i class="el-icon-edit"></i>
                    <span class="add-comment">添加新评论</span>
                </div>
                <transition name="fade">
                    <div class="input-wrapper" v-if="showItemId === item.id">
                        <el-input class="gray-bg-input"
                                  v-model="inputComment"
                                  type="textarea"
                                  :rows="3"
                                  autofocus
                                  placeholder="写下你的评论">
                        </el-input>
                        <div class="btn-control">
                            <span class="cancel" @click="cancel">取消</span>
                            <el-button class="btn" type="success" round @click="commitComment(item)">确定</el-button>
                        </div>
                    </div>
                </transition>
            </div>-->
        </div>
    </div>
</template>

<script>
import blogApi from "@/api/blog/blogApi";

export default {
    props: {
        comments: {
            type: Array,
            required: true
        },
        commentsReplay: {
            type: Array,
        }
    },
    data() {
        return {
            inputComment: '',
            showItemId: '',
            show3: true,
            blogc:{
                topicId:Number,
                userName:String,
                topicCategory:1 //评论
            }
        }
    },
    methods: {
        /**
         * 点赞
         */
        loveClick(item) {
            if (item.islove === null) {
                Vue.$set(item, "islove", true);
                item.love++
                blogApi.updateCommentLoveNum(item.id, 1)
            } else {
                if (item.islove) {
                    item.love--
                    blogApi.updateCommentLoveNum(item.id, 2)
                } else {
                    item.love++
                    blogApi.updateCommentLoveNum(item.id, 1)
                }
                item.islove = !item.islove;
            }
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
        commitComment(/*blogcomment*/) {
           /* console.log(blogcomment);*/
            //对评论做初始化
            /*blogcomment.content = this.inputComment;*/
            this.blogc.content = this.inputComment;
            this.blogc.topicId=this.$route.params.id
            this.blogc.userName='游客'
            blogApi.saveComments(this.blogc)
            //页面刷新
            this.$router.go(0)
        },
        /**
         * 点击评论按钮显示输入框
         * item: 当前大评论
         * reply: 当前回复的评论
         */
        showCommentInput(item, reply) {
            if (reply) {
                this.inputComment = "@" + reply.userName + " "
            } else {
                this.inputComment = ''
            }
            this.showItemId = item.id
        }
    },
    created() {
        /*  console.log(this.comments)*/
    }
}
</script>

<style scoped lang="scss">
@import "../../../public/scss/index";

.container {
    padding: 0 10px;
    box-sizing: border-box;
    .comment {
        display: flex;
        flex-direction: column;
        padding: 10px;
        border-bottom: 1px solid $border-fourth;
        .info {
            display: flex;
            align-items: center;
            .avatar {
                border-radius: 50%;
            }
            .right {
                display: flex;
                flex-direction: column;
                margin-left: 10px;
                .name {
                    font-size: 16px;
                    color: $text-main;
                    margin-bottom: 5px;
                    font-weight: 500;
                }
                .date {
                    font-size: 12px;
                    color: $text-minor;
                }
            }
        }
        .content {
            font-size: 16px;
            color: $text-main;
            line-height: 20px;
            padding: 10px 0;
        }
        .control {
            display: flex;
            align-items: center;
            font-size: 14px;
            color: $text-minor;
            .love {
                display: flex;
                align-items: center;
                margin-right: 20px;
                cursor: pointer;
                &.active, &:hover {
                    color: $color-main;
                }
                .iconfont {
                    font-size: 14px;
                    margin-right: 5px;
                }
            }
            .comment-reply {
                display: flex;
                align-items: center;
                cursor: pointer;
                &:hover {
                    color: $text-333;
                }
                .iconfont {
                    font-size: 16px;
                    margin-right: 5px;
                }
            }
        }
        .reply {
            margin: 10px 0;
            border-left: 2px solid $border-first;
            .item {
                margin: 0 10px;
                padding: 10px 0;
                border-bottom: 1px dashed $border-third;
                .reply-content {
                    display: flex;
                    align-items: center;
                    font-size: 14px;
                    color: $text-main;
                    .from-name {
                        color: $color-main;
                    }
                    .to-name {
                        color: $color-main;
                        margin-left: 5px;
                        margin-right: 5px;
                    }
                }
                .reply-bottom {
                    display: flex;
                    align-items: center;
                    margin-top: 6px;
                    font-size: 12px;
                    color: $text-minor;
                    .reply-text {
                        display: flex;
                        align-items: center;
                        margin-left: 10px;
                        cursor: pointer;
                        &:hover {
                            color: $text-333;
                        }
                        .icon-comment {
                            margin-right: 5px;
                        }
                    }
                }
            }
            .write-reply {
                display: flex;
                align-items: center;
                font-size: 14px;
                color: $text-minor;
                padding: 10px;
                cursor: pointer;
                &:hover {
                    color: $text-main;
                }
                .el-icon-edit {
                    margin-right: 5px;
                }
            }
            .fade-enter-active, fade-leave-active {
                transition: opacity 0.5s;
            }
            .fade-enter, .fade-leave-to {
                opacity: 0;
            }
            .input-wrapper {
                padding: 10px;
                .gray-bg-input, .el-input__inner {
                    /*background-color: #67C23A;*/
                }
                .btn-control {
                    display: flex;
                    justify-content: flex-end;
                    align-items: center;
                    padding-top: 10px;
                    .cancel {
                        font-size: 16px;
                        color: $text-normal;
                        margin-right: 20px;
                        cursor: pointer;
                        &:hover {
                            color: $text-333;
                        }
                    }
                    .confirm {
                        font-size: 16px;
                    }
                }
            }
        }
    }
}
</style>
