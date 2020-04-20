<template>
  <router-link :to="'/events/view?id=' + event.id">
    <div class="event-thumb">
      <img
        v-if="!event.photoId"
        src="@/assets/images/logo-200x200.png"
        class="cover"
      />
      <img
        v-if="event.photoId"
        :src="API_URL + '/files/' + event.photoId"
        class="cover"
      />
      <span class="event-title">{{ event.title }}</span>
      <span class="event-datetime" v-if="event.onDatetime">
        {{
          event.onDatetime
            | dateParse('YYYY-MM-DDTHH:mm:ss.000+0000')
            | dateFormat('MMM DD, YYYY h:mma')
        }}
      </span>
      <span v-if="event.locationShort" class="event-location">
        {{ event.locationShort }}
      </span>
    </div>
  </router-link>
</template>

<script lang="ts">
/* eslint-disable space-before-function-paren */

import { Component, Prop, Vue, Model } from 'vue-property-decorator'
import { Event } from './Event'
import { apiUrl } from '../Api'

@Component
export default class GalleryEventThumb extends Vue {
  API_URL = apiUrl

  @Model() model!: Event
  @Model() userId!: number

  get event() {
    return this.model || {}
  }
}
</script>

<style scoped lang="less">
.event-thumb {
  position: relative;
  // width: 30%;
  height: 240px;
  overflow: hidden;
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
  background-color: rgba(211, 211, 211, 0.75);
  padding: 0.5rem;
}
.event-datetime {
  color: black;
  position: absolute;
  left: 0.5rem;
  bottom: 0.5rem;
  font-size: 0.75rem;
  background-color: rgba(211, 211, 211, 0.75);
  padding: 0.5rem;
  border-radius: 5px;
}
.event-location {
  color: black;
  position: absolute;
  right: 0.5rem;
  bottom: 0.5rem;
  font-size: 0.75rem;
  background-color: rgba(211, 211, 211, 0.75);
  padding: 0.5rem;
  border-radius: 5px;
}
</style>
