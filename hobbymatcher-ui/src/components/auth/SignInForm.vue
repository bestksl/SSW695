<template>
  <div class="signin-form">
    <ValidationObserver v-slot="{ invalid }">
      <form @submit.prevent="save">
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
          <div class="p-col-2 p-offset-2 text-left">
            <label>Email</label>
          </div>
          <div class="p-col-6 text-left">
            <ValidationProvider name="email" v-slot="{ errors }" rules="required|email|min:2">
              <InputText type="text" v-model="model.email" />
              <ul v-if="errors.length" class="v-error">
                <li v-for="error in errors" v-bind:key="error">{{ error }}</li>
              </ul>
            </ValidationProvider>
          </div>
          <div class="p-col-2 p-offset-2 text-left">
            <label>PassWord</label>
          </div>
          <div class="p-col-6 text-left">
            <ValidationProvider name="password" v-slot="{ errors }" rules="required|min:6">
              <Password v-model="model.passWord" />
              <ul v-if="errors.length" class="v-error">
                <li v-for="error in errors" v-bind:key="error">{{ error }}</li>
              </ul>
            </ValidationProvider>
          </div>
          <div class="p-col-8 p-offset-2 text-left">
            <Button
              label="Back"
              icon="pi pi-chevron-left"
              class="p-button-secondary"
              v-on:click="back()"
            />
            <Button
              label="Sign In"
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
import { Component, Prop, Vue } from 'vue-property-decorator'
import { AuthService } from './AuthService'
import { AuthUser } from './AuthUser'

@Component
export default class SignInForm extends Vue {
  model: AuthUser = {} as AuthUser
  api: AuthService = new AuthService()

  back () {
    window.history.back()
  }

  save () {
    this.api
      .signin(this.model)
      .then((resp: any) => {
        this.model = {} as AuthUser
        Vue.toasted.show('You have been signed in.', { duration: 5000 })
      })
      .catch((err: any) => {
        Vue.toasted.show('Failed to signin.', { duration: 5000 })
        console.log(err)
      })
  }
}
</script>

<style scoped lang="less"></style>
