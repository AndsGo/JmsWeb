<template>
  <div>
    <div>{{title}}</div>
    <p v-if="seen">看KA</p>
    <button v-on:click="seen=!seen">暴击</button>
    <button v-on:click="say('hi')">Say hi</button>
    <button v-on:click="say('what')">Say what</button>
    <div id="main" style="width: 600px; height: 400px"></div>
    <div v-if="Math.random() > 0.5">Now you see me</div>
    <div v-else>Now you don't</div>
    <ul>
      <li
        v-for="(item, index) in items"
        :key="index"
      >{{ parentMessage }} - {{ index }} - {{ item.message }}</li>
    </ul>
  </div>
</template>
<script>
export default {
  data() {
    return {
      title: "hello todo ssdsd",
      seen: true,
      parentMessage: "1",
      items: [{ message: "Foo" }, { message: "Bar" }],
      option: {
        title: {
          text: "cpu"
        },
        tooltip: {
          trigger: "axis"
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
          data: ["周一", "周二", "周三", "周四", "周五", "周六", "周日"]
        },
        yAxis: {
          type: "value"
        },
        series: [
          {
            name: "CPU 使用情况",
            type: "line",
            smooth: true,
            stack: "总量",
            data: [120, 132, 101, 134, 90, 230, 210, 12]
          },
          {
            name: "垃圾回收活动",
            type: "line",
            smooth: true,
            stack: "总量",
            data: [220, 182, 191, 234, 290, 330, 310]
          }
        ]
      }
    };
  },
  mounted() {
    this.set();
  },
  methods: {
    onclick() {
      this.seen = !this.seen;
    },
    say(message) {
      alert(message);
    },
    set() {
      let myChart = this.$echarts.init(document.getElementById("main"));
      myChart.clear(); //如果图表有修改需求建议加上此方法先清后画
      myChart.setOption(this.option);
    }
  }
};
</script>
