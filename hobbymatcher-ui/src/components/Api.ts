import axios from 'axios'
export const apiUrl = process.env.VUE_APP_URL
export const http = axios.create({ baseURL: apiUrl })
