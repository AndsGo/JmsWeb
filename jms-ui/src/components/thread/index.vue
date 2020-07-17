<template>
  <div>
    <el-row type="flex" id="row2">
      <el-col :span="24">
        <div id="thread" style="width:100%;height: 400px"></div>
      </el-col>
    </el-row>
  </div>
</template>
<script>
export default {
  data() {
    return {
      title: "线程",
      categories: ['aa','bb'],
      data: [],
      dataCount:10,
      startTime:+new Date(),
      types: [
        { name: "JS Heap", color: "#7b9ce1" },
        { name: "Documents", color: "#bd6d6c" },
        { name: "Nodes", color: "#75d874" },
        { name: "Listeners", color: "#e0bc78" },
        { name: "GPU Memory", color: "#dc77dc" },
        { name: "GPU", color: "#72b362" }
      ],
      threadOption: {
        tooltip: {
          formatter: function(params) {
            return params.marker + params.name + ": " + params.value[3] + " ms";
          }
        },
        title: {
          text: "Profile",
          left: "center"
        },
        dataZoom: [
          {
            type: "slider",
            filterMode: "weakFilter",
            showDataShadow: false,
            top: 400,
            height: 10,
            borderColor: "transparent",
            backgroundColor: "#e2e2e2",
            handleIcon:
              "M10.7,11.9H9.3c-4.9,0.3-8.8,4.4-8.8,9.4c0,5,3.9,9.1,8.8,9.4h1.3c4.9-0.3,8.8-4.4,8.8-9.4C19.5,16.3,15.6,12.2,10.7,11.9z M13.3,24.4H6.7v-1.2h6.6z M13.3,22H6.7v-1.2h6.6z M13.3,19.6H6.7v-1.2h6.6z", // jshint ignore:line
            handleSize: 20,
            handleStyle: {
              shadowBlur: 6,
              shadowOffsetX: 1,
              shadowOffsetY: 2,
              shadowColor: "#aaa"
            },
            labelFormatter: ""
          },
          {
            type: "inside",
            filterMode: "weakFilter"
          }
        ],
        grid: {
          height: 300
        },
        xAxis: {
          min: new Date().getTime(),
          scale: true,
          axisLabel: {
            formatter: function(val) {
              return new Date(val).Format("hh:mm:ss");
            }
          }
        },
        yAxis: {
          data:[]
        },
        series: [
          {
            type: "custom",
            renderItem: this.renderItem,
            itemStyle: {
              opacity: 0.8
            },
            encode: {
              x: [1, 2],
              y: 0
            },
            data:[]
          }
        ]
      }
    };
  },
  mounted() {
    if (!this.$store.monitor) {
      this.$store.monitor = {};
    }
    this.init();
    // this.interval = setInterval(this.refresh, 5000);
    
  },
  methods: {
    refresh: function() {
        if(!document.getElementById("thread")){
            debugger
            return
        }
      this.$echarts.util.each(this.categories, (category, index) => {
        var baseTime = new Date().getTime();
        for (var i = 0; i < this.dataCount; i++) {
          var typeItem = this.types[Math.round(Math.random() * (this.types.length - 1))];
          var duration = 1000;
          this.data.push({
            name: typeItem.name,
            value: [index, baseTime, (baseTime += duration), duration],
            itemStyle: {
              normal: {
                color: typeItem.color
              }
            }
          });
        }
      });
      this.threadOption.series[0].data=this.data
      this.threadChart.setOption(this.threadOption, true);
    },
    init: function() {
        if(!document.getElementById("thread")){
            debugger
            return
        }
      this.$echarts.util.each(this.categories, (category, index) => {
        var baseTime = new Date().getTime();
        for (var i = 0; i < this.dataCount; i++) {
          var typeItem = this.types[Math.round(Math.random() * (this.types.length - 1))];
          var duration = 1000;
          this.data.push({
            name: typeItem.name,
            value: [index, baseTime, (baseTime += duration), duration],
            itemStyle: {
              normal: {
                color: typeItem.color
              }
            }
          });
        }
      });
      this.threadOption.series[0].data=this.data
      this.threadOption.yAxis.data=this.categories
      this.threadChart = this.$echarts.init(document.getElementById("thread"));
      this.threadChart.setOption(this.threadOption, true);
    },
    renderItem: function(params, api) {
      var categoryIndex = api.value(0);
      var start = api.coord([api.value(1), categoryIndex]);
      var end = api.coord([api.value(2), categoryIndex]);
      var height = api.size([0, 1])[1] * 0.6;

      var rectShape = this.$echarts.graphic.clipRectByRect(
        {
          x: start[0],
          y: start[1] - height / 2,
          width: end[0] - start[0],
          height: height
        },
        {
          x: params.coordSys.x,
          y: params.coordSys.y,
          width: params.coordSys.width,
          height: params.coordSys.height
        }
      );

      return (
        rectShape && {
          type: "rect",
          shape: rectShape,
          style: api.style()
        }
      );
    }
  }
};
</script>
