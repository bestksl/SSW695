<template>
  <div class="general-info-form">
    <ValidationObserver v-slot="{ invalid }">
      <form @submit.prevent="save">
        <div class="p-grid">
          <div class="p-col-12 text-center">
            <div v-if="imgForceUpdate">
              <!-- when there is photoId -->
              <img
                v-if="!info.url && info.photoId"
                :src="API_URL + '/files/' + info.photoId"
                style="
                  width: 12rem;
                  height: 12rem;
                  border: solid 1px lightgray;
                "
              />
              <!-- else -->
              <!-- if no photo is selected yet -->
              <img
                v-if="!info.url && !info.photoId"
                src="@/assets/images/logo-200x200.png"
                style="
                  width: 12rem;
                  height: 12rem;
                  border: solid 1px lightgray;
                "
              />
              <!-- if selected but not uploaded yet -->
              <img
                v-if="info.url"
                :src="info.url"
                style="
                  width: 12rem;
                  height: 12rem;
                  border: solid 1px lightgray;
                "
              />
            </div>
            <div>
              <FileUpload
                mode="basic"
                accept="image/*"
                :maxFileSize="1000000"
                @select="onFileSelect"
                @clear="clearFileSelect"
              />
            </div>
          </div>
          <div class="p-col-3 text-right">
            First Name:
          </div>
          <div class="p-col-7">
            <ValidationProvider
              name="first name"
              v-slot="{ errors }"
              rules="required|min:4|max:128"
            >
              <InputText type="text" v-model="info.firstName" class="w-100" />
              <ul v-if="errors.length" class="v-error">
                <li v-for="error in errors" v-bind:key="error">{{ error }}</li>
              </ul>
            </ValidationProvider>
          </div>

          <div class="p-col-3 text-right">
            Nick Name:
          </div>
          <div class="p-col-7">
            <ValidationProvider
              name="nick name"
              v-slot="{ errors }"
              rules="required|min:4|max:128"
            >
              <InputText type="text" v-model="info.nickName" class="w-100" />
              <ul v-if="errors.length" class="v-error">
                <li v-for="error in errors" v-bind:key="error">{{ error }}</li>
              </ul>
            </ValidationProvider>
          </div>

          <div class="p-col-3 text-right">
            Last Name:
          </div>
          <div class="p-col-7">
            <ValidationProvider
              name="last name"
              v-slot="{ errors }"
              rules="required|min:4|max:128"
            >
              <InputText type="text" v-model="info.lastName" class="w-100" />
              <ul v-if="errors.length" class="v-error">
                <li v-for="error in errors" v-bind:key="error">{{ error }}</li>
              </ul>
            </ValidationProvider>
          </div>

          <div class="p-col-3 text-right">
            Date of Birth:
          </div>
          <div class="p-col-7 text-left">
            <ValidationProvider
              name="birth date"
              v-slot="{ errors }"
              rules="required"
            >
              <Calendar v-model="info.dateOfBirth" />
              <ul v-if="errors.length" class="v-error">
                <li v-for="error in errors" v-bind:key="error">{{ error }}</li>
              </ul>
            </ValidationProvider>
          </div>

          <div class="p-col-3 text-right">
            Gender:
          </div>
          <div class="p-col-7">
            <ValidationProvider
              name="gender"
              v-slot="{ errors }"
              rules="required"
            >
              <Dropdown
                v-model="info.gender"
                :options="genders"
                :filter="true"
                placeholder="--"
                optionLabel="title"
                optionValue="value"
                class="w-50"
              />
              <ul v-if="errors.length" class="v-error">
                <li v-for="error in errors" v-bind:key="error">{{ error }}</li>
              </ul>
            </ValidationProvider>
          </div>

          <div class="p-col-7 p-offset-3 text-right">
            <Button
              label="Save"
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
/* eslint-disable comma-dangle */

import { Component, Prop, Vue } from 'vue-property-decorator'
import { GeneralInformation } from './GeneralInformation'
import { ProfileService } from './ProfileService'
import { apiUrl } from '../Api'

@Component
export default class GeneralInformationForm extends Vue {
  API_URL = apiUrl

  @Prop() model!: GeneralInformation
  info = {} as any

  imgForceUpdate = Math.random()

  genders = [
    { title: 'Male', value: 'Male' },
    { title: 'Female', value: 'Female' },
    { title: 'Other', value: 'Other' }
  ]

  profileApi = ProfileService.getInstance()

  clearFileSelect($info: any) {
    this.info.url = null
    this.info.file = null
  }

  onFileSelect($info: any) {
    if ($info.files && $info.files[0]) {
      this.info.file = $info.files[0]
      const reader = new FileReader()
      reader.onload = (e: any) => {
        this.info.url = e.target.result
        this.imgForceUpdate = Math.random()
      }
      reader.readAsDataURL(this.info.file)
    }
  }

  mounted() {
    this.profileApi
      .loadGeneralInformation()
      .then((resp: any) => {
        resp.data.dateOfBirth = new Date(resp.data.dateOfBirth)
        this.info = resp.data as any
      })
      .catch((err: any) => console.log(err))
  }

  save() {
    const data = new FormData()
    data.append('username', this.info.username)
    data.append('firstName', this.info.firstName)
    data.append('lastName', this.info.lastName)
    data.append('nickName', this.info.nickName)
    data.append('dateOfBirth', this.info.dateOfBirth)
    data.append('gender', this.info.gender)
    data.append('file', this.info.file)

    this.profileApi
      .saveGeneralInformation(data)
      .then((resp: any) => {
        Vue.toasted.show('Changes have been saved.', { duration: 5000 })
      })
      .catch((err: any) => {
        Vue.toasted.show('Failed to save the changes.', { duration: 5000 })
        console.log(err)
      })
  }
}
</script>

<style scoped lang="less"></style>
