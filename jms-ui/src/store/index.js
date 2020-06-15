import Vue from 'vue'
import Vuex from 'vuex'

Vue.use(Vuex)//如果使用全局的 script 标签，则无须如此（手动安装）。

const data  = {
    count:0,
    peerSet:[],
    currentPeer:{host:''},
    monitor:{
      mointorData:{
        time:[],
        cpu:{
          cpuData:[],
          gcData:[]
        },
        memory:{
          heapSizeData:[],
          heapUseData:[],
          metaspaceSizeData:[],
          metaspaceUseData:[]
        },
        classes:{
          loadedData:[],
          totalData:[]
        },
        thread:{
          countData:[],
          daemonData:[]
        }
      }
    },
    overView:{}
}

const store = new Vuex.Store({
  data
})

export default store