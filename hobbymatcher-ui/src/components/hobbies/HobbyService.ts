/* eslint-disable space-before-function-paren */

import { http } from '../Api'
import { Hobby } from './Hobby'
import { Filter } from '../search/Filter'

export class HobbyService {
  private static instance: any

  public static getInstance() {
    if (HobbyService.instance == null) {
      HobbyService.instance = new HobbyService()
    }

    return HobbyService.instance
  }

  list(filter?: Filter) {
    let query = []
    if (filter) {
      if (filter.offset) {
        query.push('offset=' + filter.offset)
      }
      if (filter.perpage) {
        query.push('perpage=' + filter.perpage)
      }
      if (filter.categories) {
        query.push('categories=' + filter.categories.join(','))
      }
    }
    // /hobby/listhobby?searchPrase=sdfsd&offset=10&perpage=10
    return http.get('/hobby/listhobby' + (query.length ? `?${query.join('&')}` : ''))
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

  getFollowship(id: any) {
    return http.get('/hobby/getfollowship?id=' + id)
  }

  manageFollowship(id: number, action: string) {
    return http.post(`/hobby/followship?action=${action}&id=${id}`)
  }

  loadRecentEvents(id: number) {
    return http.get('/hobby/recentevents?id=' + id)
  }

  loadRecentBlogs(id: number) {
    return http.get('/hobby/recentblogs?id=' + id)
  }

  loadBlogs(filter: Filter) {
    let query = []
    if (filter) {
      if (filter.offset) {
        query.push('offset=' + filter.offset)
      }
      if (filter.perpage) {
        query.push('perpage=' + filter.perpage)
      }
      if (filter.hobbyId) {
        query.push('hobbyId=' + filter.hobbyId)
      }
    }
    // /hobby/listhobby?searchPrase=sdfsd&offset=10&perpage=10
    return http.get('/hobby/blogs' + (query.length ? `?${query.join('&')}` : ''))
  }
}
