import { http } from '../Api'
import { NewUser } from './NewUser'
import { LoginUser } from './LoginUser'

export class AuthService {
  isLogin!: boolean;

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
  storeToken(jwtToken: string) {
    http.defaults.headers.common.Authorization = `Bearer ${jwtToken}`
  }

  // eslint-disable-next-line space-before-function-paren
  clearToken() {
    http.defaults.headers.common.Authorization = null
  }

  // eslint-disable-next-line space-before-function-paren
  isLoggedIn() {
    return http.get('/is-logged-in')
  }
}
