<template>
  <v-card>
    <v-list-item class="board-item">
      <v-list-item-content>
        <v-list-item-title class="item-title">{{title}}</v-list-item-title>
        <v-list-item-subtitle v-if="subTitle" class="item-sub-title">{{subTitle}}</v-list-item-subtitle>
      </v-list-item-content>
    </v-list-item>
    <v-list-item>
      <v-list-item-content>
        <slot></slot>
      </v-list-item-content>
    </v-list-item>
  </v-card>
</template>

<script lang="ts">

import Vue from 'vue';

export default Vue.extend({
  props:{
    title: {
      type: String as () => string ,
      default: 'Title'
    },
    subTitle:{
      type: String as () => string,
      default: undefined
    }
  }
})
</script>

<style lang="scss">
.board-item{
  .item-title{
    font-size: 15px;
    font-weight: bold;
    margin-bottom: 7px;
  }
  .item-sub-title{
    font-size: 12px;
  }
}
</style>