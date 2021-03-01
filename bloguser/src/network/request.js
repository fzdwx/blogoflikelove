/*import axios from 'axios'*/
// 创建axios实例

import cookie from 'js-cookie'

const service = axios.create({
    baseURL: 'http://8.131.57.243:8888', // api 的 base_url
    // baseURL: 'http://localhost:8888', // api 的 base_url
    timeout: 5000 // 请求超时时间
})
// request拦截器
service.interceptors.request.use(
    config => {
        const token = cookie.get("blog-token")
        //如果存在就添加并发送给服务器
        if (token) {
            config.headers['token'] = cookie.get("blog-token")
        }
        return config;
    }/*,
    error => {
        // Do something with request error
        console.log(error) // for debug
    }*/
);

// response 拦截器
service.interceptors.response.use(
    response => {
        /**
         * code为非20000是抛错 可结合自己业务进行修改
         */
        const res = response.data
        if (res.code === 22222) {
            /*  /!*cookie.set("token",null)//清除cookie中的token*!/
              cookie.delete("token")*/
        }
        if (res.code !== 20000) {
            // 50008:非法的token; 50012:其他客户端登录了;  50014:Token 过期了;

            return Promise.reject('error');
        } else {
            return response.data;
        }
    },
    error => {
        console.log('err' + error) // for debug
        return Promise.reject(error)
    }
)

export default service
