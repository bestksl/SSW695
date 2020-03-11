<template>
  <div class="home-page">
    <div class="p-grid">
      <div class="p-offset-1 p-col-10 carousel">
        <Carousel
          :value="values"
          :numVisible="4"
          :numScroll="3"
          :circular="true"
          :autoplayInterval="3000"
        >
          <template #item="value">
            <div class="text-center">
              <EventThumb :model="value" class="d-inline-block" />
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
        <HobbyThumbs />
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
        <EventThumbs />
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
import { Filter } from '../components/search/Filter'

@Component
export default class Home extends Vue {
  model: Filter = {
    searchScope: 'hobby',
    count: 48,
    perpage: 10,
    offset: 0 // zero-based index
  } as Filter

  values = [
    { id: 1, title: 'Jogging 1', description: 'Good 1' },
    { id: 2, title: 'Jogging 2', description: 'Good 2' },
    { id: 3, title: 'Jogging 3', description: 'Good 3' },
    { id: 4, title: 'Jogging 4', description: 'Good 4' },
    { id: 5, title: 'Jogging 5', description: 'Good 5' },
    { id: 6, title: 'Jogging 6', description: 'Good 6' },
    { id: 7, title: 'Jogging 7', description: 'Good 7' }
  ]

  // eslint-disable-next-line space-before-function-paren
  pageChanged($event: any) {
    console.log($event)
    // load the 'page' content from backend
  }
}
</script>
