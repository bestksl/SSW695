<template>
  <div class="blog-page">
    <div v-if="blog.id" class="p-grid">
      <div class="p-col-8 p-offset-1">
        <h2>{{ blog.title }}</h2>
      </div>
      <div class="p-col-2 text-right">
        <div>
          <small>{{
            blog.onDatetime
              | dateParse('YYYY-MM-DDTHH:mm:ss.000+0000')
              | dateFormat('MMM DD, YYYY h:mma')
          }}</small>
        </div>

        <div class="mt-1">
          <span>{{ blog.byUserFirst }}</span>
          <span v-if="blog.byUserLast">{{ blog.byUserLast }}</span>
        </div>

        <div class="mt-2">
          <Button
            :label="blog.clapCount + ''"
            icon="pi pi-thumbs-up"
            class="p-button-secondary"
            @click="doLike()"
          />
        </div>
      </div>

      <div class="p-col-10 p-offset-1 pt-0">
        <hr />
        <div v-html="blog.html" class="mt-3"></div>
      </div>
    </div>
  </div>
</template>

<script lang="ts">
/* eslint-disable space-before-function-paren */

import { Component, Prop, Vue, Model } from 'vue-property-decorator'
import { BlogService } from './BlogService'
import { Blog } from './Blog'

@Component
export default class BlogView extends Vue {
  blogApi = BlogService.getInstance()

  blog: Blog = {} as any

  mounted() {
    this.reload()
  }

  doLike() {
    this.blogApi.like(this.blog.id)
    this.reload()
  }

  reload() {
    this.blogApi
      .get(this.$route.query.id)
      .then((resp: any) => (this.blog = resp.data.blog))
      .catch((err: any) => console.log(err))
  }
}
</script>

<style lang="less"></style>
