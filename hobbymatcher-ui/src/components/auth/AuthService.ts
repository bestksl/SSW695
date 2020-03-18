import { http } from '../Api'
import { NewUser } from './NewUser'
import { AuthUser } from './AuthUser'

export class AuthService {
  // eslint-disable-next-line space-before-function-paren
  signup(newUser: NewUser) {
    return http.post('/user/adduser', newUser)
  }

  // eslint-disable-next-line space-before-function-paren
  signin(authUser: AuthUser) {
    return http.post('/user/adduser', authUser)
  }
}
