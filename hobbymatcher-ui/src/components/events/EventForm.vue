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
            <ValidationProvider
              name="location"
              v-slot="{ errors }"
              rules="required"
            >
              <InputText
                id="event-location"
                type="text"
                v-model="event.location"
                class="w-75"
              />
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
              rules="numeric|min_value:1|max_value:999999"
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
            <div>
              <img
                v-if="!event.url"
                src="@/assets/images/logo-200x200.png"
                style="width: 12rem; height: 12rem; border: solid 1px lightgray;"
              />
              <img
                v-if="event.url"
                :src="event.url"
                style="width: 12rem; height: 12rem; border: solid 1px lightgray;"
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
import { Component, Prop, Vue, Model } from 'vue-property-decorator'
import { EventService } from './EventService'
import { Event } from './Event'
import { HobbyService } from '../hobbies/HobbyService'
import { Hobby } from '../hobbies/Hobby'

@Component
export default class EventForm extends Vue {
  @Model() model!: Event
  internal: Event = {} as any

  hobbies: Hobby[] = []

  // eslint-disable-next-line space-before-function-paren
  get event(): any {
    return this.model || this.internal
  }
  // event.file: event picture to upload
  // event.url: event picture preview

  hobbyApi: HobbyService = new HobbyService()
  eventApi: EventService = new EventService()

  // eslint-disable-next-line space-before-function-paren
  mounted() {
    this.hobbyApi
      .list()
      .then((resp: any) => (this.hobbies = resp.data.list))
      .catch((err: any) => console.log(err))
  }

  // eslint-disable-next-line space-before-function-paren
  clearFileSelect($event: any) {
    this.event.url = null
    this.event.file = null
  }

  // eslint-disable-next-line space-before-function-paren
  onFileSelect($event: any) {
    if ($event.files && $event.files[0]) {
      this.event.file = $event.files[0]
      const reader = new FileReader()
      reader.onload = (e: any) => (this.event.url = e.target.result)
      reader.readAsDataURL(this.event.file)
    }
  }

  // eslint-disable-next-line space-before-function-paren
  back() {
    window.history.back()
  }

  // eslint-disable-next-line space-before-function-paren
  save() {
    const data = new FormData()
    data.append('hobbyId', this.event.hobbyId)
    data.append('title', this.event.title)
    data.append('onDatetime', this.event.onDatetime)
    data.append('location', this.event.location)
    data.append('capacity', this.event.capacity)
    data.append('description', this.event.description)
    data.append('plus18Only', this.event.plus18Only)
    data.append('organizer', this.event.organizer)
    data.append('file', this.event.file)

    this.eventApi
      .add(data)
      .then((resp: any) => {
        Vue.toasted.show('Event has been added.', { duration: 5000 })
        this.back()
      })
      .catch((err: any) => {
        Vue.toasted.show('Failed to add the event.', { duration: 5000 })
        console.log(err)
      })
  }
}
</script>

<style lang="less">
.event-form .p-dropdown-filter-container {
  width: 100%;
}
</style>
