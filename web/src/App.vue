<template>
  <v-app>
    <top-banner id="top-banner-id"
      @click-left-btn="changeRoute({name: 'home'})"
      @click-right-btn="changeRoute({name: 'signin'})"/>
    <v-main>
      <!-- 라우터 상태 유지 (리스트 재로딩 방지) -->
      <!-- <keep-alive>  -->
        <router-view/>
      <!-- </keep-alive> -->
    </v-main>
    <v-avatar class="floating-btn" @click="goTop">
      <v-icon>mdi-transfer-up</v-icon>
    </v-avatar>
  </v-app>
</template>

<script lang="ts">
import { defineComponent } from 'vue';
import TopBanner from './components/global/banner/TopBanner.vue';

interface URLPath{
  name?: string;
  path?: string;
  params?: any;
}

export default defineComponent({
  name: 'App',
  components: {
    TopBanner
  },

  beforeMount(){
    this.$EventBus.$on('changeRoute', this.changeRoute);
  },
  methods: {
    changeRoute({name, params, path} : URLPath){
      if(this.$route.name !== name){
        this.$router.push({name: name, params: params, path: path})
      }
    },
    goTop(){
      this.$vuetify.goTo('#top-banner-id')
    }
  }
});
</script>

<style lang="scss" scoped>
.floating-btn{
  position: fixed;
  bottom: 20px;
  right: 10px;
  cursor: pointer;
  box-shadow : 4px 5px 8px -5px;
  background: var(--v-primary-base);
  &:hover{
    background: var(--v-primary-lighten1);
  }
}
</style>
