/* eslint-disable space-before-function-paren */

import Vue from 'vue'
import { http } from '../Api'
import { Review } from './Review'

export class ReviewService {
  private static instance: any

  public static getInstance() {
    if (ReviewService.instance == null) {
      ReviewService.instance = new ReviewService()
    }

    return ReviewService.instance
  }

  postORput(review: Review) {
    return review.id ? this.put(review) : this.post(review)
  }

  post(review: Review) {
    return http.post('/review', review)
  }

  put(review: Review) {
    return http.put('/review', review)
  }

  load(type: string, oId: number, pId: number) {
    return http.get(`/review?type=${type}&oid=${oId}` + (pId ? '&pid=' + pId : ''))
  }

  delete(id: number) {
    return http.delete('/review/' + id)
  }
}
