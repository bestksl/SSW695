<template>
  <div class="event-form">
    <div class="p-grid">
      <div class="p-offset-1 p-col-10">
        <div
          v-for="blog of blogs"
          v-bind:key="blog"
          class="d-flex align-items-center py-2 blog-record"
        >
          <strong class="flex-grow-1">
            <a href="">{{ blog.title }}</a>
          </strong>
          <strong> By: {{ blog.by }} </strong>
          <small class="ml-4">
            {{ blog.datetime | dateFormat('MMM DD hh:mm a') }}
          </small>
        </div>
      </div>
      <div class="p-col-10 p-offset-1 text-right">
        <Button
          label="More Blogs"
          icon="pi pi-chevron-right"
          class="p-button-secondary"
        />
      </div>
    </div>
  </div>
</template>

<script lang="ts">
import { Component, Prop, Vue, Model } from 'vue-property-decorator'
import { Blog } from './Blog'

@Component
export default class BlogsList extends Vue {
  @Model() model!: Blog
  blogs: Blog[] = [
    {
      id: 1,
      title: 'Sunday Jogging',
      datetime: new Date('2020-10-10'),
      by: 'John Doe'
    } as Blog
  ]

  // eslint-disable-next-line space-before-function-paren
  constructor() {
    super()
    for (let i = 0; i < 30; i++) {
      this.blogs.push(this.blogs[0])
    }
  }
}
</script>

<style scoped lang="less">
.blog-record:not(:last-child) {
  border-bottom: solid 1px lightgray;
}
</style>
