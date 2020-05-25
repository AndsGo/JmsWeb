import Vue from 'vue'
import Vuex from 'vuex'

Vue.use(Vuex)//如果使用全局的 script 标签，则无须如此（手动安装）。

const state  = {
    count:0,
    peerSet:[]
}

const store = new Vuex.Store({
  state
})

export default store