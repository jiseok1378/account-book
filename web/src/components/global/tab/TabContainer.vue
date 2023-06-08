<template>
   <v-tabs class="tab-container">
    <v-tabs-slider color="primary"></v-tabs-slider>
    <v-tab class="tab-item" v-for="(item, index) in items" :key="index">{{ item }}</v-tab>
  </v-tabs>
</template>

<script lang="ts">
import Vue from 'vue';
export default Vue.extend({
    props: {
        items: {
            type: Array as () => string[],
            default: () => ([])
        },

    }
})
</script>

<style lang="scss" scoped>
.tab-item{
    width: fit-content !important;
    font-size: 13px !important;
    font-weight: bold;
}
</style>