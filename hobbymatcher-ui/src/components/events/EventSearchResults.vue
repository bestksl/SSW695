<template>
  <div class="event-thumbs">
    <div v-if="!events || events.length == 0" class="text-center">
      No Events
    </div>
    <EventThumb
      v-for="event of events"
      :key="event.id"
      :model="event"
      class="d-inline-block mx-5 mb-5"
    />
  </div>
</template>

<script lang="ts">
import { Component, Prop, Vue, Model } from 'vue-property-decorator'
import { Event } from './Event'

@Component
export default class EventSearchResults extends Vue {
  @Model() model!: Event[]

  // eslint-disable-next-line space-before-function-paren
  get events() {
    return this.model || []
  }
}
</script>

<style scoped lang="less"></style>
