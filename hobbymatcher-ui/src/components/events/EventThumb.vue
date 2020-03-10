<template>
  <div class="event-thumb">
    <img src="@/assets/images/logo-200x200.png" class="cover" />
    <span class="event-title">{{ event.title }}</span>
    <span class="event-datetime">{{
      event.datetime | dateFormat('MMM DD hh:mm a')
    }}</span>
    <span class="event-location">{{ event.location }}</span>
  </div>
</template>

<script lang="ts">
import { Component, Prop, Vue, Model } from 'vue-property-decorator'
import { Event } from './Event'

@Component
export default class EventThumb extends Vue {
  @Model() model!: Event
  event: Event = {
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
}
</script>

<style scoped lang="less">
.event-thumb {
  position: relative;
  width: 220px;
  height: 160px;
  overflow: hidden;
  border: solid 1px lightgray;
  cursor: pointer;
}
.cover {
  width: 100%;
  height: 100%;
  opacity: 0.75;
}
.event-title {
  position: absolute;
  top: 30%;
  left: 0;
  right: 0;
  text-align: center;
  font-size: 1.25rem;
  max-height: 100px;
  overflow: hidden;
}
.event-datetime {
  position: absolute;
  left: 0.5rem;
  bottom: 0.5rem;
  font-size: 0.75rem;
}
.event-location {
  position: absolute;
  right: 0.5rem;
  bottom: 0.5rem;
  font-size: 0.75rem;
}
</style>
