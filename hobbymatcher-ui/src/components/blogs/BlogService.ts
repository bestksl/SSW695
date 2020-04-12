/* eslint-disable space-before-function-paren */

import { http } from '../Api'
import { Blog } from './Blog'

export class BlogService {
  private static instance: any

  public static getInstance() {
    if (BlogService.instance == null) {
      BlogService.instance = new BlogService()
    }

    return BlogService.instance
  }

  post(blog: Blog) {
    return http.post('/blog/postblog', blog)
  }

  like(id: any) {
    return http.post('/blog/like?id=' + id)
  }

  update(blog: Blog) {
    return http.post('/blog/updateblog', blog)
  }

  get(id: any) {
    return http.get('/blog/getblog?id=' + id)
  }

  delete(id: number) {
    return http.post(`/blog/deleteblog?id=${id}`)
  }
}
