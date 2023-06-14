<template>
  <div class="group-tab">
    <tab-container 
        @change="changeValue"
        :items="items"
        :labelKey="labelKey"
        :value="value"/>
    <v-select :items="selectItems" />
  </div>
</template>

<script lang="ts">
import Vue from 'vue';
import TabContainer from '../global/tab/TabContainer.vue';
export default Vue.extend({
    components: { TabContainer },
    props: {
        value: {
            type: Number as () => number,
        },
        labelKey: {
            type: String as () => string,
            default: undefined
        },
        items: {
            type: Array as () => string[],
            default: () => ([])
        },
        selectItems:{
            type: Array as () => string[],
            default: () => ([])

        }
    },
    methods:{
        changeValue(value){
            this.$emit('change', value);
        }
    }

})
</script>
<style lang="scss" scoped>
.group-tab{
    display: flex;
    justify-content: space-between;
}

</style>