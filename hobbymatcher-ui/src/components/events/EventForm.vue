<template>
  <div class="event-form">
    <ValidationObserver v-slot="{ invalid }">
      <form @submit.prevent="save">
        <div class="p-grid">
          <div class="p-offset-1 p-col-2 text-right">
            Hobby:
          </div>
          <div class="p-col-7">
            <ValidationProvider
              name="hobby"
              v-slot="{ errors }"
              rules="required"
            >
              <Dropdown
                v-model="event.hobbyId"
                :options="hobbies"
                :filter="true"
                placeholder="Specify the Hobby"
                optionLabel="name"
                optionValue="id"
                class="w-50"
              />
              <ul v-if="errors.length" class="v-error">
                <li v-for="error in errors" v-bind:key="error">{{ error }}</li>
              </ul>
            </ValidationProvider>
          </div>

          <div class="p-offset-1 p-col-2 p-align-center">
            <div class="text-right pt-1">Event title</div>
          </div>
          <div class="p-col-8 text-left">
            <ValidationProvider
              name="title"
              v-slot="{ errors }"
              rules="required|min:4|max:128"
            >
              <InputText
                id="event-title"
                type="text"
                v-model="event.title"
                class="w-75"
              />
              <ul v-if="errors.length" class="v-error">
                <li v-for="error in errors" v-bind:key="error">{{ error }}</li>
              </ul>
            </ValidationProvider>
          </div>
          <div class="p-offset-1 p-col-2 p-align-center">
            <div class="text-right pt-1">Date and Time</div>
          </div>
          <div class="p-col-8 text-left">
            <ValidationProvider
              name="date and time"
              v-slot="{ errors }"
              rules="required"
            >
              <Calendar v-model="event.onDatetime" />
              <ul v-if="errors.length" class="v-error">
                <li v-for="error in errors" v-bind:key="error">{{ error }}</li>
              </ul>
            </ValidationProvider>
          </div>
          <div class="p-offset-1 p-col-2 p-align-center">
            <div class="text-right pt-1">Location</div>
          </div>
          <div class="p-col-8 text-left">
            <label>
              {{ event.location }}
            </label>
            <ToggleButton
              v-model="locationToggle"
              onIcon="pi pi-search"
              offIcon="pi pi-search"
            />
            <ValidationProvider
              name="location"
              v-slot="{ errors }"
              rules="required"
            >
              <div v-if="locationToggle" class="mt-2">
                <AutoComplete
                  v-model="event.locationObj"
                  :suggestions="locations"
                  @complete="loadLocations($event)"
                  @item-select="storeLatLng()"
                  field="display_name"
                  class="w-75"
                />
              </div>
              <ul v-if="errors.length" class="v-error">
                <li v-for="error in errors" v-bind:key="error">{{ error }}</li>
              </ul>
            </ValidationProvider>
          </div>
          <div class="p-offset-1 p-col-2 p-align-center">
            <div class="text-right pt-1">Capacity</div>
          </div>
          <div class="p-col-8 text-left">
            <ValidationProvider
              name="capacity"
              v-slot="{ errors }"
              rules="numeric|min_value:1|max_value:9999"
            >
              <InputText
                id="event-capacity"
                type="text"
                v-model="event.capacity"
              />
              <ul v-if="errors.length" class="v-error">
                <li v-for="error in errors" v-bind:key="error">{{ error }}</li>
              </ul>
            </ValidationProvider>
          </div>
          <div class="p-offset-1 p-col-2 p-align-center">
            <div class="text-right pt-1">Description</div>
          </div>
          <div class="p-col-8 text-left">
            <ValidationProvider
              name="description"
              v-slot="{ errors }"
              rules="max:512"
            >
              <Textarea
                id="event-description"
                v-model="event.description"
                rows="5"
                cols="30"
                class="w-75"
              />
              <ul v-if="errors.length" class="v-error">
                <li v-for="error in errors" v-bind:key="error">{{ error }}</li>
              </ul>
            </ValidationProvider>
          </div>
          <div class="p-offset-1 p-col-2 p-align-center">
            <div class="text-right pt-1">
              +18 Only:
            </div>
          </div>
          <div class="p-col-8 text-left">
            <ValidationProvider
              name="age restriction"
              v-slot="{ errors }"
              rules="required"
            >
              <Checkbox
                id="event-plus18"
                v-model="event.plus18Only"
                :binary="true"
              />
              <ul v-if="errors.length" class="v-error">
                <li v-for="error in errors" v-bind:key="error">{{ error }}</li>
              </ul>
            </ValidationProvider>
          </div>
          <div class="p-offset-1 p-col-2 p-align-center">
            <div class="text-right pt-1">Fee</div>
          </div>
          <div class="p-col-8 text-left">
            <ValidationProvider
              name="fee"
              v-slot="{ errors }"
              rules="numeric|min_value:0|max_value:999999"
            >
              <InputText id="event-fee" type="text" v-model="event.fee" />
              <ul v-if="errors.length" class="v-error">
                <li v-for="error in errors" v-bind:key="error">{{ error }}</li>
              </ul>
            </ValidationProvider>
          </div>
          <div class="p-offset-1 p-col-2 p-align-center">
            <div class="text-right pt-1">Organizer</div>
          </div>
          <div class="p-col-8 text-left">
            <ValidationProvider
              name="organizer"
              v-slot="{ errors }"
              rules="required|min:4|max:128"
            >
              <InputText
                id="event-organizer"
                type="text"
                v-model="event.organizer"
                class="w-75"
              />
              <ul v-if="errors.length" class="v-error">
                <li v-for="error in errors" v-bind:key="error">{{ error }}</li>
              </ul>
            </ValidationProvider>
          </div>
          <div class="p-offset-1 p-col-2 p-align-center">
            <div class="text-right pt-1">Cover Photo</div>
          </div>
          <div class="p-col-8 text-left">
            <div v-if="imgForceUpdate">
              <!-- when there is photoId -->
              <img
                v-if="!event.url && event.photoId"
                :src="
                  'http://localhost:8080/hobbymatcher/files/' + event.photoId
                "
                style="
                  width: 12rem;
                  height: 12rem;
                  border: solid 1px lightgray;
                "
              />
              <!-- else -->
              <!-- if no photo is selected yet -->
              <img
                v-if="!event.url && !event.photoId"
                src="@/assets/images/logo-200x200.png"
                style="
                  width: 12rem;
                  height: 12rem;
                  border: solid 1px lightgray;
                "
              />
              <!-- if selected but not uploaded yet -->
              <img
                v-if="event.url"
                :src="event.url"
                style="
                  width: 12rem;
                  height: 12rem;
                  border: solid 1px lightgray;
                "
              />
            </div>
            <div>
              <FileUpload
                mode="basic"
                accept="image/*"
                :maxFileSize="1000000"
                @select="onFileSelect"
                @clear="clearFileSelect"
              />
            </div>
          </div>

          <div class="p-col-8 p-offset-3 text-left">
            <Button
              type="button"
              label="Back"
              icon="pi pi-chevron-left"
              class="p-button-secondary"
              v-on:click="back()"
            />
            <Button
              v-if="event.id"
              label="Update"
              icon="pi pi-check"
              class="p-button-success"
              :disabled="invalid"
            />
            <Button
              v-if="!event.id"
              label="Create"
              icon="pi pi-check"
              class="p-button-primary"
              :disabled="invalid"
            />
          </div>
        </div>
      </form>
    </ValidationObserver>
  </div>
