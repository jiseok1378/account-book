<template>
    <div>
        <label v-if="title" style="font-size: 10px; margin-bottom: 5px;">{{title}}</label>
        <v-text-field class="title-input-text-field" :hide-details="hideDetails" :placeholder="placeholder" outlined autofocus />
    </div>
</template>

<script lang="ts">
import Vue from 'vue';
export default Vue.extend({
    props:{
        title : {
            type: String,
            default: undefined
        },
        placeholder : {
            type: String,
            default: undefined
        },
        hideDetails : {
            type: Boolean,
            default: false,
        }
    }
})
</script>

<style lang="scss" scoped>
.title-input-text-field::v-deep{    
    .v-input__slot {
        height: 40px;
        font-size: 12px;
        min-height: unset !important;
    }
}
</style>