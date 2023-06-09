<template>
   <v-tabs @change="(value) => $emit('change', items[value])" :value="val" class="tab-container">
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
            type: Number as () => number
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
    data(){
        return{
            val : 0
        }
    },
    watch:{
        value(o, n){
            this.val = n;
        }
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