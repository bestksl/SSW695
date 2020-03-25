<template>
  <div class="register-page">
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
    <RegisterForm></RegisterForm>
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
