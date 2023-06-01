<template>
  <v-app>
    <v-app-bar
      app
      color="primary"
      dark
    >
      <div class="d-flex align-center">
        <v-btn text>
          <v-icon style="font-size: 30px;">mdi-credit-card-clock-outline</v-icon>
          <span style="margin-left: 10px; font-size: 15px">Account book</span>
        </v-btn>
      </div>

      <v-spacer></v-spacer>

      <v-btn
        href="https://github.com/vuetifyjs/vuetify/releases/latest"
        target="_blank"
        text
      >
        <span class="mr-2">LOGIN ACCOUNT</span>
        <v-icon>mdi-open-in-new</v-icon>
      </v-btn>
    </v-app-bar>

    <v-main>
      <router-view/>
    </v-main>
  </v-app>
</template>

<script lang="ts">

import {defineComponent} from 'vue' 
export default defineComponent({
  name: 'App',

  components: {
  },
  created(){
    this.$cookies.set("1", "2")
  },
  data: () => ({
    
    //
  }),
});
</script>
