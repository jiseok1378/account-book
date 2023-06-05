<template>
    <cmmn-btn text @click="click">
        <v-icon 
            v-if="icon" 
            :style="`font-size: ${iconSize}px; margin-right: 5px`">
            {{icon}}
        </v-icon>
        <top-text :font-size="15">{{ title }}</top-text>
    </cmmn-btn>
</template>

<script lang="ts">
import CmmnBtn from '@/components/global/button/CmmnBtn.vue';
import { TopText } from '@/styled-components/StyledComponents';
import Vue from 'vue';
export default Vue.extend({
  components: { CmmnBtn, TopText },
    props:{
        textSize: {
            type: Number,
            default: 18
        },  
        iconSize: {
            type: Number,
            default : 30
        },
        icon : {
            type: String,
            default : undefined,
        },
        title: {
            type: String,
            default : () => ""

        }
    },
    methods: {
        click(e : any){
            this.$emit("click",e)
        }
    }
})
</script>

<style>

</style>