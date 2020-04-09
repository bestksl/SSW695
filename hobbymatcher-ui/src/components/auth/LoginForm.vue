<template>
  <div class="login-form">
    <ValidationObserver v-slot="{ invalid }">
      <form @submit.prevent="save">
        <div class="p-grid">
          <div class="p-offset-4 p-col-4">
            <h3 class="text-center">
              Login to Hobby Matcher
            </h3>
          </div>

          <div class="p-offset-4 p-col-4 text-left">
            <ValidationProvider
              name="email"
              v-slot="{ errors }"
              rules="required|email|min:2|max:64"
            >
              <InputText
                type="text"
                v-model="model.email"
                placeholder="Email Address"
                class="w-100"
              />
              <ul v-if="errors.length" class="v-error">
                <li v-for="error in errors" v-bind:key="error">{{ error }}</li>
              </ul>
            </ValidationProvider>
          </div>
          <div class="p-offset-4 p-col-4 text-left">
            <ValidationProvider
              name="password"
              v-slot="{ errors }"
              rules="required|min:6|max:32"
            >
              <Password
                v-model="model.password"
                placeholder="Password"
                class="w-100"
                :feedback="false"
              />
              <ul v-if="errors.length" class="v-error">
                <li v-for="error in errors" v-bind:key="error">{{ error }}</li>
              </ul>
            </ValidationProvider>
          </div>
          <div class="p-offset-4 p-col-4 text-center">
            <Button
              label="Back"
              icon="pi pi-chevron-left"
              class="p-button-secondary"
              v-on:click="window.history.back()"
            />
            <Button
              label="Login"
              icon="pi pi-check"
              class="p-button-primary"
              :disabled="invalid"
            />
          </div>
        </div>
      </form>
    </ValidationObserver>
  </div>
</template>

<script lang="ts">
/* eslint-disable space-before-function-paren */
import { Component, Prop, Vue } from 'vue-property-decorator'
import { AuthService } from './AuthService'
import { LoginUser } from './LoginUser'

@Component
export default class LoginForm extends Vue {
  authApi: AuthService = AuthService.getInstance()

  model: LoginUser = {
    email: 'jafar@gmail.com',
    password: 'jafarjafar'
  } as LoginUser

  save() {
    this.authApi
      .login(this.model)
      .then((resp: any) => {
        this.model = {} as LoginUser
        this.authApi.storeToken(resp.data.jwt)
        this.authApi.checkLogin()
        this.authApi.goHome(this.$router)
        Vue.toasted.show('You have been logged in.', { duration: 5000 })
      })
      .catch((err: any) => {
        Vue.toasted.show('Failed to login.', { duration: 5000 })
        console.log(err)
      })
  }
}
</script>

<style scoped lang="less"></style>
