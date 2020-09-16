<template>
    <div class="block" type="card" style="height: 600px;">
        <el-carousel indicator-position="none" height="590px">
            <el-carousel-item v-for="item in imgs" :key="item">
                <img :src="item" alt="走马灯">
            </el-carousel-item>
        </el-carousel>
    </div>
</template>

<script>
export default {
    name: "homeCarousel",
    data() {
        return {
            imgs: [
                'https://images.pexels.com/photos/590141/pexels-photo-590141.jpeg?auto=compress&cs=tinysrgb&dpr=1&w=500',
                'https://images.pexels.com/photos/3062527/pexels-photo-3062527.jpeg?auto=compress&cs=tinysrgb&dpr=1&w=500',
                'https://images.pexels.com/photos/1841709/pexels-photo-1841709.jpeg?auto=compress&cs=tinysrgb&dpr=1&w=500',
                'https://images.pexels.com/photos/2508810/pexels-photo-2508810.jpeg?auto=compress&cs=tinysrgb&dpr=1&w=500',
                'https://images.pexels.com/photos/5099224/pexels-photo-5099224.jpeg?auto=compress&cs=tinysrgb&dpr=1&w=500',

               /* 'http://47.112.150.204:8888/img/carousel/c1.png',
                'http://47.112.150.204:8888/img/carousel/c2.png',
                'http://47.112.150.204:8888/img/carousel/c3.png',
                'http://47.112.150.204:8888/img/carousel/c4.png',*/
               /* 'http://localhost/img/carousel/c1.png',
                'http://localhost/img/carousel/c2.png',
                'http://localhost/img/carousel/c3.png',
                'http://localhost/img/carousel/c4.png',
                '/static/carousel/c1.png',
                '/static/carousel/c2.png',
                '/static/carousel/c3.png',
                '/static/carousel/c4.png',*/
            ]
        }
    }
}
</script>

<style scoped>

</style>
