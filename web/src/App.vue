<template>
  <v-app>
    <top-banner id="top-banner-id"
      @click-left-btn="changeRoute('/')"
      @click-right-btn="changeRoute('/signin')"/>
    <v-main>
      <router-view/>
    </v-main>
    <v-list-item-avatar class="floating-btn" @click="goTop">
      <v-icon>mdi-transfer-up</v-icon>
    </v-list-item-avatar>
  </v-app>
</template>

<script lang="ts">
import { defineComponent } from 'vue';
import TopBanner from './components/global/banner/TopBanner.vue';

export default defineComponent({
  name: 'App',
  components: {
    TopBanner
  },

  beforeMount(){
    this.$EventBus.$on('changeRoute', this.changeRoute);
  },
  methods: {
    changeRoute(path : string){
      if(this.$route.path !== path){
        this.$router.push(path)
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
