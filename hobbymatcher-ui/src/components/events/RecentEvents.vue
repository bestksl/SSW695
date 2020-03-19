<template>
  <div class="recent-events">
    <div class="p-grid">
      <div class="p-col-10 p-offset-1">
        <strong>Recent Events</strong>
      </div>
      <div class="p-col-10 p-offset-1">
        <EventThumb
          v-for="event of events"
          :key="event.id"
          :model="event"
          class="d-inline-block mr-4 mb-4"
        />
      </div>
      <div class="p-col-10 p-offset-1 text-right">
        <Button
          label="More Events"
          icon="pi pi-chevron-right"
          class="p-button-secondary"
        />
      </div>
    </div>
  </div>
</template>

<script lang="ts">
import { Component, Prop, Vue } from 'vue-property-decorator'
import { Event } from './Event'

@Component
export default class RecentEvents extends Vue {
  @Prop() model!: Event
  events: Event[] = [
    {
      title: 'Sunday Jogging',
      datetime: new Date('2020-10-10'),
      location: 'Hoboken, NJ',
      capacity: 10,
      description: 'This is going to be a fun weekly event.',
      plus18: false,
      fee: 0,
      organizer: 'Stevens Institute of Technology Fun Club',
      coverPhotoId: '@/assets/images/logo-200x200.png'
    } as any
  ]

  // eslint-disable-next-line space-before-function-paren
  constructor() {
    super()

    for (let i = 0; i < 5; i++) {
      this.events.push(this.events[0])
    }
  }
}
</script>

<style scoped lang="less"></style>
