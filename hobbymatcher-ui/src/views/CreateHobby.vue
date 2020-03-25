<template>
  <div class="hobby-page">
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
    <HobbyForm />
  </div>
</template>

<script lang="ts">
import { Component, Prop, Vue, Model } from 'vue-property-decorator'
import { AuthService } from '../components/auth/AuthService'

@Component
export default class CreateHobby extends Vue {
  authApi = AuthService.getInstance()

  // eslint-disable-next-line space-before-function-paren
  mounted() {
    if (!this.authApi.isLogin) {
      Vue.toasted.show('You have to be logged in to access this page.', { duration: 5000 })
      this.$router.back()
    }
  }

  // eslint-disable-next-line space-before-function-paren
  back() {
    window.history.back()
  }
}
</script>

<style lang="less"></style>
