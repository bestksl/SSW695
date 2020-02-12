<template>
  <div>
    <EventView />
    <div class="p-grid">
      <hr class="p-col-10 p-offset-1 py-0 my-3" />
    </div>
    <Reviews />
    <EventThumbs class="mt-3" />
  </div>
</template>

<script>
// @ is an alias to /src
import EventView from '@/components/events/EventView.vue'

export default {
  name: 'home',
  components: {
    EventView
  }
}
</script>
