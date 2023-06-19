
<template>
  <div>DEFAULT</div>
</template>

<script lang="ts">
import Vue from 'vue';
export default Vue.extend({
    created() {
        console.log("AAAAAAAA", this.$route.params.groupSn)
    }
})
</script>
<style>

</style>