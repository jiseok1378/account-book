<template>
   <v-app-bar
      app
      color="primary"
      dark
    >
      <div class="d-flex align-center">
        <banner-btn :title="'Woo Ga'" 
          @click="(e) => $emit('click-left-btn', e)" 
          icon="mdi-notebook-heart-outline"/>
      </div>

      <v-spacer></v-spacer>
      
      <banner-btn 
        :title="'SignIn'" 
        @click="(e) => $emit('click-right-btn', e)" 
        icon="mdi-open-in-new"
        :iconSize="23"
        />
        
    </v-app-bar>
</template>

<script lang="ts">
import { defineComponent } from 'vue';
import BannerBtn from './BannerBtn.vue';

export default defineComponent({
  components: { BannerBtn },

})
</script>

<style lang="scss" scoped>
#top-banner{
    text-transform: none !important;
}   
</style>