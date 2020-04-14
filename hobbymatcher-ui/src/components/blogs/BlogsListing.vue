<template>
  <div class="blogs-list">
    <div class="p-grid">
      <div class="p-col-10 p-offset-1">
        <strong>Blogs</strong>
      </div>
      <div class="p-offset-1 p-col-10">
        <div v-if="(model || []).length < 1">
          No Blogs
        </div>
        <BlogsList v-if="(model || []).length" v-model="model" />
      </div>
      <div class="p-col-10 p-offset-1 text-right">
        <router-link :to="'/blogs?hobbyId=' + hobbyId">
          <Button
            label="More Blogs"
            icon="pi pi-chevron-right"
            class="p-button-primary"
          />
        </router-link>
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
  @Model() model!: Blog[]
  @Prop() hobbyId!: number
}
</script>

<style scoped lang="less"></style>
