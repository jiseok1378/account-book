<template>
    <v-card
  >
    <v-img
      :src="item.groupThumbnail"
      height="200px"
    ></v-img>

    <v-card-title>
      {{item.groupNm}}
    </v-card-title>

    <v-card-subtitle>
      {{item.groupIntro}}
    </v-card-subtitle>

    <v-card-actions>
      <v-btn
        color="orange lighten-2"
        text
      >
        Explore
      </v-btn>

      <v-spacer></v-spacer>

    </v-card-actions>


  </v-card>
</template>

<script lang="ts">
import { GroupItem } from '@/@types/global-types';
import Vue from 'vue';
export default Vue.extend({
  props:{
    item: {
      type: Object as () => GroupItem
    }
  }, 
  data: () => ({
  }),
})
</script>

<style>

</style>