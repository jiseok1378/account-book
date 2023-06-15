<template>
    <v-avatar :size="size">
        <img v-if="isShowImg" :src="src" @error="errorHandle"/>
        <v-icon v-else> mdi-face-man </v-icon>
    </v-avatar>
</template>

<script lang="ts">
import Vue from 'vue';
export default Vue.extend({
    props: {
        src: String as () => string,
        size: {
            type: Number as () => number,
            default: 30
        }
    },
    data(){
        return {
            isShowImg: true,
        }
    },
    methods: {
        errorHandle(){
            this.isShowImg = false;
        }
    }
})
</script>

<style>

</style>