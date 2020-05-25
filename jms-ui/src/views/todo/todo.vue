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
          text: "echarts"
        },
        toolbox: {
          show: true,
          feature: {
            saveAsImage: {
              show: true
            }
          }
        },
        legend: {
          data: ["销量"]
        },
        xAxis: {
          data: ["衬衫", "羊毛衫", "雪纺衫", "裤子", "高跟鞋", "袜子"]
        },
        yAxis: {},
        series: [
          {
            name: "销量",
            type: "bar",
            data: [5, 20, 36, 10, 10, 20]
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
