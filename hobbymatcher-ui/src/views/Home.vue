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

      <div class="p-offset-1 p-col-10 text-center">
        <AutoComplete
          ref="homeSearch"
          v-model="search.selected"
          placeholder="Search ..."
          :suggestions="search.events"
          @complete="doSearch($event)"
          @item-select="navToEvent()"
          field="title"
          size="55"
        />
        <Button
          icon="pi pi-search"
          class="p-button-primary"
          @click="$refs.homeSearch.focus()"
        />
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
/* eslint-disable space-before-function-paren */
import { Component, Prop, Vue, Model } from 'vue-property-decorator'
import { HobbyService } from '../components/hobbies/HobbyService'
import { EventService } from '../components/events/EventService'
import { AuthService } from '../components/auth/AuthService'
import { Filter } from '../components/search/Filter'

@Component
export default class Home extends Vue {
  authApi = AuthService.getInstance()
  hobbyApi = new HobbyService()
  eventApi = new EventService()

  model: Filter = {
    searchScope: 'hobby',
    count: 0,
    offset: 0, // zero-based index
    perpage: 10
  } as Filter

  hobbies = []
  events = []
  search = {
    offset: 0, // zero-based index
    perpage: 10,
    searchPhrase: '',
    selected: null as any,
    events: []
  }

  mounted() {
    this.hobbyApi
      .list()
      .then((resp: any) => (this.hobbies = resp.data.list))
      .catch((err: any) => console.log(err))

    this.eventApi
      .list()
      .then((resp: any) => (this.events = resp.data.list))
      .catch((err: any) => console.log(err))
  }

  doSearch($event: any) {
    this.search.searchPhrase = $event.query
    this.eventApi
      .list(this.search as any)
      .then((resp: any) => (this.search.events = resp.data.list))
      .catch((err: any) => console.log(err))
  }

  navToEvent() {
    this.$router.push({
      name: 'view-event',
      query: { id: this.search.selected.id }
    })
  }
}
</script>
