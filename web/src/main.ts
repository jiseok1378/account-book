import Vue from 'vue'
import App from './App.vue'
import router from './router'
import store from './store'
import api from '@/util/axios'
import vuetify from './plugins/vuetify'
import VueCookies from "vue-cookies";

Vue.config.productionTip = false
Vue.prototype.$http = api;
Vue.use(VueCookies);

declare module 'vue/types/vue' {
  interface Vue {
    $http: typeof api 
  }
}
new Vue({
  router,
  store,
  vuetify,
  render: h => h(App)
}).$mount('#app')
