<template>
  <div class="py-5 text-center footer">
    <div class="links">
      <router-link to="/">Home</router-link>
      <span>|</span>
      <router-link to="/about">About</router-link>
      <span v-if="!authApi.isLogin">|</span>
      <router-link v-if="!authApi.isLogin" to="/register">Register</router-link>
      <span>|</span>
      <a href="#" v-if="authApi.isLogin" v-on:click="doLogout()">Logout</a>
      <router-link v-if="!authApi.isLogin" to="/login">Login</router-link>
      <span v-if="authApi.isLogin">|</span>
      <router-link v-if="authApi.isLogin" to="/hobbies/create">
        Create Hobby
      </router-link>
      <span v-if="authApi.isLogin">|</span>
      <router-link v-if="authApi.isLogin" to="/events/create">
        Create Event
      </router-link>
    </div>
    <small class="d-inline-block pt-3">
      HobbyMatcher - 2020 All Right Reserved.
    </small>
  </div>
</template>

<script lang="ts">
import { Component, Prop, Vue } from 'vue-property-decorator'
import { AuthService } from './auth/AuthService'

@Component
export default class Header extends Vue {
  authApi = AuthService.getInstance()

  // eslint-disable-next-line space-before-function-paren
  doLogout() {
    this.authApi
      .logout()
      .then((resp: any) => {
        this.authApi.clearToken()
        this.authApi.checkLogin()
        this.authApi.goHome(this.$router)
      })
      .catch((err: any) => console.log(err))
  }
}
</script>

<style scoped lang="less">
.footer * {
  color: white;
  margin: 0 1rem;
}
.footer .links a {
  font-style: italic;
  text-decoration: underline;
}
</style>
