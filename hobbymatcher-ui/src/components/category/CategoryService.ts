/* eslint-disable space-before-function-paren */

import { http } from '../Api'
import { Category } from './Category'

export class CategoryService {
  list() {
    return http.get('/category/listcategory')
  }
}
