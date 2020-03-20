<template>
  <div class="home-page">
    <div class="p-grid">
      <div class="p-offset-1 p-col-10 carousel">
        <Carousel
          :value="events"
          :numVisible="4"
          :numScroll="3"
          :circular="true"
          :autoplayInterval="3000"
        >
          <template #item="value">
            <div class="text-center">
              <EventThumb :model="value.data" class="d-inline-block" />
            </div>
          </template>
        </Carousel>
      </div>

      <div class="p-offset-1 p-col-10">
        <div
          class="p-inputgroup search-options"
          style="width: fit-content; margin: 0 auto;"
        >
          <InputText
            type="text"
            placeholder="Search ..."
            class="search-txf"
            size="40"
          />

          <router-link to="/events">
            <Button icon="pi pi-search" class="p-button-primary" />
          </router-link>
        </div>
      </div>
      <div class="p-offset-1 p-col-10 d-flex align-items-center">
        <h3 class="flex-grow-1">Hobbies</h3>
        <div>
          <router-link to="/hobbies">
            <Button
              label="More Hobbies"
              icon="pi pi-chevron-right"
              class="p-button-primary"
            />
          </router-link>
        </div>
      </div>
      <div class="p-offset-1 p-col-10">
        <HobbyThumbs v-model="hobbies" />
      </div>

      <div class="p-offset-1 p-col-10 d-flex align-items-center">
        <h3 class="flex-grow-1">Events</h3>
        <div>
          <router-link to="/events">
            <Button
              label="More Events"
              icon="pi pi-chevron-right"
              class="p-button-primary"
            />
          </router-link>
        </div>
      </div>
      <div class="p-offset-1 p-col-10">
        <EventThumbs v-model="events" />
      </div>
      <div class="p-offset-1 p-col-10 d-flex align-items-center">
        <h3 class="flex-grow-1">Blogs</h3>
        <div>
          <router-link to="/blogs">
            <Button
              label="More Blogs"
              icon="pi pi-chevron-right"
              class="p-button-primary"
            />
          </router-link>
        </div>
      </div>
      <div class="p-offset-1 p-col-10">
        <BlogsList />
      </div>
    </div>
  </div>
</template>

<script lang="ts">
import { Component, Prop, Vue, Model } from 'vue-property-decorator'
import { HobbyService } from '../components/hobbies/HobbyService'
import { EventService } from '../components/events/EventService'
import { AuthService } from '../components/auth/AuthService'
import { Filter } from '../components/search/Filter'

@Component
export default class Home extends Vue {
  authApi = new AuthService()
  hobbyiesApi = new HobbyService()
  eventsApi = new EventService()

  model: Filter = {
    searchScope: 'hobby',
    count: 48,
    perpage: 10,
    offset: 0 // zero-based index
  } as Filter

  hobbies = []
  events = []

  // eslint-disable-next-line space-before-function-paren
  mounted() {
    this.authApi
      .isLoggedIn()
      .then((resp: any) => (this.authApi.isLogin = true))
      .catch((err: any) => console.log(err))

    this.hobbyiesApi
      .list()
      .then((resp: any) => (this.hobbies = resp.data.list))
      .catch((err: any) => console.log(err))

    this.eventsApi
      .list()
      .then((resp: any) => (this.events = resp.data.list))
      .catch((err: any) => console.log(err))
  }

  // eslint-disable-next-line space-before-function-paren
  pageChanged($event: any) {
    console.log($event)
    // load the 'page' content from backend
  }
}
</script>
