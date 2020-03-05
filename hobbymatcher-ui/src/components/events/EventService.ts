import { http } from '../Api'
import { Event } from './Event'

export class EventService {
  list () {
    return http.get('/events/listevents')
  }

  create (event: Event) {
    return http.post('/events/addevents', event)
  }

  delete (event: Event) {
    return http.post('/events/deleteevents', [event])
  }
}
