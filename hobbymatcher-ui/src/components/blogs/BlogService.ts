/* eslint-disable space-before-function-paren */

import { http } from '../Api'

export class BlogService {
  private static instance: any

  public static getInstance() {
    if (BlogService.instance == null) {
      BlogService.instance = new BlogService()
    }

    return BlogService.instance
  }

  delete(id: number) {
    return http.delete(`/blogs/${id}`)
  }
}
