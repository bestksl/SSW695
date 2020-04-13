<template>
  <div class="blogs-list">
    <div
      v-for="blog of model"
      v-bind:key="blog.id"
      class="d-flex align-items-center py-2 blog-record"
    >
      <strong class="flex-grow-1">
        <router-link :to="'/blogs/view?id=' + blog.id">
          {{ blog.title }}
        </router-link>
      </strong>

      <div v-if="blog.byUserId == autApi.response.userId" class="mx-3">
        <router-link :to="'/blogs/edit?id=' + blog.id">
          <Button
            title="Edit"
            icon="pi pi-pencil"
            class="p-button-primary"
            style="width: 24px; height: 24px;"
          />
        </router-link>
        <Button
          title="Delete"
          icon="pi pi-times"
          class="p-button-warning"
          style="width: 24px; height: 24px;"
          v-on:click="doDelete(blog)"
        />
      </div>

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
import { AuthService } from '../auth/AuthService'
import { BlogService } from './BlogService'
import { Blog } from './Blog'

@Component
export default class BlogsList extends Vue {
  @Model() model!: Blog[]

  autApi = AuthService.getInstance()
  blogApi = BlogService.getInstance()

  doDelete(blog: Blog) {
    if (confirm('Are you sure?')) {
      this.blogApi
        .delete(blog.id)
        .then((resp: any) => {
          Vue.toasted.show('Blog has been deleted.', { duration: 5000 })
          this.$emit('deleted', null)
        })
        .catch((err: any) => console.log(err))
    }
  }
}
</script>

<style scoped lang="less">
.blog-record:not(:last-child) {
  border-bottom: solid 1px lightgray;
}
</style>
