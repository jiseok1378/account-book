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
      const res = await this.$http.get("/api/user", [{key: "userId", value : this.user.userId}])
      this.res = JSON.stringify(res.data);
    }
  }
})
</script>

<style>

</style>
