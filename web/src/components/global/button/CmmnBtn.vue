<template>
  <v-btn class="cmmn-btn" @click="click" :text="text" :href="href">
    <slot></slot>
  </v-btn>
</template>

<script lang="ts">

import Vue from 'vue';

export default Vue.extend({
    props:{
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
}
</style>