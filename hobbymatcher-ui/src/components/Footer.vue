<template>
  <div class="py-5 footer">
    <div class="links text-center">
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
    <div class="footer-bottom-box mx-auto">
      <div class="footer-info-box-left">
        <a href="">
          <img src="@/assets/images/logo-180x90.png" alt="logo">
        </a>
      </div>
      <div class="footer-info-box-right">
        <ul class="footer-bottom-ul">
          <li><a href="">Report complaints</a></li>
          <li><a href="mailto:hobbymatcher@gmail.com?cc=hobbymatcherdev@gmail.com&bcc=hobbymatcherbusiness@gmail.com;&
                          &subject=The%20subject%20of%20the%20email
                          &body=I%20would%20like%20to%20report%20a%20bug%20,%20it%20is%20on%20this%20website(link%20please%20attached%20below),%20some%20descriptions">
            Contact Us</a>
          </li>
          <li><a href="">FAQs</a></li>
        </ul>
      </div>
    </div>

    <div class="text-center">
      <small class="d-inline-block pt-3">
        HobbyMatcher - 2020 All Right Reserved.
      </small>
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
ul {
  list-style: none;
}
.footer {
  height: 270px;
  background-color: #fff;
}
.footer * {
  color: #050505;
  margin: 0 1rem;
}
.footer .links a {
  font-style: italic;
  text-decoration: underline;
}
.footer-bottom-box {
  margin-top: 25px;
  margin-bottom: 15px;
  width: 620px;
  height: 98px;
}

.footer-info-box-left {
  float: left;
}

.footer-info-box-right {
  float: left;
  margin: 0 auto;
}

.footer-bottom-ul li {
  margin-bottom: 13px;
}

a:hover {
  color: #FF1268;
}
</style>
