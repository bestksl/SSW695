import { http } from '../Api'

export class BlogService {
  private static instance: any

  // eslint-disable-next-line space-before-function-paren
  public static getInstance() {
    if (BlogService.instance == null) {
      BlogService.instance = new BlogService()
    }

    return BlogService.instance
  }

  // eslint-disable-next-line space-before-function-paren
  delete(id: number) {
    return http.delete(`/blogs/${id}`)
  }
}
