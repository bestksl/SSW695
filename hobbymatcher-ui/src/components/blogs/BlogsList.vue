<template>
  <div class="blogs-list">
    <div
      v-for="blog of model"
      v-bind:key="blog.id"
      class="d-flex align-items-center py-2 blog-record"
    >
      <strong class="flex-grow-1">
        <router-link to="/blogs/view">
          {{ blog.title }}
        </router-link>
      </strong>
      <strong> By: {{ blog.byUserFirst }} {{ blog.byUserLast }} </strong>
      <small class="ml-4">
        {{
          blog.onDatetime
            | dateParse('YYYY-MM-DDTHH:mm:ss.000+0000')
            | dateFormat('MMM DD, YYYY h:mma')
        }}
      </small>
    </div>
  </div>
</template>

<script lang="ts">
/* eslint-disable space-before-function-paren */
/* eslint-disable comma-dangle */

import { Component, Prop, Vue, Model } from 'vue-property-decorator'
import { Blog } from './Blog'

@Component
export default class BlogsList extends Vue {
  @Model() model!: Blog[]
}
</script>

<style scoped lang="less">
.blog-record:not(:last-child) {
  border-bottom: solid 1px lightgray;
}
</style>
