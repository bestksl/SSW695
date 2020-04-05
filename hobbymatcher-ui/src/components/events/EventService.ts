/* eslint-disable space-before-function-paren */
/* eslint-disable comma-dangle */

import axios from 'axios'
import { http } from '../Api'
import { Event } from './Event'

export class EventService {
  list() {
    return http.get('/event/listevent')
  }

  add(event: FormData) {
    return http.post('/event/addevent', event, {
      headers: { 'content-type': 'multipart/form-data' }
    })
  }

  get(id: any) {
    return http.get('/event/getevent?id=' + id)
  }

  suggestLocations(key: string) {
    return axios.create().get(`https://nominatim.openstreetmap.org/search/${key}?format=json&addressdetails=1&limit=10`)
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
