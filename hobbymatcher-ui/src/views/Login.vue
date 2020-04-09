<template>
  <div class="login-page">
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
    <LoginForm></LoginForm>
  </div>
</template>

<script lang="ts">
/* eslint-disable space-before-function-paren */
import { Component, Prop, Vue } from 'vue-property-decorator'
import { AuthService } from '../components/auth/AuthService'

@Component
export default class Login extends Vue {
  authApi = AuthService.getInstance()

  mounted() {
    if (this.authApi.isLogin) {
      Vue.toasted.show('You have to logout first.', { duration: 5000 })
      this.$router.back()
    }
  }

  back() {
    window.history.back()
  }
}
</script>

<style scoped lang="less"></style>
