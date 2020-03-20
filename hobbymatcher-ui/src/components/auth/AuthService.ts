import Vue from 'vue'
import { http } from '../Api'
import { NewUser } from './NewUser'
import { LoginUser } from './LoginUser'

export class AuthService {
  // eslint-disable-next-line space-before-function-paren
  constructor() {
    const token = Vue.$cookies.get('jwt-token')
    if (token) { this.useJwtToken(token) }
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
    this.useJwtToken(token)
    Vue.$cookies.set('jwt-token', 'Bearer ' + token)
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
  isLoggedIn() {
    return http.get('/is-logged-in')
  }
}
