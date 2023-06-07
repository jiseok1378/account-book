<template>
  <v-app>
    <top-banner 
      @click-left-btn="changeRoute('/')"
      @click-right-btn="changeRoute('/signin')"/>
    <v-main>
      <router-view/>
    </v-main>
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
    
  }
});
</script>
