<template>
  <link-text-container>
    <slot> </slot>
  </link-text-container>
</template>

<script lang="ts">
import { LinkTextContainer } from '@/styled-components/StyledComponents';
import Vue from 'vue';
export default Vue.extend({
    components:{
        LinkTextContainer
    },
    props:{
        title : {
            type: String
        }
    }
})
</script>

<style>

</style>