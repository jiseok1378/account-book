<template>
  <home-container class="home">
      <main-title>WooGa(우리 가계부 써요)에 오신것을 환영해요 :) </main-title>
      <sub-title v-for="(value, index) in subTitle" :key="index">{{value}}</sub-title>
      <sub-title><router-link to="/signup">회원가입 하러가기</router-link></sub-title>
      <v-icon class="home-main-icon" color="primary" >mdi-notebook-heart-outline</v-icon>
  </home-container>
</template>

<script lang="ts">
import { HomeContainer, MainTitle, SubTitle } from '@/styled-components/HomeStyle';
import { defineComponent } from 'vue';
export default defineComponent({
  components:{ HomeContainer, MainTitle, SubTitle },
  
  data(){
    return {
      subTitle:[
        "WooGa에서는 페어를 맺어 가계부를 입력하고 공유할 수 있어요.",
        "가계부를 입력하시면 통계를 작성하여 보여드릴게요!",
        "통계는 설정하신 기간별로 계산하여 워드클라우드, 그래프 등으로 확인할 수 있어요 (추후 더 추가될 예정입니다!)",
        "WooGa에 가입한 후 페어를 맺고 서로간의 가계부를 작성해보세요!"
      ]    
    }
  }
});
</script>

<style lang="scss" scoped>
.home-main-icon{
  position: absolute;
  right: 100px;
  top: 300px;
  font-size: 50vh;
}
@media (max-width: 1500px) {
  .home-main-icon{
    display: none;
  }
}
</style>