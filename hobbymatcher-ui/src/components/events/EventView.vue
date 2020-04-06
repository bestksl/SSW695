<template>
  <div class="event-form">
    <div class="p-grid">
      <div class="p-col-3 p-offset-1 text-center">
        <div>
          <img
            v-if="event.photoId"
            :src="'http://localhost:8080/hobbymatcher/files/' + event.photoId"
            style="width: 12rem; height: 12rem; border: solid 1px lightgray;"
          />
        </div>
        <div class="mt-3">
          <button class="p-button p-button-secondary px-2 py-1 mr-1">
            <i class="fab fa-google"></i>
          </button>
          <button class="p-button p-button-secondary px-2 py-1 mr-1">
            <i class="fab fa-twitter"></i>
          </button>
          <button class="p-button p-button-secondary px-2 py-1 mr-1">
            <i class="fab fa-facebook"></i>
          </button>
          <button class="p-button p-button-secondary px-2 py-1 mr-1">
            <i class="fas fa-share-alt-square"></i>
          </button>
        </div>
        <div class="mt-2">
          <Button
            v-if="status === ''"
            label="Request to Participate"
            icon="pi pi-plus"
            class="p-button-primary"
            v-on:click="manageParticipation('request')"
          />
          <Button
            v-if="status === 'requested'"
            label="Cancel Participation Request"
            icon="pi pi-times"
            class="p-button-warning"
            v-on:click="manageParticipation('cancel')"
          />
          <Button
            v-if="status === 'approved'"
            label="Mark as Participated"
            icon="pi pi-check"
            class="p-button-success"
            v-on:click="manageParticipation('participated')"
          />
          <Button
            v-if="status === 'participated'"
            label="Participated"
            class="p-button-secondary"
          />
        </div>
      </div>
      <div class="p-col-7">
        <div class="p-grid">
          <div class="p-col-12">
            <h3>{{ event.title }}</h3>
          </div>
          <div class="p-col-3">
            Time:
          </div>
          <div class="p-col-9">
            <div v-if="event.onDatetime">
              {{
                event.onDatetime
                  | dateParse('YYYY-MM-DDTHH:mm:ss.000+0000')
                  | dateFormat('MMM DD, YYYY h:mma')
              }}
            </div>
          </div>

          <div class="p-col-3">
            Location:
          </div>
          <div class="p-col-9">
            {{ event.location }}

            <a
              :href="
                'https://www.google.com/maps/search/?api=1&query=' +
                event.geoLat +
                ',' +
                event.geoLon
              "
              target="_blank"
            >
              (open in map)
            </a>
          </div>

          <div v-if="event.capacity" class="p-col-3">
            Capacity:
          </div>
          <div v-if="event.capacity" class="p-col-9">
            {{ event.capacity }}
          </div>

          <div class="p-col-3">
            +18 only
          </div>
          <div class="p-col-9">
            {{ event.plus18Only ? 'Yes' : 'Open for everyone' }}
          </div>

          <div class="p-col-3">
            Fee:
          </div>
          <div class="p-col-9">{{ event.fee }} $</div>

          <div class="p-col-3">
            Organizer:
          </div>
          <div class="p-col-9">
            {{ event.organizer }}
          </div>

          <div class="p-col-3">Description:</div>
          <div class="p-col-9">
            {{ event.description }}
          </div>
        </div>
      </div>
    </div>
  </div>
</template>

<script lang="ts">
/* eslint-disable space-before-function-paren */

import { Component, Prop, Vue, Model } from 'vue-property-decorator'
import { Event } from './Event'
import { EventService } from './EventService'

@Component
export default class EventView extends Vue {
  @Model() model!: Event

  status: string = '' // '', 'requested', 'approved'
  get event() {
    return this.model || {}
  }

  eventApi = EventService.getInstance()

  mounted() {
    this.doCheckParticipation()
  }

  doCheckParticipation() {
    this.eventApi
      .getParticipation(this.event.id)
      .then((resp: any) => (this.status = resp.data.status))
      .catch((err: any) => console.log(err))
  }

  manageParticipation(action: string) {
    this.eventApi
      .manageParticipation(this.event.id, action)
      .then((resp: any) => (this.status = resp.data.status))
      .catch((err: any) => console.log(err))
  }
}
</script>

<style scoped lang="less">
.cover {
  border: solid 1px lightgray;
  max-width: 200px;
  max-height: 200px;
}
</style>
