<template>
  <div id="app">
    <el-container style="border: 1px solid #eee">
      <el-aside width="200px" style="background-color: rgb(238, 241, 246)">
        <el-menu>
          <el-menu-item v-for="item in routes" :key="item.path" @click="go(item.path)">{{item.name}}</el-menu-item>
        </el-menu>
      </el-aside>
      <el-main>
        <router-view />
      </el-main>
    </el-container>
  </div>
</template>

<script>
export default {
  name: "App",
  data() {
    return {
      routes: []
    };
  },
  mounted() {
    this.getConnection();
      this.routes = this.$router.options.routes;
  },
  methods: {
    go(param){
      this.$router.push({ path: param })
    },
    getConnection:function(){
      this.$axios.get('getPeerList', {
          params: {
            host: "172.36.0.231",
            port:60001
          }
        })
        .then(function (response) {
          console.log(response);
        })
        .catch(function (error) {
          console.log(error);
        })
    }
  }
};
</script>

<style>
#app {
  font-family: "Avenir", Helvetica, Arial, sans-serif;
  -webkit-font-smoothing: antialiased;
  -moz-osx-font-smoothing: grayscale;
  text-align: center;
  color: #2c3e50;
  margin-top: 60px;
}
</style>
