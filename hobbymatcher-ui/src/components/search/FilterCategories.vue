<template>
  <div class="filter-categories">
    <h4 v-if="!notitle" class="mb-2">Categories</h4>
    <Listbox
      v-model="model.categories"
      :options="categories"
      :multiple="true"
      optionLabel="label"
      optionValue="id"
      style="width: 120px;"
    />
  </div>
</template>

<script lang="ts">
import { Component, Prop, Vue, Model } from 'vue-property-decorator'
import { Filter } from './Filter'

@Component
export default class FilterCategories extends Vue {
  @Prop() notitle!: boolean
  @Model() model!: Filter
  categories = [
    { id: 1, label: 'Sports' },
    { id: 2, label: 'Music' },
    { id: 3, label: 'Art' }
  ]
}
</script>

<style scoped lang="less"></style>
