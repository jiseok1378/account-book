import GroupRequestInviteView from '@/views/group/request-invite/GroupRequestInviteView.vue'
import HomeView from '@/views/home/HomePage.vue'
import SignInVue from '@/views/sign/SignIn.vue'
import SignUpVue from '@/views/sign/SignUp.vue'

import GroupDetailVue from '@/views/group/detail/GroupDetail.vue'
import GroupListView from '@/views/group/list/GroupListView.vue'
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
    path: '/group/request',
    name: 'groupRequest',
    component: GroupRequestInviteView
  },
  {
    path: '/group/list',
    name: 'groupList',
    component: GroupListView
  },
  {
    path: '/group/detail/:groupSn',
    name: 'groupDetail',
    component: GroupDetailVue
  }
]

const router = new VueRouter({
  mode: 'history',
  base: process.env.BASE_URL,
  routes
})

export default router
