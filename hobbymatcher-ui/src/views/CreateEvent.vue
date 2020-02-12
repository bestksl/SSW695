<template>
  <div>
    <EventForm />
  </div>
</template>

<script>
// @ is an alias to /src
import EventForm from '@/components/events/EventForm.vue'

export default {
  name: 'home',
  components: {
    EventForm
  }
}
</script>
