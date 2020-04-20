<template>
  <div class="blog-page">
    <div class="p-grid">
      <div class="p-col-10 p-offset-1 text-left">
        <Button
          label="Back"
          icon="pi pi-chevron-left"
          class="p-button-secondary"
          v-on:click="back()"
        />
      </div>
      <div class="p-col-10 p-offset-1">
        <hr />
      </div>
    </div>

    <BlogView v-if="blog.id" v-model="blog" v-on:like="like($event)" />

    <div class="p-grid">
      <hr class="p-col-10 p-offset-1 py-0 my-3" />
    </div>
    <Reviews v-if="blog.id" :type="'blog'" :oId="blog.id" />
  </div>
</template>
<script lang="ts">
/* eslint-disable space-before-function-paren */

import { Component, Prop, Vue, Model } from 'vue-property-decorator'
import { BlogService } from '../components/blogs/BlogService'
import { Blog } from '../components/blogs/Blog'

@Component
export default class ViewBlog extends Vue {
  blogApi = BlogService.getInstance()

  blog: Blog = {} as any

  mounted() {
    this.reload()
  }

  back() {
    window.history.back()
  }

  like(id: any) {
    this.blogApi.like(id).then(() => this.reload())
  }

  reload() {
    this.blogApi
      .get(this.$route.query.id)
      .then((resp: any) => (this.blog = resp.data.blog))
      .catch((err: any) => console.log(err))
  }
}
</script>
