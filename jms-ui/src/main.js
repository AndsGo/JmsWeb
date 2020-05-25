// The Vue build version to load with the `import` command
// (runtime-only or standalone) has been set in webpack.base.conf with an alias.
import Vue from 'vue'
import App from './App'
import router from './router'

Vue.config.productionTip = false
//引入ui
import ElementUI from 'element-ui'
import 'element-ui/lib/theme-chalk/index.css'
Vue.use(ElementUI)
//引入axios,绑定到axios原型链上
import axios from 'axios'
axios.defaults.withCredentials = true // 若跨域请求需要带 cookie 身份识别
axios.defaults.headers.post['Content-Type'] = 'application/x-www-form-urlencoded'
//设置统一请求地址
console.log(process.env.API_HOST)
axios.defaults.baseURL = process.env.API_HOST;
Vue.prototype.$axios = axios

//POST传参序列化(添加请求拦截器)
axios.interceptors.request.use((config) => {
  //在发送请求之前如果为post序列化请求参数
  if (config.method === 'post') {
      config.data = qs.stringify(config.data);
  }
  return config;
}, (error) => {
  Toast({
      message: '传参错误，请检查！',
      duration: 1000
  });
  return Promise.reject(error);
});
//相应拦截器
axios.interceptors.response.use( (response) => {
  // 对响应数据做点什么
  // response 是请求回来的数据
  if(response.data.succ=='fail'){
    this.$message.error(response.data.msg);
  }else{
    return response;
  }
}, function (error) {
  // 对响应错误做点什么
  return Promise.reject(error)
}
)

//引入echarts
import echarts from 'echarts'
Vue.prototype.$echarts = echarts

//引入vuex  store
import store from './store'

/* eslint-disable no-new */
new Vue({
  el: '#app',
  store,
  router,
  components: { App },
  template: '<App/>'
})