</template>

<script lang="ts">
/* eslint-disable space-before-function-paren */
/* eslint-disable comma-dangle */

import { Component, Prop, Vue, Model } from 'vue-property-decorator'
import { EventService } from './EventService'
import { Event } from './Event'
import { HobbyService } from '../hobbies/HobbyService'
import { Hobby } from '../hobbies/Hobby'

@Component
export default class EventForm extends Vue {
  @Model() id!: number

  event: any /* Event */ = {
    plus18Only: false,
    capacity: '',
    description: '',
    fee: '',
  } as any
  // event.file: event picture to upload
  // event.url: event picture preview
  imgForceUpdate = Math.random()

  locations = []
  locationToggle = false
  hobbies: Hobby[] = []

  hobbyApi: HobbyService = new HobbyService()
  eventApi: EventService = new EventService()

  mounted() {
    this.hobbyApi
      .list()
      .then((resp: any) => (this.hobbies = resp.data.list))
      .catch((err: any) => console.log(err))

    if (this.id) {
      this.doLoad()
    }
  }

  doLoad() {
    this.eventApi
      .get(this.id)
      .then((resp: any) => {
        const event = resp.data.event
        event.onDatetime = new Date(event.onDatetime)
        this.event = event
      })
      .catch((err: any) => console.log(err))
  }

