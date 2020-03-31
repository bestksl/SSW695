<template>
  <div class="event-view-page">
    <div class="p-grid">
      <div class="p-col-10 p-offset-1 text-left">
        <Button
          label="Back"
          icon="pi pi-chevron-left"
          class="p-button-secondary"
          v-on:click="back()"
        />
      </div>
      <div class="p-col-10 p-offset-1">
        <hr />
      </div>
    </div>
    <EventView v-model="event" />

    <div class="p-grid">
      <hr class="p-col-10 p-offset-1 py-0 my-3" />
    </div>
    <Reviews v-if="event.id" :type="'event'" :oId="event.id" />

    <div class="p-grid">
      <div class="p-col-10 p-offset-1 mt-4">
        <strong>Events you may interested in:</strong>
      </div>
      <div class="p-col-10 p-offset-1">
        <EventThumbs />
      </div>
    </div>
  </div>
</template>

<script lang="ts">
import { Component, Prop, Vue, Model } from 'vue-property-decorator'
import { EventService } from '../components/events/EventService'
import { Event } from '../components/events/Event'

@Component
export default class ViewEvent extends Vue {
  api = new EventService()

  event: Event = {} as any

  // eslint-disable-next-line space-before-function-paren
  back() {
    window.history.back()
  }

  // eslint-disable-next-line space-before-function-paren
  mounted() {
    this.api
      .get(this.$route.query.id)
      .then((resp: any) => (this.event = resp.data.event))
      .catch((err: any) => console.log(err))
  }
}
</script>

<style lang="less"></style>
