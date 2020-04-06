<template>
  <div class="event-thumbs">
    <div v-if="!events || events.length == 0" class="text-center">
      No Events
    </div>
    <EventThumb
      v-for="event of events"
      :key="event.id"
      :model="event"
      class="d-inline-block mr-4 mb-4"
    />
  </div>
</template>

<script lang="ts">
/* eslint-disable space-before-function-paren */
import { Component, Prop, Vue, Model } from 'vue-property-decorator'
import { Event } from './Event'

@Component
export default class EventThumbs extends Vue {
  @Model() model!: Event[]

  get events() {
    return this.model || []
  }
}
</script>

<style scoped lang="less"></style>
