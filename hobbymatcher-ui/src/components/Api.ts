import axios from 'axios'
export const appUrl = process.env.VUE_APP_URL
export const apiUrl = process.env.VUE_APP_REST_API_URL
export const http = axios.create({ baseURL: apiUrl })
