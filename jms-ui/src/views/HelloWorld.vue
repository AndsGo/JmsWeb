<template>
  <div class="hello">
    <h1>{{ msg }}</h1>
    <h2>Essential Links</h2>
    <button v-on:click="onClick()">点我</button>
  </div>
</template>

<script>
export default {
  name: 'HelloWorld',
  data () {
    return {
      msg: 'Welcome to Your Vue.js App hello world',
      flag : true,
      interval:0
    }
  },
  mounted () {
  //  this.interval = setInterval(this.getConnection,1000);
  },
  methods:{
    onClick : function(){
        this.flag=!this.flag
        console.log("/todo")
        console.log(this.flag)
        if(this.flag){
          this.$router.push({ path: '/Todo' })
        }
        // clearInterval(this.interval)
    },
    getConnection:function(){
      this.$axios.get('getConnection', {
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
}
</script>

<!-- Add "scoped" attribute to limit CSS to this component only -->
<style scoped>
h1, h2 {
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
