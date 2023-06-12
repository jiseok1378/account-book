<template>
    <logo>
        <div class="d-flex" >
            <v-avatar style="margin-right: 10px;" v-if="type === 'signup'"> 
                <label style="width: inherit">
                    <v-icon :size="fontSize" color="primary" v-if="!thumbnail">mdi-upload</v-icon>  
                    <v-img v-else :src="thumbnail" />
                    <input type="file" name="thumbnail-upload" style="display: none" @change="e => $emit('upload-thumbnail',e)">
                </label>
            </v-avatar>
           
            <circle-logo >
                <v-icon :size="fontSize" color="primary">mdi-notebook-heart-outline</v-icon>  
            </circle-logo>
        </div>
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
        thumbnail:{
            type: String,
            default: undefined
        },
        type:{
            type: String
        },
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