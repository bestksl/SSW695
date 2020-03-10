<template>
  <div class="event-form">
    <div class="p-grid">
      <div class="p-offset-1 p-col-2 p-align-center">
        <div class="text-right pt-1">Event title</div>
      </div>
      <div class="p-col-8 text-left">
        <InputText
          id="event-title"
          type="text"
          v-model="event.title"
          class="w-75"
        />
      </div>
      <div class="p-offset-1 p-col-2 p-align-center">
        <div class="text-right pt-1">Date and Time</div>
      </div>
      <div class="p-col-8 text-left">
        <Calendar id="event-datetime" v-model="event.datetime" />
      </div>
      <div class="p-offset-1 p-col-2 p-align-center">
        <div class="text-right pt-1">Location</div>
      </div>
      <div class="p-col-8 text-left">
        <InputText
          id="event-location"
          type="text"
          v-model="event.location"
          class="w-75"
        />
      </div>
      <div class="p-offset-1 p-col-2 p-align-center">
        <div class="text-right pt-1">Capacity</div>
      </div>
      <div class="p-col-8 text-left">
        <InputText id="event-capacity" type="text" v-model="event.capacity" />
      </div>
      <div class="p-offset-1 p-col-2 p-align-center">
        <div class="text-right pt-1">Description</div>
      </div>
      <div class="p-col-8 text-left">
        <Textarea
          id="event-description"
          v-model="event.description"
          rows="5"
          cols="30"
          class="w-75"
        />
      </div>
      <div class="p-offset-1 p-col-2 p-align-center">
        <div class="text-right pt-1">Only +18 of Age</div>
      </div>
      <div class="p-col-8 text-left">
        <Checkbox id="event-plus18" v-model="event.plus18" :binary="true" />
      </div>
      <div class="p-offset-1 p-col-2 p-align-center">
        <div class="text-right pt-1">Fee</div>
      </div>
      <div class="p-col-8 text-left">
        <InputText id="event-fee" type="text" v-model="event.fee" />
      </div>
      <div class="p-offset-1 p-col-2 p-align-center">
        <div class="text-right pt-1">Organizer</div>
      </div>
      <div class="p-col-8 text-left">
        <InputText
          id="event-organizer"
          type="text"
          v-model="event.organizer"
          class="w-75"
        />
      </div>
      <div class="p-offset-1 p-col-2 p-align-center">
        <div class="text-right pt-1">Cover Photo</div>
      </div>
      <div class="p-col-8 text-left">
        <div>
          <img
            src="@/assets/images/logo-200x200.png"
            style="width: 12rem; height: 12rem; border: solid 1px lightgray;"
          />
        </div>
        <div>
          <Button label="Upload" class="p-button-secondary" />
        </div>
      </div>
      <div class="p-col-8 p-offset-3 text-left">
        <Button
          label="Back"
          icon="pi pi-chevron-left"
          class="p-button-secondary"
          v-on:click="back()"
        />
        <Button
          label="Save"
          icon="pi pi-check"
          class="p-button-primary"
          v-on:click="save()"
        />
      </div>
    </div>
  </div>
</template>

<script lang="ts">
import { Component, Prop, Vue } from 'vue-property-decorator'
import { EventService } from './EventService'
import { Event } from './Event'

@Component
export default class EventForm extends Vue {
  @Prop() model!: Event
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
  api: EventService = new EventService()

  // eslint-disable-next-line space-before-function-paren
  mounted() {}

  // eslint-disable-next-line space-before-function-paren
  back() {
    window.history.back()
  }

  // eslint-disable-next-line space-before-function-paren
  save() {
    this.api
      .create(this.event)
      .then((resp: any) => {
        this.event = {} as Event
        Vue.toasted.show('Event Created.', { duration: 5000 })
      })
      .catch((err: any) => {
        Vue.toasted.show('Failed to save the event.', { duration: 5000 })
        console.log(err)
      })
  }
}
</script>

<style scoped lang="less"></style>
