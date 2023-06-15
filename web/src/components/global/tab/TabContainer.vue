<template>
   <v-tabs 
    @change="(value) => $emit('change', value)" 
    class="tab-container"
    next-icon="mdi-arrow-right"
    prev-icon="mdi-arrow-left"
    show-arrows
    >
    <v-tabs-slider color="primary"></v-tabs-slider>
    <v-tab class="tab-item" v-for="(item, index) in items" :key="index">
        {{ labelKey ? item[labelKey] : item }} 
        <template v-if="!hideTotal">{{ item.total ? `(${item.total})` : ''}}</template>
    </v-tab>
  </v-tabs>
</template>

<script lang="ts">
import Vue from 'vue';
export default Vue.extend({
    props: {
        hideTotal:{
            type: Boolean as () => boolean,
            default: false,
        },  
        value: {
            type: String as () => string
        },
        labelKey:{
            type: String as () => string,
            default: undefined
        },  
        items: {
            type: Array as () => any[],
            default: () => ([])
        },
    },

})
</script>

<style lang="scss" scoped>
.tab-item{
    width: fit-content !important;
    font-size: 13px !important;
    font-weight: bold;
}
</style>