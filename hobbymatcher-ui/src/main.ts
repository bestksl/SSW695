import Vue from 'vue'
import App from './App.vue'
import './registerServiceWorker'
import router from './router'
import store from './store'
import VueCookies from 'vue-cookies'

import VueFilterDateParse from 'vue-filter-date-parse'
import VueFilterDateFormat from 'vue-filter-date-format'

// -- import fontawesome --
import { library, dom as fasdom } from '@fortawesome/fontawesome-svg-core'
import { fas } from '@fortawesome/free-solid-svg-icons'
import { fab } from '@fortawesome/free-brands-svg-icons'
import { far } from '@fortawesome/free-regular-svg-icons'
import { FontAwesomeIcon } from '@fortawesome/vue-fontawesome'

// -- import primevue library --
import Button from 'primevue/button'
import InputText from 'primevue/inputtext'
import Calendar from 'primevue/calendar'
import Textarea from 'primevue/textarea'
import Checkbox from 'primevue/checkbox'
import FileUpload from 'primevue/fileupload'
import Rating from 'primevue/rating'
import Password from 'primevue/password'
import SelectButton from 'primevue/selectbutton'
import Listbox from 'primevue/listbox'
import Paginator from 'primevue/paginator'
import Dropdown from 'primevue/dropdown'
import Carousel from 'primevue/carousel'

import 'primevue/resources/themes/nova-light/theme.css'
import 'primevue/resources/primevue.min.css'
import 'primeicons/primeicons.css'
import 'primeflex/primeflex.css'

// -- import vue-toast --
import Toasted from 'vue-toasted'

// -- import validation --
import { ValidationProvider, ValidationObserver, localize, extend } from 'vee-validate'
import en from 'vee-validate/dist/locale/en.json'
import * as rules from 'vee-validate/dist/rules'

// -- declare our components --
import Header from './components/Header.vue'
import Footer from './components/Footer.vue'
import RegisterForm from './components/auth/RegisterForm.vue'
import LoginForm from './components/auth/LoginForm.vue'
import EventView from './components/events/EventView.vue'
import EventForm from './components/events/EventForm.vue'
import EventThumb from './components/events/EventThumb.vue'
import EventThumbs from './components/events/EventThumbs.vue'
import RecentEvents from './components/events/RecentEvents.vue'
import Reviews from './components/reviews/Reviews.vue'
import ReviewForm from './components/reviews/ReviewForm.vue'
import ReviewView from './components/reviews/ReviewView.vue'
import HobbyForm from './components/hobbies/HobbyForm.vue'
import HobbyView from './components/hobbies/HobbyView.vue'
import HobbyThumb from './components/hobbies/HobbyThumb.vue'
import HobbyThumbs from './components/hobbies/HobbyThumbs.vue'
import FilterBar from './components/search/FilterBar.vue'
import FilterCategories from './components/search/FilterCategories.vue'
import EventSearchResults from './components/events/EventSearchResults.vue'
import BlogsList from './components/blogs/BlogsList.vue'
import BlogsListing from './components/blogs/BlogsListing.vue'
import HobbyThumbV2 from './components/hobbies/HobbyThumbV2.vue'
import GeneralInformationForm from './components/user/GeneralInformationForm.vue'
import UserHobbies from './components/user/UserHobbies.vue'

Vue.use(VueCookies)

Vue.use(VueFilterDateParse)
Vue.use(VueFilterDateFormat)

// -- registring primevue library --
Vue.component('Button', Button)
Vue.component('InputText', InputText)
Vue.component('Calendar', Calendar)
Vue.component('Textarea', Textarea)
Vue.component('Checkbox', Checkbox)
Vue.component('FileUpload', FileUpload)
Vue.component('Rating', Rating)
Vue.component('Password', Password)
Vue.component('SelectButton', SelectButton)
Vue.component('Listbox', Listbox)
Vue.component('Paginator', Paginator)
Vue.component('Dropdown', Dropdown)
Vue.component('Carousel', Carousel)

// This will kick of the initial replacement of i to svg tags
// and configure a MutationObserver
fasdom.watch()
library.add(fas)
library.add(fab)
library.add(far)
Vue.component('font-awesome-icon', FontAwesomeIcon)

// -- register toast --
Vue.use(Toasted)

// -- register validation --
Vue.component('ValidationProvider', ValidationProvider)
Vue.component('ValidationObserver', ValidationObserver)
Object.keys(rules).forEach((rule: any) => extend(rule, (rules as any)[rule]))
localize({ en }) // Install English locales.

// -- registring our components --
Vue.component('Header', Header)
Vue.component('Footer', Footer)
Vue.component('RegisterForm', RegisterForm)
Vue.component('LoginForm', LoginForm)
Vue.component('EventView', EventView)
Vue.component('EventForm', EventForm)
Vue.component('EventThumb', EventThumb)
Vue.component('EventThumbs', EventThumbs)
Vue.component('RecentEvents', RecentEvents)
Vue.component('Reviews', Reviews)
Vue.component('ReviewForm', ReviewForm)
Vue.component('ReviewView', ReviewView)
Vue.component('HobbyForm', HobbyForm)
Vue.component('HobbyView', HobbyView)
Vue.component('HobbyThumb', HobbyThumb)
Vue.component('HobbyThumbs', HobbyThumbs)
Vue.component('FilterBar', FilterBar)
Vue.component('FilterCategories', FilterCategories)
Vue.component('EventSearchResults', EventSearchResults)
Vue.component('BlogsList', BlogsList)
Vue.component('BlogsListing', BlogsListing)
Vue.component('HobbyThumbV2', HobbyThumbV2)
Vue.component('GeneralInformationForm', GeneralInformationForm)
Vue.component('UserHobbies', UserHobbies)
// -------------------------------

Vue.config.productionTip = false

new Vue({
  router,
  store,
  render: h => h(App)
}).$mount('#app')
