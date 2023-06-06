<template>
  <v-btn :width="width" class="cmmn-btn" @click="click" :text="text" :href="href" :color="color">
    <slot></slot>
  </v-btn>
</template>

<script lang="ts">

import Vue from 'vue';

export default Vue.extend({
    props:{
        width: {
            type: String,
            default: undefined
        },
        color: {
            type: String,
            default : undefined
        },
        href : {
            type: String,
            default : undefined
        },
        text: {
            type: Boolean,
            default: () => false
        },
        icon:{
            type: Boolean,
            default: () => false
        }
    },
    methods:{
        click(e : any){
            this.$emit("click", e);
        }
    }
});
</script>

<style lang="scss" scoped>
.cmmn-btn{
    text-transform: none !important;
    font-size: $font-size-root;
    
}
</style>