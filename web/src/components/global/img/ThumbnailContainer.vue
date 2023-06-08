<template>
    <v-avatar :size="size">
        <img :src="src" />
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
    }
})
</script>

<style>

</style>