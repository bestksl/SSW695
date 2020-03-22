<template>
  <div class="header pt-3 pb-2">
    <div class="p-grid">
      <div class="p-col-6 pl-5">
        <img
          src="@/assets/images/logo-200x200.png"
          class="rounded-circle logo"
        />
        <h3 class="d-inline-block mx-4 my-0 pb-1 app-name">
          <router-link to="/">
            Hobby Matcher
          </router-link>
        </h3>
      </div>
      <div v-if="authApi.isLogin" class="p-col-6 text-right">
        <Button
          icon="pi pi-bell"
          class="p-button-secondary mr-2 rounded-circle"
        />
        <img src="@/assets/images/profile-photo.png" class="mr-1" />
        <Button
          :label="authApi.$resp.firstName"
          class="p-button-secondary mr-2"
        />
        <Button
          label="Logout"
          icon="pi pi-sign-out"
          class="p-button-secondary"
          v-on:click="doLogout()"
        />
      </div>
      <div v-if="!authApi.isLogin" class="p-col-6 text-right">
        <router-link to="/login">
          <Button
            v-if="!authApi.isLogin"
            label="Login"
            icon="pi pi-sign-in"
            class="p-button-primary"
          />
        </router-link>
      </div>

      <!-- <div class="p-col-10 p-offset-1 text-left">
        <router-link to="/">Home</router-link>|
        <router-link to="/about">About</router-link>
        <router-link to="/events/create">Create</router-link>
      </div> -->
    </div>
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
.header img {
  width: 32px;
  height: 32px;
  vertical-align: bottom;
}
.logo {
  background-color: white;
}
.app-name {
  color: white;
}
</style>
