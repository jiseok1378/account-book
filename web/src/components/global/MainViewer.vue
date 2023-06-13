<template>
  <div class="main-viewer">
    <main-title :icon="icon" :title="title" :subTitle="subTitle" class="main-title"/>
    <slot></slot>
  </div>
</template>

<script lang="ts">
import Vue from 'vue';
import MainTitle from './title/MainTitle.vue';
export default Vue.extend({
  components: { MainTitle },
  props:{
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