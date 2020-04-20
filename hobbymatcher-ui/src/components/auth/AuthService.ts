/* eslint-disable space-before-function-paren */

import { http } from '../Api'
import Vue from 'vue'
import { NewUser } from './NewUser'
import { LoginUser } from './LoginUser'

export class AuthService {
  private static instance: any
  private $response: any = {}

  get isLogin() { return this.$response.isLogin }
  get response() { return this.$response }

  public static getInstance() {
    if (AuthService.instance == null) {
      AuthService.instance = new AuthService()
      AuthService.instance.checkLogin()
    }

    return AuthService.instance
  }

  constructor() {
    const token = Vue.$cookies.get('jwt-token')
    if (token) { this.useJwtToken(token) }
  }

  checkLogin(success?: any, error?: any) {
    this.handshake()
      .then((resp: any) => {
        this.$response = resp.data

        if (this.$response.isLogin) {
          // refresh the token
          this.storeToken(this.$response.jwtToken)
        }

        if (success) {
          success()
        }
      })
      .catch((err: any) => {
        console.log(err)
        if (error) {
          error()
        }
      })
  }

  ifLogin(yes: any, no?: any) {
    const check = () => {
      if (this.isLogin) {
        if (yes) yes()
      } else {
        if (no) no()
      }
    }

    if (this.response.loaded) check()
    else this.checkLogin(() => check(), no)
  }

  register(newUser: NewUser) {
    return http.post('/register', newUser)
  }

  login(authUser: LoginUser) {
    return http.post('/login', authUser)
  }

  logout() {
    return http.post('/logout')
  }

  storeToken(token: string) {
    token = 'Bearer ' + token
    this.useJwtToken(token)
    Vue.$cookies.set('jwt-token', token)
  }

  clearToken() {
    this.useJwtToken(null)
    Vue.$cookies.set('jwt-token', null)
  }

  private useJwtToken(token: any) {
    http.defaults.headers.common.Authorization = token
  }

  handshake() {
    return http.get('/handshake')
  }

  goHome(router: any) {
    if (window.location.pathname !== '/') {
      router.push({ name: 'home' })
    }
  }

  shouldBeLoggedIn($router: any) {
    const interval = setInterval(() => {
      if (this.response.loaded) {
        if (!this.isLogin) {
          Vue.toasted.show('Please Login', { duration: 5000 })
          $router.back()
        }

        clearInterval(interval)
      }
    }, 50)
  }
}
