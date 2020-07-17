<template>
  <div class="hello">
    <el-tabs type="border-card" v-model="tab">
      <el-tab-pane label="概述">
        <overview/>
      </el-tab-pane>
      <el-tab-pane label="监视">
        <monitor/>
      </el-tab-pane>
      <el-tab-pane label="线程"  name="thread">
        <thread/>
      </el-tab-pane>
      <el-tab-pane label="抽样器">抽样器</el-tab-pane>
    </el-tabs>
  </div>
</template>

<script>
import overview from "@/components/overview/index";
import monitor from "@/components/monitor/index";
import thread from "@/components/thread/index";
export default {
  components: {
    overview,
    monitor,
    thread
  },
  name: "HelloWorld",
  data() {
    return {
      tab:'thread',
      interval: 0
    };
  },
  mounted() {
    //  this.interval = setInterval(this.getConnection,1000);
  },
  methods: {
    onClick: function() {
      this.flag = !this.flag;
      console.log("/todo");
      console.log(this.flag);
      if (this.flag) {
        this.$router.push({ path: "/Todo" });
      }
      // clearInterval(this.interval)
    }
  }
};
</script>

<!-- Add "scoped" attribute to limit CSS to this component only -->
<style scoped>
h1,
h2 {
  font-weight: normal;
}
ul {
  list-style-type: none;
  padding: 0;
}
li {
  display: inline-block;
  margin: 0 10px;
}
a {
  color: #42b983;
}
</style>
