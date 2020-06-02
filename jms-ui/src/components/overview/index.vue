<template>
  <div>
    <el-container>
      <el-button type="primary" icon="el-icon-refresh" @click="refresh()" size="mini"></el-button>
      <el-header height="180px" style="text-align: left; font-size: 12px ">
        <p>
          <span style="font-weight:bold">PID:</span>
          {{overview.pid}}
        </p>
        <p>
          <span style="font-weight:bold">主机:</span>
          {{peer.host}}
        </p>
        <p>
          <span style="font-weight:bold">JVM:</span>
          {{overview.vmName}}
        </p>
        <p>
          <span style="font-weight:bold">jvm供应商:</span>
          {{overview.vmVendor}}
        </p>
        <p>
          <span style="font-weight:bold">开始运行时间:</span>
          {{overview.startTime?new Date(overview.startTime):""}}
        </p>
        <p>
          <span style="font-weight:bold">正常运行时间:</span>
          {{overview.uptime}}ms
        </p>
        <el-divider></el-divider>
      </el-header>
      <el-main style="text-align: left; font-size: 12px ;" >
        <el-tabs type="card">
          <el-tab-pane label="jvm参数" name="first">
            <div >
              <p v-for="item in overview.inputArguments" :key="item+'j'">{{item}}</p>
            </div>
          </el-tab-pane>
          <el-tab-pane label="系统参数" name="second">
            <div >
              <p v-for="(v,k) in overview.systemProperties" :key="k+'s'">
                <span style="color: #BF7158;font-weight:bold">{{k}}</span>
                = {{v}}
              </p>
            </div>
          </el-tab-pane>
        </el-tabs>
      </el-main>
    </el-container>
  </div>
</template>
<script>
export default {
  data() {
    return {
      title: "概述",
      overview: {},
      peer: { host: "" }
    };
  },
  mounted() {
    this.interval = setInterval(this.refresh, 10000);
  },
  computed: {
    getPeer() {
      return this.$store.currentPeer;
    }
  },
  watch: {
    getPeer() {
      this.refresh();
    }
  },
  methods: {
    refresh: function() {
      this.peer = this.$store.currentPeer;
      this.getOverview();
    },
    getOverview: function() {
      if (this.peer && this.peer.host) {
        this.$axios
          .get("getOverview", {
            params: {
              host: this.peer.host,
              port: this.peer.port
            }
          })
          .then(response => {
            this.overview = response.data.result;
          })
          .catch(error => {
            this.$message.error(
              error.response ? error.response.data : error.message
            );
          });
      }
    }
  }
};
</script>
