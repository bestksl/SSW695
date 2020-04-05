/* eslint-disable space-before-function-paren */
import { http } from '../Api'
import { Hobby } from './Hobby'

export class HobbyService {
  list() {
    return http.get('/hobby/listhobby')
  }

  get(id: any) {
    return http.get('/hobby/gethobby?id=' + id)
  }

  add(hobby: FormData) {
    return http.post('/hobby/addhobby', hobby, {
      headers: { 'content-type': 'multipart/form-data' }
    })
  }

  update(hobby: FormData) {
    return http.post('/hobby/edithobby', hobby, {
      headers: { 'content-type': 'multipart/form-data' }
    })
  }
}
