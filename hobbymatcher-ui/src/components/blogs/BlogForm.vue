<template>
  <div class="blog-form">
    <ValidationObserver v-slot="{ invalid }">
      <form @submit.prevent="save">
        <div class="p-grid">
          <div class="p-col-10 p-offset-1">
            <ValidationProvider
              name="title"
              v-slot="{ errors }"
              rules="required|min:4|max:128"
            >
              <InputText
                type="text"
                v-model="blog.title"
                class="w-100"
                style="font-size: 1.5rem;"
              />
              <ul v-if="errors.length" class="v-error">
                <li v-for="error in errors" v-bind:key="error">{{ error }}</li>
              </ul>
            </ValidationProvider>
          </div>

          <div class="p-col-10 p-offset-1">
            <hr />
            <ValidationProvider
              name="content"
              v-slot="{ errors }"
              rules="required|min:4|max:16777215"
            >
              <Editor v-model="blog.html" editorStyle="height: 320px" />
              <ul v-if="errors.length" class="v-error">
                <li v-for="error in errors" v-bind:key="error">{{ error }}</li>
              </ul>
            </ValidationProvider>
          </div>

          <div class="p-col-10 p-offset-1">
            <Button
              type="button"
              label="Back"
              icon="pi pi-chevron-left"
              class="p-button-secondary"
              v-on:click="back()"
            />
            <Button
              v-if="blog.id"
              label="Update"
              icon="pi pi-check"
              class="p-button-success"
              :disabled="invalid"
            />
            <Button
              v-if="!blog.id"
              label="Post"
              icon="pi pi-check"
              class="p-button-primary"
              :disabled="invalid"
            />
          </div>
        </div>
      </form>
    </ValidationObserver>
  </div>
</template>

<script lang="ts">
/* eslint-disable space-before-function-paren */

import { Component, Prop, Vue, Model } from 'vue-property-decorator'
import { BlogService } from './BlogService'
import { Blog } from './Blog'

@Component
export default class BlogForm extends Vue {
  blogApi = BlogService.getInstance()

  blog: Blog = {} as any

  back() {
    window.history.back()
  }

  mounted() {
    if (this.$route.query.id) {
      this.blogApi
        .get(this.$route.query.id)
        .then((resp: any) => (this.blog = resp.data.blog))
        .catch((err: any) => console.log(err))
    } else {
      this.blog.forHobbyId = this.$route.query.hobbyId as any
    }
  }

  save() {
    if (this.blog.id) {
      this.doUpdate()
    } else {
      this.doSave()
    }
  }

  doSave() {
    this.blogApi
      .post(this.blog)
      .then((resp: any) => {
        Vue.toasted.show('Blog has been posted.', { duration: 5000 })
        this.back()
      })
      .catch((err: any) => {
        Vue.toasted.show('Failed to post the blog.', { duration: 5000 })
        console.log(err)
      })
  }

  doUpdate() {
    this.blogApi
      .update(this.blog)
      .then((resp: any) => {
        Vue.toasted.show('Blog has been updated.', { duration: 5000 })
        this.back()
      })
      .catch((err: any) => {
        Vue.toasted.show('Failed to update the blog.', { duration: 5000 })
        console.log(err)
      })
  }
}
</script>

<style lang="less"></style>
