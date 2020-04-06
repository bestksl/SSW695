<template>
  <div class="event-page">
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
    <EventForm />
  </div>
</template>

<script lang="ts">
/* eslint-disable space-before-function-paren */
import { Component, Prop, Vue, Model } from 'vue-property-decorator'
import { AuthService } from '../components/auth/AuthService'

@Component
export default class CreateEvent extends Vue {
  authApi = AuthService.getInstance()

  mounted() {
    this.authApi.shouldBeLoggedIn(this.$router)
  }

  back() {
    window.history.back()
  }
}
</script>

<style lang="less"></style>
