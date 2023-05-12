<template>
    <div>
      <div><p>User Id: </p><input v-model="user.userId"></div>
      <div><p>User Pw: </p><input v-model="user.userPw"></div>
      <div><p>User Nm: </p><input v-model="user.userNm"></div>
      <div><button @click="submit">submit</button></div>
      <div>{{allUsers}}</div>
    </div>
  </template>
  
  <script lang="ts">
  import { defineComponent } from "vue";
  export default defineComponent({
    data(){
      return{
        user:{
          userId: '',
          userPw: '',
          userNm: ''
        },
        allUsers: "",
      }
    },
    methods:{
      async submit(){
        const response = await this.$http.post("/api/user", this.user)
        console.log(response);
        const allRes = await this.$http.get("/api/user/all")
        this.allUsers = JSON.stringify(allRes.data, null, 2)
      }
    }
  })
  </script>
  
  <style>
  
  </style>