  clearFileSelect($event: any) {
    this.event.url = null
    this.event.file = null
  }

  onFileSelect($event: any) {
    if ($event.files && $event.files[0]) {
      this.event.file = $event.files[0]
      const reader = new FileReader()
      reader.onload = (e: any) => {
        this.event.url = e.target.result
        this.imgForceUpdate = Math.random()
      }
      reader.readAsDataURL(this.event.file)
    }
  }

  loadLocations($event: any) {
    this.eventApi
      .suggestLocations($event.query)
      .then((resp: any) => (this.locations = resp.data))
      .catch((err: any) => {
        console.log(err)
      })
  }

  storeLatLng() {
    this.event.location = this.event.locationObj.display_name
    const addr = this.event.locationObj.address
    this.event.locationShort = [
      addr.city,
      this.eventApi.getStateShort(addr.state),
    ]
      .filter((add) => add)
      .join(', ')
    this.event.geoLat = this.event.locationObj.lat
    this.event.geoLon = this.event.locationObj.lon

    this.locationToggle = false
  }

  back() {
    window.history.back()
  }

  save() {
    if (this.event.id) {
      this.doUpdate()
    } else {
      this.doSave()
    }
  }

  getFormData() {
    const data = new FormData()
    if (this.event.id) {
      data.append('id', this.event.id) // only in update
    }
    data.append('hobbyId', this.event.hobbyId)
    data.append('title', this.event.title)
    data.append('onDatetime', this.event.onDatetime)
    data.append('location', this.event.location)
    data.append('locationShort', this.event.locationShort)
    data.append('geoLat', this.event.geoLat)
    data.append('geoLon', this.event.geoLon)
    data.append('capacity', this.event.capacity)
    data.append('description', this.event.description)
    data.append('plus18Only', this.event.plus18Only)
    data.append('organizer', this.event.organizer)
    data.append('file', this.event.file)
    return data
  }

  doSave() {
    this.eventApi
      .add(this.getFormData())
      .then((resp: any) => {
        Vue.toasted.show('Event has been added.', { duration: 5000 })
        this.back()
      })
      .catch((err: any) => {
        Vue.toasted.show('Failed to add the event.', { duration: 5000 })
        console.log(err)
      })
  }

  doUpdate() {
    this.eventApi
      .update(this.getFormData())
      .then((resp: any) => {
        Vue.toasted.show('Event has been updated.', { duration: 5000 })
        this.back()
      })
      .catch((err: any) => {
        Vue.toasted.show('Failed to update the event.', { duration: 5000 })
        console.log(err)
      })
  }
}
</script>

<style lang="less">
.event-form .p-dropdown-filter-container {
  width: 100%;
}
.event-form .p-autocomplete-input {
  width: 100%;
}
</style>
