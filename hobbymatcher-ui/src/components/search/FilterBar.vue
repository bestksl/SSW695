<template>
  <div class="filter-bar d-flex align-items-center">
    <SelectButton
      v-model="model.sortBy"
      :options="options"
      optionValue="value"
      optionLabel="label"
      class="sort-by"
    />

    <span class="mx-2"></span>

    <label class="mx-2">Fee ($):</label>
    <InputText type="text" v-model="model.feeStart" size="5" />
    <span class="mx-2">-</span>
    <InputText type="text" v-model="model.feeEnd" size="5" />

    <span class="flex-grow-1"></span>

    <div class="p-inputgroup search-options">
      <Dropdown
        v-if="model.hobbyId"
        v-model="model.searchScope"
        :options="scopes"
        optionLabel="label"
        optionValue="value"
      />
      <InputText
        type="text"
        v-model="model.searchPhrase"
        placeholder="Search ..."
        :class="'search-txf ' + (model.hobbyId ? 'with-dropdown' : '')"
        size="20"
      />
      <Button
        icon="pi pi-search"
        class="p-button-primary"
        v-on:click="$emit('changed', null)"
      />
    </div>
  </div>
</template>

<script lang="ts">
/* eslint-disable space-before-function-paren */
/* eslint-disable comma-dangle */

import { Component, Prop, Vue, Model } from 'vue-property-decorator'
import { Filter } from './Filter'

@Component
export default class FilterBar extends Vue {
  @Model() model!: Filter
  options = [
    { value: 'popularity', label: 'Popularity' },
    { value: 'distance', label: 'Distance' },
    { value: 'newest', label: 'Newest' }
  ]
  scopes = [
    { value: 'hobby', label: 'Under Present Hobby' },
    { value: 'sitewide', label: 'Sitewide' }
  ]
}
</script>

<!-- scoped -->
<style lang="less">
.filter-bar {
  .search-txf.with-dropdown.p-inputtext {
    border-radius: 0;
    border-left: none;
    border-right: none;
  }
  .search-options .p-dropdown-panel {
    min-width: 12rem;
  }
  .search-options .p-dropdown {
    border-top-right-radius: 0;
    border-bottom-right-radius: 0;
  }
}
</style>
