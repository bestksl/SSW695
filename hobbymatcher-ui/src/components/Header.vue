<template>
  <div class="header pt-3 pb-2">
    <div class="p-grid">
      <div class="p-col-6 pl-5">
        <!-- logo here -->
        <router-link to="/">
          <img src="@/assets/images/logo-180x90.png" class="rounded logo" />
        </router-link>

        <!-- location-header -->
        <!-- <div class="d-inline-block locationButton">
          <a href="">
            <i class="fas fa-map-marker-alt"></i>
            <span class="city-location">&nbsp;(NY)New York&nbsp;</span>
            <i class="fas fa-caret-down"></i>
          </a>
        </div> -->

        <!--        <h3 class="d-inline-block mx-4 my-0 pb-1 app-name">-->
        <!--          <router-link to="/">-->
        <!--            Hobby Matcher-->
        <!--          </router-link>-->
        <!--        </h3>-->

        <!-- log in and sign up -->
      </div>
      <div v-if="authApi.isLogin" class="p-col-6 text-right">
        <!-- <Button
          icon="pi pi-bell"
          class="p-button-secondary mr-2 rounded-circle"
        /> -->

        <router-link to="/profile">
          <img
            v-if="!authApi.response.photoId"
            src="@/assets/images/profile-photo.png"
            class="mr-1 profile-photo"
          />
          <img
            v-if="authApi.response.photoId"
            :src="
              'http://localhost:8080/hobbymatcher/files/' +
                authApi.response.photoId
            "
            class="mr-1 profile-photo"
          />

          <Button
            :label="authApi.response.firstName"
            class="p-button-secondary mr-2"
          />
        </router-link>

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
/* eslint-disable space-before-function-paren */
import { Component, Prop, Vue } from 'vue-property-decorator'
import { AuthService } from './auth/AuthService'

@Component
export default class Header extends Vue {
  authApi = AuthService.getInstance()

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
.header {
  background-color: #fff;
}
.header img {
  width: 180px;
  height: 90px;
  vertical-align: bottom;
}
.logo {
  background-color: white;
}
.locationButton {
  margin-left: 30px;
  line-height: 80px;
}

.locationButton a {
  color: #050505;
}

.profile-photo {
  height: 32px !important;
  width: 32px !important;
  border: solid 1px;
}

/*.app-name {*/
/*  color: white;*/
/*}*/
</style>
