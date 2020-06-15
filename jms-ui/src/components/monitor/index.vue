<template>
  <div>
    <el-row>
      <el-col :span="24">
        <div style="text-align: right">
          <el-checkbox v-model="cpuShow" @change="reDraw(1)">CPU</el-checkbox>
          <el-checkbox v-model="memoryShow" @change="reDraw(2)">内存</el-checkbox>
          <el-checkbox v-model="classShow" @change="reDraw(3)">类</el-checkbox>
          <el-checkbox v-model="threadShow" @change="reDraw(4)">线程</el-checkbox>
        </div>
      </el-col>
    </el-row>

    <el-row type="flex" id ="row1">
      <el-col :span="12">
        <el-tabs v-model="cpuTab">
          <el-tab-pane label="cpu" name="first">
            <p>cpu使用率:{{monitor.cpu.cpuUse.toFixed(2)}} gc使用率:{{monitor.cpu.gcUse.toFixed(2)}}</p>
            <div id="cpu" style="width:100%;height: 400px"></div>
          </el-tab-pane>
        </el-tabs>
      </el-col>
      <el-col :span="12">
        <el-tabs v-model="tab">
          <el-tab-pane label="堆" name="first">
            <p>堆大小:{{monitor?monitor.memory.heapMemoryUsage.max:0}} 已使用:{{monitor?monitor.memory.heapMemoryUsage.used:0}}</p>
            <div id="heap" style="width:100%;height: 400px"></div>
          </el-tab-pane>
          <el-tab-pane label="metaspace" name="second">
             <p>大小:{{monitor?monitor.memory.metaspaceUsage.committed:0}} 已使用:{{monitor?monitor.memory.metaspaceUsage.used:0}}</p>
            <div id="metaspace" style="width:100%;height: 400px"></div>
          </el-tab-pane>
        </el-tabs>
      </el-col> 
    </el-row>
        <el-divider></el-divider>
    <el-row type="flex" id ="row2">
      <el-col :span="12">
        <p>当前使用:{{monitor?monitor.classes.loadedClassCount:0}} 已装入总数:{{monitor?monitor.classes.totalLoadedClassCount:0}}</p>
        <div id="class" style="width:100%;height: 400px"></div>
      </el-col>
      <el-col :span="12">
        <p>守护线程:{{monitor?monitor.thread.daemonThreadCount:0}} 实时线程:{{monitor?monitor.thread.threadCount:0}}</p>
        <div id="thread" style="width:100%;height: 400px"></div>
      </el-col>
    </el-row>
  </div>
