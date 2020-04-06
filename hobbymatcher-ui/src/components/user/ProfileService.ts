/* eslint-disable space-before-function-paren */

import { http } from '../Api'
import { GeneralInformation } from './GeneralInformation'

export class ProfileService {
  private static instance: any

  public static getInstance() {
    if (ProfileService.instance == null) {
      ProfileService.instance = new ProfileService()
    }

    return ProfileService.instance
  }

  loadGeneralInformation() {
    return http.get('/user/general-info')
  }

  saveGeneralInformation(event: FormData) {
    return http.put('/user/general-info', event, {
      headers: { 'content-type': 'multipart/form-data' }
    })
  }

  loadHobbies(selected: string) {
    return http.get(`/user/hobbies?load=${selected}`)
  }

  loadEvents(selected: string) {
    return http.get(`/user/events?load=${selected}`)
  }

  loadBlogs(selected: string) {
    return http.get(`/user/blogs?load=${selected}`)
  }

  saveEmail(email: string) {
    return http.put('/user/email/' + email)
  }

  savePassword(current: string, newPassword: string, confirmed: string) {
    return http.put('/user/password', {
      current: current,
      newPassword: newPassword,
      confirmed: confirmed
    })
  }
}
