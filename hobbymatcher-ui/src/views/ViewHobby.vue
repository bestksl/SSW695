<template>
  <div class="hobby-page">
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

    <RecentEvents v-model="events" :hobbyId="hobby.id" class="mt-4" />

    <BlogsListing v-model="blogs" :hobbyId="hobby.id" class="mt-4" />
  </div>
</template>

<script lang="ts">
/* eslint-disable space-before-function-paren */

import { Component, Prop, Vue, Model, Watch } from 'vue-property-decorator'
import { HobbyService } from '../components/hobbies/HobbyService'
import { Hobby } from '../components/hobbies/Hobby'
import { Event } from '../components/events/Event'
import { Blog } from '../components/blogs/Blog'

@Component
export default class ViewHobby extends Vue {
  hobbyApi = HobbyService.getInstance()

  hobby: Hobby = {} as any
  events: Event[] = []
  blogs: Blog[] = []

  back() {
    window.history.back()
  }

  @Watch('$route', { immediate: true, deep: true })
  onUrlChange(newVal: any) {
    this.load()
  }

  mounted() {
    this.load()
  }

  load() {
    const id = this.$route.query.id

    this.hobbyApi
      .get(id)
      .then((resp: any) => (this.hobby = resp.data.hobby))
      .catch((err: any) => console.log(err))

    this.hobbyApi
      .loadRecentEvents(id)
      .then((resp: any) => (this.events = resp.data.list))
      .catch((err: any) => console.log(err))

    this.hobbyApi
      .loadRecentBlogs(id)
      .then((resp: any) => (this.blogs = resp.data.list))
      .catch((err: any) => console.log(err))
  }
}
</script>

<style lang="less"></style>
