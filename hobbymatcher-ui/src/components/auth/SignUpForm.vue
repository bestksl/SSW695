<template>
  <div class="signup-form">
    <ValidationObserver v-slot="{ invalid }">
      <form @submit.prevent="save">
        <div class="p-grid">
          <div class="p-offset-1 p-col-2 text-right">
            <label>First Name</label>
          </div>
          <div class="p-col-7 text-left">
            <ValidationProvider
              name="first name"
              v-slot="{ errors }"
              rules="required|min:2|max:64"
            >
              <InputText type="text" v-model="model.firstName" size="40" />
              <ul v-if="errors.length" class="v-error">
                <li v-for="error in errors" v-bind:key="error">{{ error }}</li>
              </ul>
            </ValidationProvider>
          </div>
          <div class="p-offset-1 p-col-2 text-right">
            <label>Nick Name</label>
          </div>
          <div class="p-col-7 text-left">
            <ValidationProvider name="nick name" v-slot="{ errors }" rules="">
              <InputText type="text" v-model="model.nickName" size="40" />
              <ul v-if="errors.length" class="v-error">
                <li v-for="error in errors" v-bind:key="error">{{ error }}</li>
              </ul>
            </ValidationProvider>
          </div>
          <div class="p-offset-1 p-col-2 text-right">
            <label>Last Name</label>
          </div>
          <div class="p-col-7 text-left">
            <ValidationProvider name="last name" v-slot="{ errors }" rules="">
              <InputText type="text" v-model="model.lastName" size="40" />
              <ul v-if="errors.length" class="v-error">
                <li v-for="error in errors" v-bind:key="error">{{ error }}</li>
              </ul>
            </ValidationProvider>
          </div>
          <div class="p-offset-1 p-col-2 text-right">
            <label>Email</label>
          </div>
          <div class="p-col-7 text-left">
            <ValidationProvider
              name="email"
              v-slot="{ errors }"
              rules="required|email|min:2|max:64"
            >
              <InputText type="text" v-model="model.email" size="40" />
              <ul v-if="errors.length" class="v-error">
                <li v-for="error in errors" v-bind:key="error">{{ error }}</li>
              </ul>
            </ValidationProvider>
          </div>
          <div class="p-offset-1 p-col-2 text-right">
            <label>PassWord</label>
          </div>
          <div class="p-col-7 text-left">
            <ValidationProvider
              name="password"
              v-slot="{ errors }"
              rules="required|min:6|max:32"
            >
              <Password v-model="model.passWord" size="40" />
              <ul v-if="errors.length" class="v-error">
                <li v-for="error in errors" v-bind:key="error">{{ error }}</li>
              </ul>
            </ValidationProvider>
          </div>
          <div class="p-col-8 p-offset-3">
            <Button
              label="Back"
              icon="pi pi-chevron-left"
              class="p-button-secondary"
              v-on:click="window.history.back()"
            />
            <Button
              label="Sign Up"
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
import { NewUser } from './NewUser'

@Component
export default class SignUpForm extends Vue {
  model: NewUser = {} as NewUser
  api: AuthService = new AuthService()

  // eslint-disable-next-line space-before-function-paren
  save() {
    this.api
      .signup(this.model)
      .then((resp: any) => {
        this.model = {} as NewUser
        Vue.toasted.show('You have been signed up.', { duration: 5000 })
      })
      .catch((err: any) => {
        Vue.toasted.show('Failed to signup.', { duration: 5000 })
        console.log(err)
      })
  }
}
</script>

<style scoped lang="less"></style>
