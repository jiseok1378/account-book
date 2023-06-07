<template>
  <flex-container >
    <logo-container title="WooGa에 로그인" />
    <sign-card submitTitle="로그인" 
      @submit="submit"
    />
  </flex-container>
</template>

<script lang="ts">
import SignCard from "@/components/sign/SignCard.vue";

import LogoContainer from "@/components/sign/LogoContainer.vue";
import { FlexContainer } from "@/styled-components/StyledComponents";
import { defineComponent } from "vue";

interface UserInfo{
  userNm : string;
  accessToken : string;
  thurmbnailUrl : string;
}
export default defineComponent({
  components: { SignCard, FlexContainer, LogoContainer },
  data(){
    return {
      user:{
        userId : ""
      },
      res : ""
    }
  },
  methods:{
    addCookieUserInfo(userInfo : UserInfo){
      const dumyTokenExpired = 100;
      this.$cookies.set('accessToken', userInfo.accessToken, dumyTokenExpired, "/")
      this.$cookies.set('userNm', userInfo.userNm, dumyTokenExpired, "/")
      this.$cookies.set('userThurmbnail', userInfo.thurmbnailUrl, dumyTokenExpired, "/")
      this.$EventBus.$emit("chageUserNm", userInfo.userNm)
      this.$EventBus.$emit("chageUserThurmbnail", userInfo.thurmbnailUrl)
    },
    async submit(){
      if(process.env.NODE_ENV == 'development'){
        const dumyUserInfo : UserInfo = {
          accessToken: "totototokenenen",
          thurmbnailUrl: "https://img.freepik.com/free-photo/young-bearded-man-with-striped-shirt_273609-5677.jpg?w=2000",
          userNm : '테스트'
        };
        this.addCookieUserInfo(dumyUserInfo);
        this.$router.push("/")
      }
    }
  }
})
</script>

<style>

</style>
