<template>
    <cmmn-btn text @click="click">
        <v-icon 
            v-if="icon && !userThurmbnail" 
            :style="`font-size: ${iconSize}px; margin-right: 5px`">
            {{icon}}
        </v-icon>
        <thumbnail-container :size="30" class="banner-thurmbnail" :src="userThurmbnail" v-else-if="!icon && userThurmbnail" />
        <top-text :font-size="15">{{ title }}</top-text>
    </cmmn-btn>
</template>

<script lang="ts">
import CmmnBtn from '@/components/global/button/CmmnBtn.vue';
import { TopText } from '@/styled-components/StyledComponents';
import Vue from 'vue';
import ThumbnailContainer from '../img/ThumbnailContainer.vue';
export default Vue.extend({
  components: { CmmnBtn, TopText,  ThumbnailContainer },
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

<style lang="scss">
.banner-thurmbnail{
    margin-right: 5px;
}
</style>