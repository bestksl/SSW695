import { http } from '../Api'
import { Hobby } from './Hobby'

export class HobbyService {
  // eslint-disable-next-line space-before-function-paren
  list() {
    return http.get('/hobby/listhobby')
  }

  // eslint-disable-next-line space-before-function-paren
  get(id: any) {
    return http.get('/hobby/gethobby?id=' + id)
  }

  // eslint-disable-next-line space-before-function-paren
  add(hobby: FormData) {
    return http.post('/hobby/addhobby', hobby, {
      headers: { 'content-type': 'multipart/form-data' }
    })
  }
}
