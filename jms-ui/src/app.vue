<template>
  <div id="app">
    <el-container style="border: 1px solid #eee">
      <el-aside width="200px" style="background-color: rgb(238, 241, 246)">
        <el-menu>
          <el-row>
          <el-tag>添加节点：</el-tag>
            <el-button type="primary" icon="el-icon-plus" size="small" @click="dialogVisible = true"></el-button>
          </el-row>
          <el-menu-item v-for="item in routes" :key="item.host" @click="go(item)">{{item.host}}:{{item.port}}</el-menu-item>
        </el-menu>
      </el-aside>
      <el-main>
        <router-view />
      </el-main>
    </el-container>
    <el-dialog title="添加jmx节点" :visible.sync="dialogVisible" width="30%">
      <el-row :gutter="5">
        <el-col :span="18">
          <el-input placeholder="ip" v-model="host" clearable></el-input>
        </el-col>
        <el-col :span="6">
          <el-input placeholder="端口" v-model="port" clearable></el-input>
        </el-col>
      </el-row>
      <span slot="footer" class="dialog-footer">
        <el-button @click="dialogVisible = false">取 消</el-button>
        <el-button type="primary" @click="addPeer">确 定</el-button>
      </span>
    </el-dialog>
  </div>
</template>

<script>
export default {
  name: "App",
  data() {
    return {
      dialogVisible: false,
      routes: [],
      host: "",
      port: ""
    };
  },
  mounted() {
    this.getConnection();
    // this.$router.push({ path: param });
  },
  methods: {
    go(param) {
      this.$story.peerSet=param
    },
    getConnection: function() {
      this.$axios
        .get("getPeerList")
        .then((response) =>{
          this.routes = response.data.result
          // this.routes = this.$store.peerSet
        })
        .catch((error)=> {
          console.log(error)
        });
    },
    addPeer() {
       this.$axios
        .get("getConnection", {
          params: {
            host: this.host,
            port: this.port
          }
        })
        .then((response) => {
          this.routes.push({
            host: this.host,
            port: this.port
          })
        })
        .catch((error) => {
         this.$message.error('出错了');
        });
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
