<template>
  <div class="main-viewer home-main" >
    <template>
      <main-title> WooGa(우리 가계부 써요)에 오신것을 환영해요 :) </main-title>
      <sub-title v-for="( value, index ) in subTitle" :key="index">{{ value }}</sub-title>
      <sub-title  v-if="!accessToken"><router-link to="/signup">회원가입 하러가기</router-link></sub-title>
      <!-- <label for="thumbnail-upload"></label>
      <v-img :src="testSrc" />
      <input type="file" id="thumbnail-upload" @change="uploadThumbnail"/> -->
      <v-icon class="home-main-icon" color="primary"> mdi-notebook-heart-outline </v-icon>
    </template>
  </div>
</template>

<script lang="ts">
import { MainTitle, SubTitle } from '@/styled-components/HomeStyle';
import { defineComponent } from 'vue';
export default defineComponent({
  components:{ MainTitle, SubTitle,  },
  
  data(){
    return {
      testSrc: "",
      accessToken: this.$cookies.get('accessToken'),
      subTitle:[
        "WooGa에서는 페어를 맺어 가계부를 입력하고 공유할 수 있어요.",
        "가계부를 입력하시면 통계를 작성하여 보여드릴게요!",
        "통계는 설정하신 기간별로 계산하여 워드클라우드, 그래프 등으로 확인할 수 있어요 (추후 더 추가될 예정입니다!)",
        "WooGa에 가입한 후 페어를 맺고 서로간의 가계부를 작성해보세요!"
      ]    
    }
  },
  methods:{
    async uploadThumbnail(e){
      const form = new FormData();
      form.append("thumbnail", e.target.files[0])
      const response = await this.$http.post("/api/upload/thumbnail", form);
      this.testSrc = (response.data.data)
    }
  }
});
</script>

<style lang="scss" scoped>
.home-main{
  padding-top: 60px !important;
}
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