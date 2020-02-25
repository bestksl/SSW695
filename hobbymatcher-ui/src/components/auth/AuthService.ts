import { http } from '../Api'
import { NewUser } from './NewUser'
import { AuthUser } from './AuthUser'

export class AuthService {
  signup (newUser: NewUser) {
    return http.post('/user/adduser', newUser)
  }

  signin (authUser: AuthUser) {
    return http.post('/user/adduser', authUser)
  }
}
