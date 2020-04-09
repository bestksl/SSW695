<template>
  <div class="more-events-page">
    <div class="p-grid">
      <div class="p-offset-1 p-col-2">
        <FilterCategories
          v-model="filter"
          :notitle="true"
          v-on:changed="load()"
        />
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
/* eslint-disable comma-dangle */

import { Component, Prop, Vue, Model } from 'vue-property-decorator'
import { Filter } from '../components/search/Filter'
import { HobbyService } from '../components/hobbies/HobbyService'
import { Hobby } from '../components/hobbies/Hobby'

@Component
export default class Hobbies extends Vue {
  hobbyApi = HobbyService.getInstance()

  hobbies: Hobby[] = []

  filter: Filter = {
    searchScope: 'hobby',
    perpage: 15,
    offset: 0, // zero-based index
    count: 0,
  } as Filter

  mounted() {
    this.load()
  }

  pageChanged($event: any) {
    this.load()
  }

  load() {
    this.hobbyApi
      .list(this.filter)
      .then((resp: any) => {
        this.hobbies = resp.data.list
        this.filter.count = resp.data.count
      })
      .catch((err: any) => console.log(err))
  }
}
</script>
