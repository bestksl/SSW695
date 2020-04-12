<template>
  <ul class="manage-participants px-2">
    <li v-if="(participants || []).length < 1">
      No Participants Yet.
    </li>
    <li
      v-for="participant of participants"
      :key="participant.email"
      class="d-flex"
    >
      <div class="mr-2">
        <div>
          <!-- when there is photoId -->
          <img
            v-if="participant.photoId"
            :src="
              'http://localhost:8080/hobbymatcher/files/' + participant.photoId
            "
            class="mr-1 user-photo rounded-circle"
          />
          <!-- else -->
          <img
            v-if="!participant.photoId"
            src="@/assets/images/logo-200x200.png"
            class="mr-1 user-photo rounded-circle"
          />
        </div>
      </div>
      <div>
        <div>
          {{ participant.firstName }}
          <span v-if="participant.lastName">({{ participant.lastName }}) </span>
          <span v-if="participant.nickName">({{ participant.nickName }}) </span>
        </div>
        <div>
          <a :href="'mailto:' + participant.email">{{ participant.email }}</a>
        </div>
      </div>
      <div class="flex-grow-1"></div>
      <div>
        <small
          class="d-block text-right"
          v-if="participant.requestedOnDatetime"
        >
          Requested On:
          {{
            participant.requestedOnDatetime
              | dateParse('YYYY-MM-DDTHH:mm:ss.000+0000')
              | dateFormat('MMM DD, YYYY h:mma')
          }}
        </small>
        <small class="d-block text-right" v-if="participant.approvedOnDatetime">
          Approved On:
          {{
            participant.approvedOnDatetime
              | dateParse('YYYY-MM-DDTHH:mm:ss.000+0000')
              | dateFormat('MMM DD, YYYY h:mma')
          }}
        </small>
      </div>
      <div v-if="!participant.approvedOnDatetime" class="ml-3">
        <Button
          title="Approve"
          icon="pi pi-check"
          class="p-button-primary"
          v-on:click="manageParticipant(participant.id, 'approve')"
        />
        <Button
          title="Reject"
          icon="pi pi-times"
          class="p-button-danger"
          v-on:click="manageParticipant(participant.id, 'reject')"
        />
      </div>
    </li>
  </ul>
</template>

<script lang="ts">
/* eslint-disable space-before-function-paren */

import { Component, Prop, Vue, Model } from 'vue-property-decorator'
import { Event } from './Event'
import { EventService } from './EventService'
import { AuthService } from '../auth/AuthService'

@Component
export default class ManageParticipants extends Vue {
  @Model() eventId!: number

  participants = []

  eventApi = EventService.getInstance()

  mounted() {
    this.reload()
  }

  reload() {
    this.eventApi
      .loadParticipants(this.eventId)
      .then((resp: any) => (this.participants = resp.data.list))
      .catch((err: any) => console.log(err))
  }

  manageParticipant(participantId: number, action: string) {
    if (confirm('Are you sure?')) {
      this.eventApi
        .manageParticipant(this.eventId, participantId, action)
        .then((resp: any) => {
          this.reload()
          Vue.toasted.show('Updated.', { duration: 3000 })
        })
        .catch((err: any) => console.log(err))
    }
  }
}
</script>

<style scoped lang="less">
.user-photo {
  width: 32px;
  height: 32px;
  border: solid 1px lightgray;
}
</style>
