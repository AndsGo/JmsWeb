<template>
  <div class="hello">
    <el-tabs type="border-card">
      <el-tab-pane label="用户管理">
        <overview/>
      </el-tab-pane>
      <el-tab-pane label="配置管理">配置管理</el-tab-pane>
      <el-tab-pane label="角色管理">角色管理</el-tab-pane>
      <el-tab-pane label="定时任务补偿">定时任务补偿</el-tab-pane>
    </el-tabs>
  </div>
</template>

<script>
import overview from "@/components/overview/index";
export default {
  components: {
    overview
  },
  name: "HelloWorld",
  data() {
    return {
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
