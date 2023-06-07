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
    async submit(){
      if(process.env.NODE_ENV == 'development'){
        const dumyToken = "totototokenenen";
        const dumyTokenExpired = 100;
        const dumyThurmbnail = "https://img.freepik.com/free-photo/young-bearded-man-with-striped-shirt_273609-5677.jpg?w=2000";
        this.$cookies.set('accessToken', dumyToken, dumyTokenExpired, "/")
        this.$cookies.set('userNm', "테스트", dumyTokenExpired, "/")
        this.$cookies.set('userThurmbnail', dumyThurmbnail, dumyTokenExpired, "/")
        this.$router.push("/")
        this.$EventBus.$emit("chageUserNm", "테스트")
        this.$EventBus.$emit("chageUserThurmbnail", dumyThurmbnail)
      }
    }
  }
})
</script>

<style>

</style>
