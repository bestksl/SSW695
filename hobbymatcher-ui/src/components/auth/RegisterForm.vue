<template>
  <div class="register-form">
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
          <div class="p-offset-1 p-col-2 p-align-center">
            <div class="text-right pt-1">Date of Birth</div>
          </div>
          <div class="p-col-8 text-left">
            <ValidationProvider
              name="date of birth"
              v-slot="{ errors }"
              rules="required"
            >
              <Calendar
                v-model="model.dateOfBirth"
                view="month"
                dateFormat="mm/yy"
                :yearNavigator="true"
                yearRange="1970:2004"
              />
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
            <label>Password</label>
          </div>
          <div class="p-col-7 text-left">
            <ValidationProvider
              name="password"
              v-slot="{ errors }"
              rules="required|min:6|max:32"
            >
              <Password v-model="model.password" size="40" />
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
              v-on:click="back()"
            />
            <Button
              label="Register"
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
import { NewUser } from './NewUser'

@Component
export default class RegisterForm extends Vue {
  api: AuthService = AuthService.getInstance()

  model: NewUser = {
    firstName: 'Jafar',
    lastName: 'Abbasi',
    dateOfBirth: new Date(1991, 2, 3),
    nickName: 'jafar',
    email: 'jafar@gmail.com',
    password: 'jafarjafar'
  } as NewUser

  back() {
    window.history.back()
  }

  save() {
    this.api
      .register(this.model)
      .then((resp: any) => {
        this.model = {} as NewUser
        Vue.toasted.show('You have been registered.', { duration: 5000 })
      })
      .catch((err: any) => {
        Vue.toasted.show('Failed to register.', { duration: 5000 })
        console.log(err)
      })
  }
}
</script>

<style scoped lang="less"></style>