</template>
<script>
export default {
  data() {
    return {
      title: "监控",
      cpuTab: "first",
      tab: "first",
      row1Width:400,
      row2Width:400,
      interval: null,
      cpuShow: true,
      memoryShow: true,
      classShow: true,
      threadShow: true,
      cpuChart: null,
      heapChart: null,
      metaspaceChart: null,
      classChart: null,
      threadChart: null,
      cpuOption: {
        title: {
          text: "cpu"
        },
        tooltip: {
          trigger: "axis",
          axisPointer: {
            type: "cross",
            label: {
              backgroundColor: "#6a7985"
            }
          }
        },
        legend: {
          data: ["CPU 使用情况", "垃圾回收活动"]
        },
        grid: {
          left: "3%",
          right: "4%",
          bottom: "3%",
          containLabel: true
        },
        toolbox: {
          feature: {
            saveAsImage: {}
          }
        },
        xAxis: {
          type: "category",
          boundaryGap: false,
          data: []
        },
        yAxis: {
          type: "value",
          axisLabel: {
            formatter: "{value} %"
          }
        },
        series: [
          {
            name: "CPU 使用情况",
            type: "line",
            smooth: true,

            data: []
          },
          {
            name: "垃圾回收活动",
            type: "line",
            smooth: true,

            data: []
          }
        ]
      },
      heapOption: {
        title: {
          text: "堆"
        },
        tooltip: {
          trigger: "axis",
          axisPointer: {
            type: "cross",
            label: {
              backgroundColor: "#6a7985"
            }
          }
        },
        legend: {
          data: ["堆大小", "使用的堆"]
        },
        grid: {
          left: "3%",
          right: "4%",
          bottom: "3%",
          containLabel: true
        },
        toolbox: {
          feature: {
            saveAsImage: {}
          }
        },
        xAxis: {
          type: "category",
          boundaryGap: false,
          data: []
        },
        yAxis: {
          type: "value",
            axisLabel: {
                formatter: '{value} M'
            }
        },
        series: [
          {
            name: "堆大小",
            type: "line",
            smooth: true,
            areaStyle: {},

            data: []
          },
          {
            name: "使用的堆",
            type: "line",
            smooth: true,
            areaStyle: {},

            data: []
          }
        ]
      },
      metaspaceOption: {
        title: {
          text: "Metaspace"
        },
        tooltip: {
          trigger: "axis",
          axisPointer: {
            type: "cross",
            label: {
              backgroundColor: "#6a7985"
            }
          }
        },
        legend: {
          data: ["Metaspace 大小", "使用的Metaspace"]
        },
        grid: {
          left: "3%",
          right: "4%",
          bottom: "3%",
          containLabel: true
        },
        toolbox: {
          feature: {
            saveAsImage: {}
          }
        },
        xAxis: {
          type: "category",
          boundaryGap: false,
          areaStyle: {},
          data: []
        },
        yAxis: {
          type: "value",
            axisLabel: {
                formatter: '{value} M'
            }
        },
        series: [
          {
            name: "Metaspace 大小",
            type: "line",
            smooth: true,
            areaStyle: {},
            data: []
          },
          {
            name: "使用的Metaspace",
            type: "line",
            smooth: true,
            areaStyle: {},
            data: []
          }
        ]
      },
      classOption: {
        title: {
          text: "类"
        },
        tooltip: {
          trigger: "axis",
          axisPointer: {
            type: "cross",
            label: {
              backgroundColor: "#6a7985"
            }
          }
        },
        legend: {
          data: ["已经装入类的总数", "当前加载类数量"]
        },
        grid: {
          left: "3%",
          right: "4%",
          bottom: "3%",
          containLabel: true
        },
        toolbox: {
          feature: {
            saveAsImage: {}
          }
        },
        xAxis: {
          type: "category",
          boundaryGap: false,
          data: []
        },
        yAxis: {
          type: "value"
        },
        series: [
          {
            name: "已经装入类的总数",
            type: "line",
            smooth: true,
            data: []
          },
          {
            name: "当前加载类数量",
            type: "line",
            smooth: true,
            data: []
          }
        ]
      },
      threadOption: {
        title: {
          text: "线程"
        },
        tooltip: {
          trigger: "axis",
          axisPointer: {
            type: "cross",
            label: {
              backgroundColor: "#6a7985"
            }
          }
        },
        legend: {
          data: ["实时线程", "守护线程"]
        },
        grid: {
          left: "3%",
          right: "4%",
          bottom: "3%",
          containLabel: true
        },
        toolbox: {
          feature: {
            saveAsImage: {}
          }
        },
        xAxis: {
          type: "category",
          boundaryGap: false,
          data: []
        },
        yAxis: {
          type: "value"
        },
        series: [
          {
            name: "实时线程",
            type: "line",
            smooth: true,
            data: []
          },
          {
            name: "守护线程",
            type: "line",
            smooth: true,
            data: []
          }
        ]
      },
      monitor: null
    };
  },
  mounted() {
    if (!this.$store.monitor) {
      this.$store.monitor = {};
    }
    this.interval = setInterval(this.refresh, 5000);
    this.init()
     window.addEventListener('resize', e => { this.reDraw() }, false);
  },
  methods: {
    refresh: function() {
      if (this.$store.peerSet) {
        this.$store.peerSet.forEach(element => {
          this.getMonitor(element);
        });
        this.draw();
      }
    },
    getMonitor: function(peer) {
      if (peer && peer.host && peer.port) {
        this.$axios
          .get("getMonitor", {
            params: peer
          })
          .then(response => {
            let monitorTmp = response.data.result;
            if (
              this.$store.currentPeer &&
              this.$store.currentPeer.host == peer.host
            ) {
              this.monitor = monitorTmp;
            }
            if (
              this.$store.monitor[peer.host] &&
              this.$store.monitor[peer.host].time.length > 400
            ) {
              //如果缓存大于400,移除缓存
              delete this.$store.monitor[peer.host];
            }
            if (!this.$store.monitor[peer.host]) {
              this.$store.monitor[peer.host] = {
                time: [],
                cpu: {
                  cpuData: [],
                  gcData: []
                },
                memory: {
                  heapSizeData: [],
                  heapUseData: [],
                  metaspaceSizeData: [],
                  metaspaceUseData: []
                },
                classes: {
                  loadedData: [],
                  totalData: []
                },
                thread: {
                  countData: [],
                  daemonData: []
                }
              };
            }
            this.$store.monitor[peer.host].time.push(monitorTmp.time);
            //cpu信息
            this.$store.monitor[peer.host].cpu.cpuData.push(
              monitorTmp.cpu.cpuUse
            );
            this.$store.monitor[peer.host].cpu.gcData.push(
              monitorTmp.cpu.gcUse
            );
            //内存信息
            this.$store.monitor[peer.host].memory.heapSizeData.push(
              (monitorTmp.memory.heapMemoryUsage.max/1024/1024).toFixed(2)
            );
            this.$store.monitor[peer.host].memory.heapUseData.push(
              (monitorTmp.memory.heapMemoryUsage.used/1024/1024).toFixed(2)
            );
            this.$store.monitor[peer.host].memory.metaspaceSizeData.push(
              (monitorTmp.memory.metaspaceUsage.committed/1024/1024).toFixed(2)
            );
            this.$store.monitor[peer.host].memory.metaspaceUseData.push(
              (monitorTmp.memory.metaspaceUsage.used/1024/1024).toFixed(2)
            );
            //类加载信息
            this.$store.monitor[peer.host].classes.loadedData.push(
              monitorTmp.classes.loadedClassCount
            );
            this.$store.monitor[peer.host].classes.totalData.push(
              monitorTmp.classes.totalLoadedClassCount
            );
            //线程信息
            this.$store.monitor[peer.host].thread.daemonData.push(
              monitorTmp.thread.daemonThreadCount
            );
            this.$store.monitor[peer.host].thread.countData.push(
              monitorTmp.thread.threadCount
            );
          })
          .catch(error => {
            this.$message.error(
              error.response ? error.response.data : error.message
            );
          });
      }
    },
    init: function() {
      //初始化echart
      if(!document.getElementById("cpu")){
        return
      }
      this.cpuChart = this.$echarts.init(document.getElementById("cpu"));
      this.cpuChart.setOption(this.cpuOption, true);
      this.heapChart = this.$echarts.init(document.getElementById("heap"));
      this.heapChart.setOption(this.heapOption, true);
      this.metaspaceChart = this.$echarts.init(
        document.getElementById("metaspace")
      );
      this.metaspaceChart.setOption(this.metaspaceOption, true);
      this.classChart = this.$echarts.init(document.getElementById("class"));
      this.classChart.setOption(this.classOption, true);
      this.threadChart = this.$echarts.init(document.getElementById("thread"));
      this.threadChart.setOption(this.threadOption, true);
    },
    draw: function() {
      // let myChart = this.$echarts.init(document.getElementById("cpu"));
      // myChart.clear(); //如果图表有修改需求建议加上此方法先清后画
      if (this.$store.currentPeer && this.$store.currentPeer.port&&this.cpuChart) {
        let key = this.$store.currentPeer.host;
        if(!this.$store.monitor[key]){
            return
        }
        this.cpuOption.xAxis.data = this.$store.monitor[key].time;
        this.heapOption.xAxis.data = this.$store.monitor[key].time;
        this.metaspaceOption.xAxis.data = this.$store.monitor[key].time;
        this.classOption.xAxis.data = this.$store.monitor[key].time;
        this.threadOption.xAxis.data = this.$store.monitor[key].time;
        //cpu
        this.cpuOption.series[0].data = this.$store.monitor[key].cpu.cpuData;
        this.cpuOption.series[1].data = this.$store.monitor[key].cpu.gcData;
        //内存
        this.heapOption.series[0].data = this.$store.monitor[
          key
        ].memory.heapSizeData;
        this.heapOption.series[1].data = this.$store.monitor[
          key
        ].memory.heapUseData;
        this.metaspaceOption.series[0].data = this.$store.monitor[
          key
        ].memory.metaspaceSizeData;
        this.metaspaceOption.series[1].data = this.$store.monitor[
          key
        ].memory.metaspaceUseData;
        //类
        this.classOption.series[0].data = this.$store.monitor[
          key
        ].classes.totalData;
        this.classOption.series[1].data = this.$store.monitor[
          key
        ].classes.loadedData;
        //线程
        this.threadOption.series[0].data = this.$store.monitor[
          key
        ].thread.countData;
        this.threadOption.series[1].data = this.$store.monitor[
          key
        ].thread.daemonData;
        this.cpuChart.setOption(this.cpuOption, true);
        this.heapChart.setOption(this.heapOption, true);
        this.metaspaceChart.setOption(this.metaspaceOption, true);
        this.classChart.setOption(this.classOption, true);
        this.threadChart.setOption(this.threadOption, true);
      }
      if(!this.cpuChart){
        this.reDraw()
      }
    },
    stop: function() {
      clearInterval(this.interval);
    },
    reDraw:function(type){
        if(!document.getElementById("row1")){
          return
        }
        let width = document.getElementById("row1").offsetWidth
        this.row1Width=width/((this.cpuShow?1:0)+(this.memoryShow?1:0))
        width = document.getElementById("row2").offsetWidth
        this.row2Width=width/((this.classShow?1:0)+(this.threadShow?1:0))
        this.$forceUpdate();
        this.init()
    }
  }
};
</script>
