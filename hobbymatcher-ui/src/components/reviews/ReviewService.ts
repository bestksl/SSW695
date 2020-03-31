import Vue from 'vue'
import { http } from '../Api'
import { Review } from './Review'

export class ReviewService {
  private static instance: any

  // eslint-disable-next-line space-before-function-paren
  public static getInstance() {
    if (ReviewService.instance == null) {
      ReviewService.instance = new ReviewService()
    }

    return ReviewService.instance
  }

  // eslint-disable-next-line space-before-function-paren
  post(review: Review) {
    return http.post('/review', review)
  }

  // eslint-disable-next-line space-before-function-paren
  load(type: string, oId: number) {
    return http.get(`/review?type=${type}&oid=${oId}`)
  }
}
