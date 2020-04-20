<template>
  <div class="event-form">
    <div class="p-grid">
      <div class="p-col-3 p-offset-1 text-center">
        <div>
          <img
            v-if="event.photoId"
            :src="API_URL + '/files/' + event.photoId"
            style="width: 12rem; height: 12rem; border: solid 1px lightgray;"
          />
        </div>
        <div class="mt-3">
          <social-sharing
            :url="API_URL + '/events/view?id=' + event.id"
            :title="'Event :' + event.title"
            :description="event.description"
            :quote="event.title + event.description"
            hashtags=""
            twitter-user="hobbymatcher"
            inline-template
          >
            <div>
              <network network="email" class="mr-2">
                <i class="fa fa-envelope"></i>
              </network>
              <network network="facebook" class="mr-2">
                <i class="fab fa-facebook"></i>
              </network>
              <network network="googleplus" class="mr-2">
                <i class="fab fa-google-plus"></i>
              </network>
              <network network="twitter" class="mr-2">
                <i class="fab fa-twitter"></i>
              </network>
              <network network="whatsapp" class="mr-2">
                <i class="fab fa-whatsapp"></i>
              </network>
            </div>
          </social-sharing>
        </div>
        <div v-if="isEventAdmin" class="mt-2">
          <Button
            label="Manage Participants"
            icon="pi pi-cog"
            class="p-button-primary"
            v-on:click="showAdminForm = true"
          />
        </div>
        <div v-if="canManageParticipation" class="mt-2">
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
            <a
              :href="
                'https://www.google.com/maps/search/?api=1&query=' +
                  event.geoLat +
                  ',' +
                  event.geoLon
              "
              target="_blank"
            >
              {{ event.location }}
            </a>
          </div>

          <div v-if="event.capacity" class="p-col-3">
            Capacity:
          </div>
          <div v-if="event.capacity" class="p-col-9">
            {{ event.capacity || '' }}
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
          <div class="p-col-9">
            <span v-if="event.fee"> {{ event.fee || '' }} $ </span>
            <span v-if="!event.fee">
              No Fee
            </span>
          </div>

          <div class="p-col-3">
            Organizer:
          </div>
          <div class="p-col-9">
            {{ event.organizer || '' }}
          </div>

          <div class="p-col-3">Description:</div>
          <div class="p-col-9">
            {{ event.description || '' }}
          </div>
        </div>
      </div>
    </div>

    <Dialog
      v-if="authApi.isLogin && event.id"
      :visible.sync="showAdminForm"
      :style="{ width: '50vw' }"
      :modal="true"
    >
      <ManageParticipants :eventId="event.id" />
    </Dialog>
  </div>
</template>

<script lang="ts">
/* eslint-disable space-before-function-paren */

import { Component, Prop, Vue, Model } from 'vue-property-decorator'
import { AuthService } from '../auth/AuthService'
import { EventService } from './EventService'
import { Event } from './Event'
import { apiUrl } from '../Api'

@Component
export default class EventView extends Vue {
  API_URL = apiUrl

  @Model() model!: Event

  status: string = '' // '', 'requested', 'approved'
  get event() {
    return this.model || {}
  }

  showAdminForm = false

  authApi = AuthService.getInstance()
  eventApi = EventService.getInstance()

  mounted() {
    this.authApi.ifLogin(() => this.doCheckParticipation())
  }

  doCheckParticipation() {
    this.eventApi
      .getParticipation(this.event.id)
      .then((resp: any) => (this.status = resp.data.status))
      .catch((err: any) => console.log(err))
  }

  manageParticipation(action: string) {
    if (confirm('Are you sure?')) {
      this.eventApi
        .manageParticipation(this.event.id, action)
        .then((resp: any) => (this.status = resp.data.status))
        .catch((err: any) => console.log(err))
    }
  }

  get canManageParticipation() {
    return (
      this.authApi.isLogin &&
      this.authApi.response.userId !== this.event.createdById
    )
  }

  get isEventAdmin() {
    return this.authApi.response.userId === this.event.createdById
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
