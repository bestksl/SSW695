import { http } from '../Api'
import { GeneralInformation } from './GeneralInformation'

export class ProfileService {
  private static instance: any

  // eslint-disable-next-line space-before-function-paren
  public static getInstance() {
    if (ProfileService.instance == null) {
      ProfileService.instance = new ProfileService()
    }

    return ProfileService.instance
  }

  // eslint-disable-next-line space-before-function-paren
  loadGeneralInformation() {
    return http.get('/user/general-info')
  }

  // eslint-disable-next-line space-before-function-paren
  saveGeneralInformation(event: FormData) {
    return http.put('/user/general-info', event, {
      headers: { 'content-type': 'multipart/form-data' }
    })
  }

  // eslint-disable-next-line space-before-function-paren
  loadHobbies(selected: string) {
    return http.get(`/user/hobbies?load=${selected}`)
  }

  // eslint-disable-next-line space-before-function-paren
  loadEvents(selected: string) {
    return http.get(`/user/events?load=${selected}`)
  }

  // eslint-disable-next-line space-before-function-paren
  loadBlogs(selected: string) {
    return http.get(`/user/blogs?load=${selected}`)
  }
}
