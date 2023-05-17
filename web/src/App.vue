<template>
  <div id="app">
    <StyledComponent>BBB</StyledComponent>
    <router-view/>
  </div>
</template>

<script lang="ts">
import { StyledComponent } from './styled-components/Test'
import { defineComponent } from 'vue'
export default defineComponent({
  components: {
    StyledComponent
  }
})
</script>

<style lang="scss">
#app {
  font-family: Avenir, Helvetica, Arial, sans-serif;
  -webkit-font-smoothing: antialiased;
  -moz-osx-font-smoothing: grayscale;
  text-align: center;
  color: #2c3e50;
}


</style>
