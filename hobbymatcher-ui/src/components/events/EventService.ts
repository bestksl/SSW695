/* eslint-disable space-before-function-paren */
/* eslint-disable comma-dangle */

import axios from 'axios'
import { http } from '../Api'
import { Event } from './Event'
import { Filter } from '../search/Filter'

export class EventService {
  private static instance: any

  public static getInstance() {
    if (EventService.instance == null) {
      EventService.instance = new EventService()
    }

    return EventService.instance
  }

  list(filter?: Filter) {
    let query = []
    if (filter) {
      if (filter.searchScope) {
        query.push('searchScope=' + filter.searchScope)
      }
      if (filter.searchPhrase) {
        query.push('searchPhrase=' + filter.searchPhrase)
      }
      if (filter.offset) {
        query.push('offset=' + filter.offset)
      }
      if (filter.perpage) {
        query.push('perpage=' + filter.perpage)
      }
      if (filter.categories) {
        query.push('categories=' + filter.categories.join(','))
      }
      if (filter.feeStart) {
        query.push('feeStart=' + filter.feeStart)
      }
      if (filter.feeEnd) {
        query.push('feeEnd=' + filter.feeEnd)
      }
      if (filter.sortBy) {
        query.push('sortBy=' + filter.sortBy)
      }
    }
    return http.get('/event/listevent' + (query.length ? `?${query.join('&')}` : ''))
  }

  add(event: FormData) {
    return http.post('/event/addevent', event, {
      headers: { 'content-type': 'multipart/form-data' }
    })
  }

  update(event: FormData) {
    return http.post('/event/editevent', event, {
      headers: { 'content-type': 'multipart/form-data' }
    })
  }

  get(id: any) {
    return http.get('/event/getevent?id=' + id)
  }

  loadParticipants(eventId: any) {
    return http.get('/event/getparticipant?id=' + eventId)
  }

  manageParticipant(eventId: any, participantId: any, action: any) {
    return http.post(`/event/participation?action=${action}&id=${eventId}&userId=${participantId}`)
  }

  suggestLocations(key: string) {
    return axios.create().get(`https://nominatim.openstreetmap.org/search/${key}?format=json&addressdetails=1&limit=10`)
  }

  getParticipation(id: any) {
    return http.get('/event/getparticipation?id=' + id)
  }

  manageParticipation(id: number, action: string) {
    return http.post(`/event/participation?action=${action}&id=${id}`)
  }

  getStateShort(state: string) {
    const shorts = [
      ['alabama', 'AL'],
      ['alaska', 'AK'],
      ['american samoa', 'AS'],
      ['arizona', 'AZ'],
      ['arkansas', 'AR'],
      ['california', 'CA'],
      ['colorado', 'CO'],
      ['connecticut', 'CT'],
      ['delaware', 'DE'],
      ['district of columbia', 'DC'],
      ['federated states of micronesia', 'FM'],
      ['florida', 'FL'],
      ['georgia', 'GA'],
      ['guam', 'GU'],
      ['hawaii', 'HI'],
      ['idaho', 'ID'],
      ['illinois', 'IL'],
      ['indiana', 'IN'],
      ['iowa', 'IA'],
      ['kansas', 'KS'],
      ['kentucky', 'KY'],
      ['louisiana', 'LA'],
      ['maine', 'ME'],
      ['marshall islands', 'MH'],
      ['maryland', 'MD'],
      ['massachusetts', 'MA'],
      ['michigan', 'MI'],
      ['minnesota', 'MN'],
      ['mississippi', 'MS'],
      ['missouri', 'MO'],
      ['montana', 'MT'],
      ['nebraska', 'NE'],
      ['nevada', 'NV'],
      ['new hampshire', 'NH'],
      ['new jersey', 'NJ'],
      ['new mexico', 'NM'],
      ['new york', 'NY'],
      ['north carolina', 'NC'],
      ['north dakota', 'ND'],
      ['northern mariana islands', 'MP'],
      ['ohio', 'OH'],
      ['oklahoma', 'OK'],
      ['oregon', 'OR'],
      ['palau', 'PW'],
      ['pennsylvania', 'PA'],
      ['puerto rico', 'PR'],
      ['rhode island', 'RI'],
      ['south carolina', 'SC'],
      ['south dakota', 'SD'],
      ['tennessee', 'TN'],
      ['texas', 'TX'],
      ['utah', 'UT'],
      ['vermont', 'VT'],
      ['virgin islands', 'VI'],
      ['virginia', 'VA'],
      ['washington', 'WA'],
      ['west virginia', 'WV'],
      ['wisconsin', 'WI'],
      ['wyoming', 'WY'],
    ]

    state = state.toLowerCase()
    for (let each of shorts) {
      if (each[0] === state) {
        return each[1]
      }
    }
  }
}
