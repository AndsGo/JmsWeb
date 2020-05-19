import Vue from 'vue'
import Router from 'vue-router'
import index from '@/views/index'
import Todo from '@/views/todo/todo'
Vue.use(Router)

export default new Router({
  routes: [
    {
      path: '/',
      name: 'index',
      component: index
    },
    {
      path: '/todo',
      name: 'Todo',
      component: Todo
    }
  ]
})
