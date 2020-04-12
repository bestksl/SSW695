<template>
  <div class="event-view-page">
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
    <EventView v-if="event.id" v-model="event" />

    <div class="p-grid">
      <hr class="p-col-10 p-offset-1 py-0 my-3" />
    </div>
    <Reviews v-if="event.id" :type="'event'" :oId="event.id" />

    <div class="p-grid">
      <div class="p-col-10 p-offset-1 mt-4">
        <strong>Events you may interested in:</strong>
      </div>
      <div class="p-col-10 p-offset-1">
        <EventThumbs v-if="suggesteds.length" :model="suggesteds" />
      </div>
    </div>
  </div>
</template>

<script lang="ts">
/* eslint-disable space-before-function-paren */

import { Component, Prop, Vue, Model } from 'vue-property-decorator'
import { EventService } from '../components/events/EventService'
import { Event } from '../components/events/Event'

@Component
export default class ViewEvent extends Vue {
  eventApi = EventService.getInstance()

  event: Event = {} as any
  suggesteds: Event[] = []

  back() {
    window.history.back()
  }

  mounted() {
    const id = this.$route.query.id

    this.eventApi
      .get(id)
      .then((resp: any) => (this.event = resp.data.event))
      .catch((err: any) => console.log(err))

    this.eventApi
      .loadSuggestedEvents(id)
      .then((resp: any) => (this.suggesteds = resp.data.list))
      .catch((err: any) => console.log(err))
  }
}
</script>

<style lang="less"></style>
