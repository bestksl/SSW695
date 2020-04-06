<template>
  <div class="more-events-page">
    <div class="p-grid">
      <div class="p-offset-1 p-col-2">
        <FilterCategories v-model="filter" :notitle="true" />
      </div>
      <div class="p-col-8">
        <div class="p-grid">
          <div class="p-col-12">
            <HobbyThumbs v-model="hobbies" />
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
import { HobbyService } from '../components/hobbies/HobbyService'
import { Hobby } from '../components/hobbies/Hobby'

@Component
export default class Hobbies extends Vue {
  api = new HobbyService()

  hobbies: Hobby[] = []

  filter: Filter = {
    searchScope: 'hobby',
    count: 48,
    perpage: 10,
    offset: 0 // zero-based index
  } as Filter

  mounted() {
    this.api
      .list()
      .then((resp: any) => (this.hobbies = resp.data.list))
      .catch((err: any) => console.log(err))
  }

  pageChanged($event: any) {
    console.log($event)
    // load the 'page' content from backend
  }
}
</script>
