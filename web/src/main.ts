import api from '@/util/axios'
import Vue from 'vue'
import VueCookies from "vue-cookies"
import App from './App.vue'
import vuetify from './plugins/vuetify'
import router from './router'
import store from './store'

Vue.config.productionTip = false
Vue.prototype.$http = api;
Vue.use(VueCookies);
const EventBus = new Vue(); 
Vue.prototype.$EventBus = EventBus;
declare module 'vue/types/vue' {
  interface Vue {
    $http: typeof api,
    $EventBus: Vue
  }
}
new Vue({
  router,
  store,
  vuetify,
  render: h => h(App)
}).$mount('#app')
