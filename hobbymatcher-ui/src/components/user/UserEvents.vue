<template>
  <div class="hobby-thumbs">
    <div class="d-flex mb-4">
      <SelectButton
        v-model="selected"
        :options="[
          { title: 'Upcoming Events', value: 'coming' },
          { title: 'Past Events', value: 'past' },
          { title: 'Events Hold', value: 'hold' }
        ]"
        optionLabel="title"
        optionValue="value"
        @input="reload()"
      />

      <span class="flex-grow-1"></span>

      <router-link to="/events/create">
        <Button
          type="button"
          label="Create an Event"
          icon="pi pi-plus"
          class="p-button-secondary"
        />
      </router-link>
    </div>
    <div v-if="!events || events.length == 0" class="text-center">
      No Events
    </div>
    <EventThumb
      v-for="event of events"
      :key="event.id"
      :model="event"
      :userId="userId"
      class="d-inline-block mr-4 mb-4"
    />
  </div>
</template>

<script lang="ts">
/* eslint-disable space-before-function-paren */
import { Component, Prop, Vue, Model } from 'vue-property-decorator'
import { Event } from '../events/Event'
import { ProfileService } from './ProfileService'
import { AuthService } from '../auth/AuthService'

@Component
export default class UserEvents extends Vue {
  selected: any = 'coming'
  events: Event[] = []

  profileApi = ProfileService.getInstance()
  authApi = AuthService.getInstance()

  get userId() {
    return this.authApi!.response.userId
  }

  mounted() {
    this.reload()
  }

  reload() {
    this.profileApi
      .loadEvents(this.selected)
      .then((resp: any) => (this.events = resp.data.list))
      .catch((err: any) => console.log(err))
  }
}
</script>

<style scoped lang="less"></style>
