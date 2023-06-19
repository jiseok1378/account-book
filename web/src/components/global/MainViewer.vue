<template>
  <div class="main-viewer" @click="(e) => $emit('click', e)">
    <main-title v-if="title" :icon="icon" :title="`${!hideUserNm ? $cookies.get('userNm') + '님의 ' : `` }` + title" :subTitle="subTitle" class="main-title"/>
    <slot></slot>
  </div>
</template>

<script lang="ts">
import Vue from 'vue';
import MainTitle from './title/MainTitle.vue';
export default Vue.extend({
  components: { MainTitle },
  props:{
    hideUserNm: {
      type: Boolean as () => boolean,
      default: false
    },  
    icon: String,
    title: String,
    subTitle: String
  }
})
</script>
<style lang="scss" scoped>
.main-title{
    margin-bottom: 40px;
}
</style>