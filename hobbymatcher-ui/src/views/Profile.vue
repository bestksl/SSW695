<template>
  <div class="profile-page">
    <div class="p-grid">
      <div class="p-col-8 p-offset-2 text-left">
        <GeneralInformationForm />
      </div>
      <div class="p-col-8 p-offset-2 text-left">
        <hr class="mb-5" />
        <UserHobbies />
      </div>
      <div class="p-col-8 p-offset-2 text-left">
        <hr class="mb-5" />
        <UserEvents />
      </div>
      <div class="p-col-8 p-offset-2 text-left">
        <hr class="mb-5" />
        <UserBlogs />
      </div>
      <div class="p-col-8 p-offset-2 text-left">
        <hr class="mb-5" />
        <UserChangeEmailPassword />
      </div>
    </div>
  </div>
</template>

<script lang="ts">
/* eslint-disable space-before-function-paren */
import { Component, Prop, Vue } from 'vue-property-decorator'

@Component
export default class Register extends Vue {
  back() {
    window.history.back()
  }
}
</script>

<style scoped lang="less"></style>
