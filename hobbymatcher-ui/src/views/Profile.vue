<template>
  <div class="profile-page">
    <div class="p-grid">
      <div class="p-col-10 p-offset-1 text-left">
        <GeneralInformationForm />
      </div>
      <div class="p-col-10 p-offset-1 text-left">
        <UserHobbies />
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
