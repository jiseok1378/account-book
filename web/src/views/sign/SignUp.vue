<template>
    <flex-container>
      <logo-container 
        @upload-thumbnail="uploadThumbnail" 
        :thumbnail="thumbnail"
        :type="'signup'"
        title="WooGa에 회원가입" />
      <sign-card type="signup" submitTitle="회원가입" @submit="submit"/>
    </flex-container>
</template>
  
<script lang="ts">
import LogoContainer from "@/components/sign/LogoContainer.vue";
import SignCard from "@/components/sign/SignCard.vue";
import { FlexContainer } from '@/styled-components/StyledComponents';
import Vue from 'vue';
export default Vue.extend({
  components:{
    SignCard,
    LogoContainer,
    FlexContainer
  },
  data(){
    return{
      user:{
        userId: '',
        userPw: '',
        userNm: ''
      },
      allUsers: "",
      thumbnail: undefined
    }
  },
  methods:{
    async uploadThumbnail(e){
      const form = new FormData();
      form.append("thumbnail", e.target.files[0])
      const response = await this.$http.post("/api/upload/thumbnail", form);
      const uploadPath = response.data.data
      if( uploadPath.includes("ERROR") ){
        alert("업로드에 실패하였습니다.");
        return;
      }
      else{
        this.thumbnail=uploadPath;
      }
    },
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
      const allRes = await this.$http.get("/api/user/all")
      this.allUsers = JSON.stringify(allRes.data, null, 2)
    }
  }
})
</script>
  
<style>
  
</style>