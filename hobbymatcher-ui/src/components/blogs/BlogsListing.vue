<template>
  <div class="blogs-list">
    <div class="p-grid">
      <div class="p-offset-1 p-col-10">
        <BlogsList v-model="blogs" />
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
/* eslint-disable space-before-function-paren */
/* eslint-disable comma-dangle */

import { Component, Prop, Vue, Model } from 'vue-property-decorator'
import { Blog } from './Blog'

@Component
export default class BlogsListing extends Vue {
  blogs: Blog[] = []

  constructor() {
    super()
    for (let i = 0; i < 9; i++) {
      this.blogs.push({
        id: i,
        title: 'Sunday Jogging',
        onDatetime: new Date('2020-10-10'),
        byUser: {
          firstName: 'John',
          lastName: 'Doe',
        },
      } as any)
    }
  }
}
</script>

<style scoped lang="less"></style>
