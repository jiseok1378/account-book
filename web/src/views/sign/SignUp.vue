<template>
    <div>
      <div><p>User Id: </p><v-text-field v-model="user.userId" /></div>
      <div><p>User Pw: </p><v-text-field v-model="user.userPw" /></div>
      <div><p>User Nm: </p><v-text-field v-model="user.userNm" /></div>
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
      setCookie(cookie_name : string , value : any, days : number) {
  var exdate = new Date();
  exdate.setDate(exdate.getDate() + days);
  // 설정 일수만큼 현재시간에 만료값으로 지정

  var cookie_value = escape(value) + ((days == null) ? '' : '; expires=' + exdate.toUTCString());
  document.cookie = cookie_name + '=' + cookie_value;
},
      async submit(){
        this.setCookie("abc", "abc", 3);
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