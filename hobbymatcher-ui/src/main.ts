import Vue from 'vue'
import App from './App.vue'
import './registerServiceWorker'
import router from './router'
import store from './store'

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
import SignUpForm from './components/auth/SignUpForm.vue'
import SignInForm from './components/auth/SignInForm.vue'
import EventThumb from './components/events/EventThumb.vue'
import EventThumbs from './components/events/EventThumbs.vue'
import Reviews from './components/reviews/Reviews.vue'
import ReviewForm from './components/reviews/ReviewForm.vue'
import ReviewView from './components/reviews/ReviewView.vue'
import HobbyForm from './components/hobbies/HobbyForm.vue'
import FilterBar from './components/search/FilterBar.vue'
import FilterCategories from './components/search/FilterCategories.vue'
import EventSearchResults from './components/events/EventSearchResults.vue'

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
Vue.component('SignUpForm', SignUpForm)
Vue.component('SignInForm', SignInForm)
Vue.component('EventThumb', EventThumb)
Vue.component('EventThumbs', EventThumbs)
Vue.component('Reviews', Reviews)
Vue.component('ReviewForm', ReviewForm)
Vue.component('ReviewView', ReviewView)
Vue.component('HobbyForm', HobbyForm)
Vue.component('FilterBar', FilterBar)
Vue.component('FilterCategories', FilterCategories)
Vue.component('EventSearchResults', EventSearchResults)
// -------------------------------

Vue.config.productionTip = false

new Vue({
  router,
  store,
  render: h => h(App)
}).$mount('#app')
