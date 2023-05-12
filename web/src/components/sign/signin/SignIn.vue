<template>
  <div>
    <div>User Id: <input v-model="user.userId" /></div> 
    <div><button @click="submit">sumbit</button></div>
    <div>{{res}}</div>
  </div>
</template>

<script lang="ts">
import { defineComponent } from "vue";


export default defineComponent({
  data(){
    return {
      user:{
        userId : ""
      },
      res : ""
    }
  },
  methods:{
    async submit(){
      const res = await this.$http.get("/api/user", [{key: "userId", value : this.user.userId}])
      this.res = JSON.stringify(res.data);
    }
  }
})
</script>

<style>

</style>
