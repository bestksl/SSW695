<template>
  <div class="blogs-list">
    <div class="mb-4">
      <SelectButton
        v-model="selected"
        :options="[
          { title: 'My Blogs', value: 'mine' },
          { title: 'Commented On', value: 'commented' }
        ]"
        optionLabel="title"
        optionValue="value"
        @input="reload()"
      />
    </div>
    <div v-if="!blogs || blogs.length == 0" class="text-center">
      No Blog
    </div>
    <div
      v-for="blog of blogs"
      v-bind:key="blog.id"
      class="d-flex align-items-center py-2 blog-record"
    >
      <router-link :to="'/blogs/edit?id=' + blog.id">
        <Button type="button" icon="pi pi-pencil" class="p-button-primary" />
      </router-link>
      <Button
        type="button"
        icon="pi pi-times"
        class="p-button-danger"
        v-on:click="doDelete(blog)"
      />

      <strong class="flex-grow-1 ml-4">
        <router-link :to="'/blogs/view?id=' + blog.id">
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
import { Component, Prop, Vue, Model } from 'vue-property-decorator'
import { ProfileService } from './ProfileService'
import { BlogService } from '../blogs/BlogService'
import { Blog } from '../blogs/Blog'

@Component
export default class UserBlogs extends Vue {
  selected: any = 'mine'
  blogs: Blog[] = []

  profileApi = ProfileService.getInstance()
  blogApi = BlogService.getInstance()

  mounted() {
    this.reload()
  }

  reload() {
    this.profileApi
      .loadBlogs(this.selected)
      .then((resp: any) => (this.blogs = resp.data.list))
      .catch((err: any) => console.log(err))
  }

  doDelete(blog: Blog) {
    if (confirm('Are you sure?')) {
      this.blogApi
        .delete(blog.id)
        .then((resp: any) => this.reload())
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
