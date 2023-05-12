import Vue from 'vue'
import App from './App.vue'
import router from './router'
import store from './store'
import api from '@/util/axios'
Vue.config.productionTip = false
Vue.prototype.$http = api;
declare module 'vue/types/vue' {
  interface Vue {
    $http: typeof api 
  }
}
new Vue({
  router,
  store,
  render: h => h(App)
}).$mount('#app')
