<template>
  <div class="signin-form">
    <ValidationObserver v-slot="{ invalid }">
      <form @submit.prevent="save">
        <div class="p-grid">
          <div class="p-offset-4 p-col-4">
            <h3 class="text-center">
              Sign In to Hobby Matcher
            </h3>
          </div>

          <div class="p-offset-4 p-col-4 text-left">
            <ValidationProvider
              name="email"
              v-slot="{ errors }"
              rules="required|email|min:2"
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
              rules="required|min:6"
            >
              <Password
                v-model="model.passWord"
                placeholder="Password"
                class="w-100"
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

  // eslint-disable-next-line space-before-function-paren
  save() {
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
