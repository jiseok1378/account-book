<template>
  <div id="app">
    <nav>
      <router-link to="/">{{helloWorld}}</router-link> |
      <router-link to="/about">About</router-link>
    </nav>
    <router-view/>
  </div>
</template>

<script lang="ts">
import {defineComponent} from 'vue';
import axios from 'axios';
export default defineComponent({
  data(){
    return {
      helloWorld : ""
    }
  },
  async beforeMount(){
    const helloWorld : string = (await axios.get("api/hello/world")).data as string;
    this.helloWorld = helloWorld;
  }
});
</script>
<style lang="scss">
#app {
  font-family: Avenir, Helvetica, Arial, sans-serif;
  -webkit-font-smoothing: antialiased;
  -moz-osx-font-smoothing: grayscale;
  text-align: center;
  color: #2c3e50;
}

nav {
  padding: 30px;

  a {
    font-weight: bold;
    color: #2c3e50;

    &.router-link-exact-active {
      color: #42b983;
    }
  }
}
</style>
