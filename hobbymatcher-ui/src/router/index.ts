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
    path: '/register',
    name: 'register',
    component: () => import('../views/Register.vue')
  },
  {
    path: '/login',
    name: 'login',
    component: () => import('../views/Login.vue')
  },
  {
    path: '/profile',
    name: 'profile',
    component: () => import('../views/Profile.vue')
  },
  {
    path: '/hobbies/create',
    name: 'create-hobby',
    component: () => import('../views/CreateHobby.vue')
  },
  {
    path: '/hobbies/view',
    name: 'view-hobby',
    component: () => import('../views/ViewHobby.vue')
  },
  {
    path: '/hobbies/edit',
    name: 'edit-hobby',
    component: () => import('../views/EditHobby.vue')
  },
  {
    path: '/hobbies',
    name: 'hobbies',
    component: () => import('../views/Hobbies.vue')
  },
  {
    path: '/events/create',
    name: 'create-event',
    component: () => import('../views/CreateEvent.vue')
  },
  {
    path: '/events/edit',
    name: 'edit-event',
    component: () => import('../views/EditEvent.vue')
  },
  {
    path: '/events/view',
    name: 'view-event',
    component: () => import('../views/ViewEvent.vue')
  },
  {
    path: '/events',
    name: 'events',
    component: () => import('../views/Events.vue')
  },
  {
    path: '/blogs/view',
    name: 'view-blog',
    component: () => import('../views/ViewBlog.vue')
  },
  {
    path: '/blogs',
    name: 'blogs',
    component: () => import('../views/Blogs.vue')
  }
]

const router = new VueRouter({
  mode: 'history',
  base: process.env.BASE_URL,
  routes
})

export default router
