import Vue from 'vue'
import VueRouter, { RouteConfig } from 'vue-router'
import HomeView from '../views/HomeView.vue'
import SignInVue from '@/components/sign/signin/SignIn.vue'
import SignUpVue from '@/components/sign/signup/SignUp.vue'
Vue.use(VueRouter)

const routes: Array<RouteConfig> = [
  {
    path: '/',
    name: 'home',
    component: HomeView
  },
  {
    path: '/signin',
    name: "signin",
    component: SignInVue
  },
  {
    path: '/signup',
    name: "signup",
    component: SignUpVue
  }
]

const router = new VueRouter({
  mode: 'history',
  base: process.env.BASE_URL,
  routes
})

export default router
