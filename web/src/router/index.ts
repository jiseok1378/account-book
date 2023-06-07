import HomeView from '@/views/home/HomePage.vue'
import PairView from '@/views/pair/PairView.vue'
import SignInVue from '@/views/sign/SignIn.vue'
import SignUpVue from '@/views/sign/SignUp.vue'
import Vue from 'vue'
import VueRouter, { RouteConfig } from 'vue-router'
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
  },
  {
    path: '/pair',
    name: 'pair',
    component: PairView
  }
]

const router = new VueRouter({
  mode: 'history',
  base: process.env.BASE_URL,
  routes
})

export default router
