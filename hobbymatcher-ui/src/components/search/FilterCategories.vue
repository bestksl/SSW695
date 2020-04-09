<template>
  <div class="filter-categories">
    <h4 v-if="!notitle" class="mb-2">Categories</h4>
    <Listbox
      v-model="model.categories"
      :options="categories"
      :multiple="true"
      optionLabel="name"
      optionValue="id"
      style="width: 120px;"
      @change="$emit('changed', null)"
    />
  </div>
</template>

<script lang="ts">
/* eslint-disable space-before-function-paren */
/* eslint-disable comma-dangle */

import { Component, Prop, Vue, Model } from 'vue-property-decorator'
import { Filter } from './Filter'
import { CategoryService } from '../category/CategoryService'

@Component
export default class FilterCategories extends Vue {
  @Prop() notitle!: boolean
  @Model() model!: Filter
  categories = []

  mounted() {
    new CategoryService()
      .list()
      .then((resp: any) => (this.categories = resp.data.list))
      .catch((err: any) => console.log(err))
  }
}
</script>

<style scoped lang="less"></style>
