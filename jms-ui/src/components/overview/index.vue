<template>
  <div>
    <el-row :gutter="20">
      <el-col :span="16">
        <div >hh  </div>
      </el-col>
      <el-col :span="8">
        <div >hh</div>
      </el-col>
    </el-row>
    <el-row :gutter="20">
      <el-col :span="8">
        <div >ss</div>
      </el-col>
      <el-col :span="8">
        <div >ss</div>
      </el-col>
      <el-col :span="4">
        <div >ss</div>
      </el-col>
      <el-col :span="4">
        <div >ss</div>
      </el-col>
    </el-row>
  </div>
</template>
<script>
export default {
  data() {
    return {
      title: "hello todo ssdsd"
    };
  },
  mounted() {
    this.interval = setInterval(this.getConnection,1000);
  },
  methods: {
    getConnection:function(){
      this.$axios.get('getPeerList', {
          params: {
            host: "172.36.0.231",
            port:60001
          }
        })
        .then(function (response) {
            
        })
        .catch(function (error) {
          console.log(error);
        })
    }
  }
};
</script>
