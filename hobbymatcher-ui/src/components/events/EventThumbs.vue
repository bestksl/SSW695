<template>
  <div class="event-thumbs">
    <EventThumb
      v-for="event of events"
      :key="event.id"
      :model="event"
      class="d-inline-block mr-4 mb-4"
    />
  </div>
</template>

<script lang="ts">
import { Component, Prop, Vue } from 'vue-property-decorator'
import { Event } from './Event'

@Component
export default class EventThumbs extends Vue {
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
    } as Event
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
