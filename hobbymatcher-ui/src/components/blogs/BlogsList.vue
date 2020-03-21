<template>
  <div class="blogs-list">
    <div
      v-for="blog of blogs"
      v-bind:key="blog.id"
      class="d-flex align-items-center py-2 blog-record"
    >
      <strong class="flex-grow-1">
        <router-link to="/blogs/view">
          {{ blog.title }}
        </router-link>
      </strong>
      <strong> By: {{ blog.by }} </strong>
      <small class="ml-4">
        {{ blog.datetime | dateFormat('MMM DD, YYYY h:mma') }}
      </small>
    </div>
  </div>
</template>

<script lang="ts">
import { Component, Prop, Vue, Model } from 'vue-property-decorator'
import { Blog } from './Blog'

@Component
export default class BlogsList extends Vue {
  @Model() model!: Blog[]
  blogs: Blog[] = []

  // eslint-disable-next-line space-before-function-paren
  constructor() {
    super()
    if (this.model) {
      this.blogs = this.model
    } else {
      for (let i = 1; i < 15; i++) {
        this.blogs.push({
          id: i,
          title: 'Sunday Jogging',
          datetime: new Date('2020-10-10'),
          by: 'John Doe'
        })
      }
    }
  }
}
</script>

<style scoped lang="less">
.blog-record:not(:last-child) {
  border-bottom: solid 1px lightgray;
}
</style>
