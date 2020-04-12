<template>
  <div class="recent-events">
    <div class="p-grid">
      <div class="p-col-10 p-offset-1">
        <strong>Recent Events</strong>
      </div>
      <div class="p-col-10 p-offset-1">
        <div v-if="(events || []).length < 1">
          No Recent Events
        </div>
        <EventThumb
          v-for="event of events"
          :key="event.id"
          :model="event"
          class="d-inline-block mr-4 mb-4"
        />
      </div>
      <div class="p-col-10 p-offset-1 text-right">
        <router-link :to="'/events?hobbyId=' + hobbyId">
          <Button
            label="More Events"
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
import { Event } from './Event'

@Component
export default class RecentEvents extends Vue {
  @Model() model!: Event[]
  @Prop() hobbyId!: number

  events: Event[] = []

  mounted() {
    if (this.model) {
      this.events = this.model
    }
  }
}
</script>

<style scoped lang="less"></style>
