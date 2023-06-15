<template>
  <div class="group-tab">
    <tab-container 
        @change="changeValue"
        :items="items"
        :labelKey="labelKey"
        :value="value"/>
    <v-select v-if="selectItems.length !== 0 && selectItems" :items="selectItems" />
  </div>
</template>

<script lang="ts">
import TabContainer from '@/components/global/tab/TabContainer.vue';
import Vue from 'vue';
export default Vue.extend({
    components: { TabContainer },
    props: {
        value: {
            type: String as () => string,
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
            console.log("GT", value)
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
@media (max-width: 780px) {
    .group-tab{
        flex-direction: column;
    }
}

</style>