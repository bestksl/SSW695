import { http } from '../Api'
import { Category } from './Category'

export class CategoryService {
  // eslint-disable-next-line space-before-function-paren
  list() {
    return http.get('/category/listcategory')
  }
}
