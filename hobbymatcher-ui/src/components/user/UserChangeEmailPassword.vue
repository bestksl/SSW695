<template>
  <div>
    <div class="email-form">
      <ValidationObserver v-slot="{ invalid }">
        <form @submit.prevent="saveEmail">
          <div class="p-grid">
            <div class="p-col-3 text-right">
              Email Address:
            </div>
            <div class="p-col-7">
              <ValidationProvider
                name="email"
                v-slot="{ errors }"
                rules="required|email|min:2|max:64"
              >
                <InputText type="text" v-model="email" class="w-100" />
                <ul v-if="errors.length" class="v-error">
                  <li v-for="error in errors" v-bind:key="error">
                    {{ error }}
                  </li>
                </ul>
              </ValidationProvider>
            </div>
            <div class="p-col-2">
              <Button
                label="Update"
                icon="pi pi-check"
                class="p-button-success"
                :disabled="invalid"
              />
            </div>
          </div>
        </form>
      </ValidationObserver>
    </div>
    <div class="password-form mt-5">
      <ValidationObserver v-slot="{ invalid }">
        <form @submit.prevent="savePassword">
          <div class="p-grid">
            <div class="p-col-3 text-right">
              Current Password
            </div>
            <div class="p-col-7">
              <ValidationProvider
                name="old password"
                v-slot="{ errors }"
                rules="required"
              >
                <Password
                  v-model="currentPassword"
                  placeholder="******"
                  class="w-100"
                  :feedback="false"
                />
                <ul v-if="errors.length" class="v-error">
                  <li v-for="error in errors" v-bind:key="error">
                    {{ error }}
                  </li>
                </ul>
              </ValidationProvider>
            </div>
            <div class="p-col-2">
              <Button
                label="Update"
                icon="pi pi-check"
                class="p-button-success"
                :disabled="invalid"
              />
            </div>
            <div class="p-col-3 text-right">
              New Password
            </div>
            <div class="p-col-7">
              <div>
                <ValidationProvider
                  name="password"
                  v-slot="{ errors }"
                  rules="required|min:6|max:32"
                >
                  <Password
                    v-model="newPassword"
                    placeholder="******"
                    class="w-100"
                    ref="password"
                  />
                  <ul v-if="errors.length" class="v-error">
                    <li v-for="error in errors" v-bind:key="error">
                      {{ error }}
                    </li>
                  </ul>
                </ValidationProvider>
              </div>
              <div class="mt-2">
                <ValidationProvider
                  name="confirmed password"
                  v-slot="{ errors }"
                  rules="required|min:6|max:32|confirmed:password"
                >
                  <Password
                    v-model="confirmedPassword"
                    placeholder="******"
                    class="w-100"
                    :feedback="false"
                  />
                  <ul v-if="errors.length" class="v-error">
                    <li v-for="error in errors" v-bind:key="error">
                      {{ error }}
                    </li>
                  </ul>
                </ValidationProvider>
              </div>
            </div>
          </div>
        </form>
      </ValidationObserver>
    </div>
  </div>
</template>

<script lang="ts">
import { Component, Prop, Vue, Model } from 'vue-property-decorator'
import { ProfileService } from './ProfileService'
import { Hobby } from '../hobbies/Hobby'
import { AuthService } from '../auth/AuthService'

@Component
export default class UserChangeEmailPassword extends Vue {
  email: string = ''
  currentPassword: string = ''
  newPassword: string = ''
  confirmedPassword: string = ''

  profileApi = ProfileService.getInstance()
  authApi = AuthService.getInstance()

  // eslint-disable-next-line space-before-function-paren
  mounted() {
    if (!this.authApi.isLogin) {
      Vue.toasted.show('You have to be logged in to access this page.', {
        duration: 5000
      })
      this.$router.back()
    }

    this.reload()
  }

  // eslint-disable-next-line space-before-function-paren
  reload() {
    this.email = this.authApi.response.email
    console.log(this.email)
  }

  // eslint-disable-next-line space-before-function-paren
  saveEmail() {
    this.profileApi
      .saveEmail(this.email)
      .then((resp: any) => {
        // refresh the token since it is based on email
        this.authApi.storeToken(resp.data.jwtToken)
        Vue.toasted.show('Your email updated.', { duration: 5000 })
      })
      .catch((err: any) => console.log(err))
  }
  // eslint-disable-next-line space-before-function-paren
  savePassword() {
    this.profileApi
      .savePassword(
        this.currentPassword,
        this.newPassword,
        this.confirmedPassword
      )
      .then((resp: any) => {
        Vue.toasted.show('Your password updated.', { duration: 5000 })
      })
      .catch((err: any) => console.log(err))
  }
}
</script>

<style scoped lang="less"></style>
