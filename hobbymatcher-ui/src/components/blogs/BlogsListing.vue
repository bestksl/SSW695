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
import { Component, Prop, Vue, Model } from 'vue-property-decorator'
import { Blog } from './Blog'

@Component
export default class BlogsListing extends Vue {
  blogs: Blog[] = []

  // eslint-disable-next-line space-before-function-paren
  constructor() {
    super()
    for (let i = 0; i < 15; i++) {
      this.blogs.push({
        id: i,
        title: 'Sunday Jogging',
        datetime: new Date('2020-10-10'),
        by: 'John Doe'
      })
    }
  }
}
</script>

<style scoped lang="less"></style>
