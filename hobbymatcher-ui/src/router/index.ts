import Vue from 'vue'
import VueRouter from 'vue-router'
import Home from '../views/Home.vue'

Vue.use(VueRouter)

const routes = [
  {
    path: '/',
    name: 'home',
    component: Home
  },
  {
    path: '/about',
    name: 'about',
    // route level code-splitting
    // this generates a separate chunk (about.[hash].js) for this route
    // which is lazy-loaded when the route is visited.
    component: () => import(/* webpackChunkName: "about" */ '../views/About.vue')
  },
  {
    path: '/signup',
    name: 'signup',
    component: () => import('../views/SignUp.vue')
  },
  {
    path: '/signin',
    name: 'signin',
    component: () => import('../views/SignIn.vue')
  },
  {
    path: '/hobbies/create',
    name: 'create-hobby',
    component: () => import('../views/CreateHobby.vue')
  },
  {
    path: '/events/create',
    name: 'create-event',
    component: () => import('../views/CreateEvent.vue')
  },
  {
    path: '/events/view',
    name: 'view-event',
    component: () => import('../views/ViewEvent.vue')
  },
  {
    path: '/search/more-events',
    name: 'more-events',
    component: () => import('../views/MoreEvents.vue')
  }
]

const router = new VueRouter({
  mode: 'history',
  base: process.env.BASE_URL,
  routes
})

export default router
