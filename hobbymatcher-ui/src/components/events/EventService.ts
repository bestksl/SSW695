import { http } from '../Api'
import { Event } from './Event'

export class EventService {
  // eslint-disable-next-line space-before-function-paren
  list() {
    return http.get('/event/listevent')
  }

  // eslint-disable-next-line space-before-function-paren
  add(event: FormData) {
    return http.post('/event/addevent', event, {
      headers: { 'content-type': 'multipart/form-data' }
    })
  }

  // eslint-disable-next-line space-before-function-paren
  get(id: any) {
    return http.get('/event/getevent?id=' + id)
  }
}
