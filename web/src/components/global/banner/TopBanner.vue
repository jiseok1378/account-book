<template>
   <v-app-bar
      app
      color="primary"
      dark
    >
      <div class="d-flex align-center">
        <banner-btn :title="'WooGa'" 
          @click="(e) => $emit('click-left-btn', e)" 
          icon="mdi-notebook-heart-outline"/>
      </div>
      <template v-if="userNm">
        <banner-btn :title="'나의 페어 목록'" 
            @click="(e) => $EventBus.$emit('changeRoute','/pair') " />
      </template>
      <v-spacer></v-spacer>
      
      <banner-btn 
        :title="`${userNm ? userNm : '로그인'}`" 
        @click="clickRightBtn" 
        :userThurmbnail="userThurmbnail"
        :icon="userThurmbnail ? undefined : 'mdi-open-in-new'"
        :iconSize="23"
        />
        
    </v-app-bar>
</template>

<script lang="ts">
import { defineComponent } from 'vue';
import BannerBtn from './BannerBtn.vue';
export default defineComponent({
  components: { BannerBtn },
  data(){
    return{
      userNm : this.$cookies.get('userNm'),
      userThurmbnail : this.$cookies.get('userThurmbnail')
    }
  },
  mounted() {
    this.$EventBus.$on('chageUserNm', (userNm) => this.userNm = userNm);
    this.$EventBus.$on('chageUserThurmbnail', (userThurmbnail) => this.userThurmbnail = userThurmbnail);
    this.$EventBus.$on('clearUserInfo', () => {
      this.userNm = undefined;
      this.userThurmbnail= undefined;
    });
    
  },
  methods: {
    clickRightBtn(e){
      if(this.userNm){
        console.log("BLOCK")
      }
      else{
        this.$emit('click-right-btn', e)
      }
    }
  }
})
</script>

<style lang="scss" scoped>
#top-banner{
    text-transform: none !important;
}   
</style>