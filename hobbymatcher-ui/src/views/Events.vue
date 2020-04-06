<template>
  <div class="more-events-page">
    <div class="p-grid">
      <div class="p-offset-1 p-col-2">
        <FilterCategories v-model="filter" />
      </div>
      <div class="p-col-8">
        <div class="p-grid">
          <div class="p-col-12">
            <FilterBar v-model="filter" />
          </div>
          <div class="p-col-12">
            <EventSearchResults v-model="events" />
          </div>
          <div class="p-col-12">
            <Paginator
              :rows="filter.perpage"
              :totalRecords="filter.count"
              :first.sync="filter.offset"
              @page="pageChanged($event)"
            >
            </Paginator>
          </div>
        </div>
      </div>
    </div>
  </div>
</template>

<script lang="ts">
/* eslint-disable space-before-function-paren */
import { Component, Prop, Vue, Model } from 'vue-property-decorator'
import { Filter } from '../components/search/Filter'
import { EventService } from '../components/events/EventService'
import { Event } from '../components/events/Event'

@Component
export default class Events extends Vue {
  api = new EventService()

  events: Event[] = []

  filter: Filter = {
    searchScope: 'hobby',
    count: 48,
    perpage: 10,
    offset: 0 // zero-based index
  } as Filter

  mounted() {
    this.api
      .list()
      .then((resp: any) => (this.events = resp.data.list))
      .catch((err: any) => console.log(err))
  }

  pageChanged($event: any) {
    console.log($event)
    // load the 'page' content from backend
  }
}
</script>
