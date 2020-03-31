<template>
  <div class="profile-page">
    <div class="p-grid">
      <div class="p-col-8 p-offset-2 text-left">
        <GeneralInformationForm />
      </div>
      <div class="p-col-8 p-offset-2 text-left">
        <UserHobbies />
      </div>
      <div class="p-col-8 p-offset-2 text-left">
        <UserEvents />
      </div>
      <div class="p-col-8 p-offset-2 text-left">
        <UserBlogs />
      </div>
      <div class="p-col-8 p-offset-2 text-left">
        <UserChangeEmailPassword />
      </div>
    </div>
  </div>
</template>

<script lang="ts">
import { Component, Prop, Vue } from 'vue-property-decorator'

@Component
export default class Register extends Vue {
  // eslint-disable-next-line space-before-function-paren
  back() {
    window.history.back()
  }
}
</script>

<style scoped lang="less"></style>
