/*import Vue from 'vue'
import ElementUI from 'element-ui';
import 'element-ui/lib/theme-chalk/index.css';
Vue.use(ElementUI);*/

import App from './App.vue'
import './registerServiceWorker'
import router from './router'
import './assets/icon/iconfont.css'
import mavonEditor from 'mavon-editor'
import 'mavon-editor/dist/css/index.css'

Vue.use(mavonEditor)

Vue.config.productionTip = false

new Vue({
  router,

  render: h => h(App)
}).$mount('#app')
