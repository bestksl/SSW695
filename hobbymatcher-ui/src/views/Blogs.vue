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

    <HobbyView v-if="hobby.id" v-model="hobby" />

    <div v-if="hobby.id" class="p-col-10 p-offset-1 text-right">
      <router-link :to="'/blogs/create?hobbyId=' + hobby.id">
        <Button
          label="Write a Post"
          icon="pi pi-pencil"
          class="p-button-primary"
        />
      </router-link>
    </div>

    <div class="p-offset-1 p-col-10">
      <div v-if="(blogs || []).length < 1" class="text-center">
        No Blog Written Yet.
      </div>
      <BlogsList
        v-if="(blogs || []).length"
        v-model="blogs"
        v-on:deleted="load()"
      />
    </div>

    <div class="p-offset-1 p-col-10">
      <Paginator
        :rows="filter.perpage"
        :totalRecords="filter.count"
        :first.sync="filter.offset"
        @page="load()"
      >
      </Paginator>
    </div>
  </div>
</template>

<script lang="ts">
/* eslint-disable space-before-function-paren */

import { Component, Prop, Vue, Model } from 'vue-property-decorator'
import { HobbyService } from '../components/hobbies/HobbyService'
import { Hobby } from '../components/hobbies/Hobby'
import { Event } from '../components/events/Event'
import { Blog } from '../components/blogs/Blog'
import { Filter } from '../components/search/Filter'

@Component
export default class Blogs extends Vue {
  hobbyApi = HobbyService.getInstance()

  hobby: Hobby = {} as any
  blogs: Blog[] = []

  filter: Filter = {
    perpage: 15,
    offset: 0, // zero-based index
    count: 0,
    hobbyId: null
  } as any

  back() {
    window.history.back()
  }

  mounted() {
    this.load()
  }

  load() {
    const hobbyId = this.$route.query.hobbyId
    this.filter.hobbyId = hobbyId as any

    if (hobbyId) {
      this.hobbyApi
        .get(hobbyId)
        .then((resp: any) => (this.hobby = resp.data.hobby))
        .catch((err: any) => console.log(err))
    }

    this.hobbyApi
      .loadBlogs(this.filter)
      .then((resp: any) => {
        this.blogs = resp.data.list
        this.filter.count = resp.data.count
      })
      .catch((err: any) => console.log(err))
  }
}
</script>

<style lang="less"></style>
