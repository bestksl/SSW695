<template>
  <div class="event-form">
    <div class="p-grid">
      <div class="p-col-10 p-offset-1 text-left">
        <Button
          label="Back"
          icon="pi pi-chevron-left"
          class="p-button-secondary"
        />
      </div>
      <div class="p-col-10 p-offset-1">
        <hr />
      </div>

      <div class="p-col-3 p-offset-1 text-center">
        <div>
          <img src="@/assets/images/logo-200x200.png" class="cover" />
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
          <Button label="Join" icon="pi pi-plus" class="p-button-primary" />
        </div>
      </div>
      <div class="p-col-7 ">
        <div class="p-grid">
          <div class="p-col-12">
            <h3>{{ event.title }}</h3>
          </div>
          <div class="p-col-3">
            Time:
          </div>
          <div class="p-col-9">
            {{ event.datetime | dateFormat('MMMM DD, YYYY hh:mm A') }}
          </div>

          <div class="p-col-3">
            Location:
          </div>
          <div class="p-col-9">
            {{ event.location }}
          </div>

          <div v-if="event.capacity" class="p-col-3">
            Capacity:
          </div>
          <div v-if="event.capacity" class="p-col-9">
            {{ event.capacity }}
          </div>

          <div class="p-col-3">
            Only +18 of Age?
          </div>
          <div class="p-col-9">
            {{ event.plus18 ? 'Yes' : 'Open for everyone' }}
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
import { Component, Prop, Vue } from 'vue-property-decorator'
import { Event } from './event'

@Component
export default class EventView extends Vue {
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
}
</script>

<style scoped lang="less">
.cover {
  border: solid 1px lightgray;
  max-width: 200px;
  max-height: 200px;
}
</style>
