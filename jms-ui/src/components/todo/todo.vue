<template>
<div>
    <div>{{title}}</div>
    <p v-if="seen" >看KA</p>
    <button v-on:click="seen=!seen">暴击</button>
    <button v-on:click="say('hi')">Say hi</button>
  <button v-on:click="say('what')">Say what</button>

    <div v-if="Math.random() > 0.5">
    Now you see me
    </div>
    <div v-else>
    Now you don't
    </div>
    <ul >
        <li v-for="(item, index) in items" :key="index">
            {{ parentMessage }} - {{ index }} - {{ item.message }}
        </li>
    </ul>
</div>
</template>
<script>
export default {
    data(){
        return{
            title : 'hello todo ssdsd',
            seen : true,
            parentMessage:"1",
            items : [
                { message: 'Foo' },
                { message: 'Bar' }
            ]
        }
    },
    methods:{
        onclick(){
            this.seen=!this.seen
        },
        say(message) {
            alert(message)
        }

    }
}
</script>
