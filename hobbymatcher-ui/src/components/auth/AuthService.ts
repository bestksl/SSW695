import Vue from 'vue'
import { http } from '../Api'
import { NewUser } from './NewUser'
import { LoginUser } from './LoginUser'

export class AuthService {
  private static instance: any
  private $response: any = {}

  // eslint-disable-next-line space-before-function-paren
  get isLogin() { return this.$response.isLogin }
  // eslint-disable-next-line space-before-function-paren
  get response() { return this.$response }

  // eslint-disable-next-line space-before-function-paren
  public static getInstance() {
    if (AuthService.instance == null) {
      AuthService.instance = new AuthService()
      AuthService.instance.checkLogin()
    }

    return AuthService.instance
  }

  // eslint-disable-next-line space-before-function-paren
  constructor() {
    const token = Vue.$cookies.get('jwt-token')
    if (token) { this.useJwtToken(token) }
    console.log('created instnace')
  }

  // eslint-disable-next-line space-before-function-paren
  checkLogin() {
    this.handshake()
      .then((resp: any) => {
        this.$response = resp.data

        if (this.$response.isLogin) {
          // refresh the token
          this.storeToken(this.$response.jwtToken)
        }
      })
      .catch((err: any) => console.log(err))
  }

  // eslint-disable-next-line space-before-function-paren
  register(newUser: NewUser) {
    return http.post('/register', newUser)
  }

  // eslint-disable-next-line space-before-function-paren
  login(authUser: LoginUser) {
    return http.post('/login', authUser)
  }

  // eslint-disable-next-line space-before-function-paren
  logout() {
    return http.post('/logout')
  }

  // eslint-disable-next-line space-before-function-paren
  storeToken(token: string) {
    token = 'Bearer ' + token
    this.useJwtToken(token)
    Vue.$cookies.set('jwt-token', token)
    console.log(token)
  }

  // eslint-disable-next-line space-before-function-paren
  clearToken() {
    this.useJwtToken(null)
    Vue.$cookies.set('jwt-token', null)
  }

  // eslint-disable-next-line space-before-function-paren
  private useJwtToken(token: any) {
    http.defaults.headers.common.Authorization = token
  }

  // eslint-disable-next-line space-before-function-paren
  handshake() {
    return http.get('/handshake')
  }

  // eslint-disable-next-line space-before-function-paren
  goHome(router: any) {
    if (window.location.pathname !== '/') {
      router.push({ name: 'home' })
    }
  }
}
