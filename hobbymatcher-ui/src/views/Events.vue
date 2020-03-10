<template>
  <div class="more-events-page">
    <div class="p-grid">
      <div class="p-offset-1 p-col-2">
        <FilterCategories v-model="model" />
      </div>
      <div class="p-col-8">
        <div class="p-grid">
          <div class="p-col-12">
            <FilterBar v-model="model" />
          </div>
          <div class="p-col-12">
            <EventSearchResults />
          </div>
          <div class="p-col-12">
            <Paginator
              :rows="model.perpage"
              :totalRecords="model.count"
              :first.sync="model.offset"
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
import { Component, Prop, Vue, Model } from 'vue-property-decorator'
import { Filter } from '../components/search/Filter'

@Component
export default class Events extends Vue {
  model: Filter = {
    searchScope: 'hobby',
    count: 48,
    perpage: 10,
    offset: 0 // zero-based index
  } as Filter

  // eslint-disable-next-line space-before-function-paren
  pageChanged($event: any) {
    console.log($event)
    // load the 'page' content from backend
  }
}
</script>
