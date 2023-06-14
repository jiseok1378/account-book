<template>
    <div class="group-title-warpper">
      <div class="d-flex align-center group-title">
        <v-icon v-if="icon" class="icon" color="primary">{{ icon }}</v-icon>
        {{ title }}
      </div>
      <div class="sub-title">{{ subTitle }}</div>
    </div>
</template>

<script lang="ts">
import Vue from 'vue';
export default Vue.extend({
    props:{
        icon: {
            default: 'mdi-format-list-checks',
            type: String as () => string
        },
        title: {
            default: 'Title',
            type: String as () => string
        },
        subTitle: {
            default: 'Sub Title',
            type: String as () => string
        }
    }
})
</script>

<style lang="scss">
.group-title-warpper{
  display: flex;
  align-items: flex-end;
  justify-content: space-between;
  margin-bottom: 20px;
  .group-title{
    font-size: 23px;
    font-weight: bold;
    min-width: fit-content;
    .icon {
      margin-right: 10px;
      font-size: 40px;
    }
  }
  .sub-title{
    color: var(--v-primary-base)
  }
}
@media (max-width: 780px) {
  .group-title-warpper{
    flex-direction: column;
    justify-content: flex-start;
    align-items: flex-start;
  }
  .group-title{
    margin-bottom: 10px;
  }
}
</style>