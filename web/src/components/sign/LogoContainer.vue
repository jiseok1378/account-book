<template>
    <logo>
        <circle-logo>
            <v-icon :size="fontSize" color="primary">mdi-notebook-heart-outline</v-icon>  
        </circle-logo>
        <LogoTitle>{{title}}</LogoTitle>
    </logo>
</template>

<script lang="ts">
import { CircleLogo, Logo, LogoTitle } from '@/styled-components/StyledComponents';
import Vue from 'vue';
export default Vue.extend({
    components: {
        Logo, 
        CircleLogo, LogoTitle
    },
    props:{
        title: {
            type: String
        },
        fontSize : {
            type: Number,
            default : 30
        }
    }
})
</script>

<style>

</style>