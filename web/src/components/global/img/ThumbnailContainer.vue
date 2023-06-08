<template>
    <thurmbnail-wrapper>
        <user-thurmbnail :src="url"/>
    </thurmbnail-wrapper>
</template>

<script lang="ts">
import { ThurmbnailWrapper, UserThurmbnail } from '@/styled-components/StyledComponents';
import Vue from 'vue';
export default Vue.extend({
    components: {ThurmbnailWrapper, UserThurmbnail},
    props: {
        url: String as () => string
    }
})
</script>

<style>

</style>