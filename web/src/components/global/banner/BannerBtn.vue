<template>
    <cmmn-btn text @click="click">
        <v-icon 
            v-if="icon && !userThurmbnail" 
            :style="`font-size: ${iconSize}px; margin-right: 5px`">
            {{icon}}
        </v-icon>
        <thurmbnail-wrapper v-else-if="!icon && userThurmbnail">
            <user-thurmbnail :src="userThurmbnail"></user-thurmbnail>
        </thurmbnail-wrapper>
        <top-text :font-size="15">{{ title }}</top-text>
    </cmmn-btn>
</template>

<script lang="ts">
import CmmnBtn from '@/components/global/button/CmmnBtn.vue';
import { ThurmbnailWrapper, TopText, UserThurmbnail } from '@/styled-components/StyledComponents';
import Vue from 'vue';
export default Vue.extend({
  components: { CmmnBtn, TopText, UserThurmbnail, ThurmbnailWrapper },
    props:{
        userThurmbnail: {
            default: undefined
        },
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