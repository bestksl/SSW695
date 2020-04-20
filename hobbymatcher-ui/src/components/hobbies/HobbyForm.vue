<template>
  <div class="hobby-form">
    <ValidationObserver v-slot="{ invalid }">
      <form @submit.prevent="save">
        <div class="p-grid">
          <div class="p-offset-1 p-col-2 text-right">
            Hobby Name:
          </div>
          <div class="p-col-7">
            <ValidationProvider
              name="name"
              v-slot="{ errors }"
              rules="required|min:4|max:128"
            >
              <InputText type="text" v-model="hobby.name" class="w-100" />
              <ul v-if="errors.length" class="v-error">
                <li v-for="error in errors" v-bind:key="error">{{ error }}</li>
              </ul>
            </ValidationProvider>
          </div>
          <div class="p-offset-1 p-col-2 text-right">
            Description:
          </div>
          <div class="p-col-7">
            <ValidationProvider
              name="description"
              v-slot="{ errors }"
              rules="max:512"
            >
              <Textarea
                id="event-description"
                v-model="hobby.description"
                rows="3"
                optionLabel="label"
                placeholder="..."
                class="w-100"
              />
              <ul v-if="errors.length" class="v-error">
                <li v-for="error in errors" v-bind:key="error">{{ error }}</li>
              </ul>
            </ValidationProvider>
          </div>

          <div class="p-offset-1 p-col-2 text-right">
            Category:
          </div>
          <div class="p-col-7">
            <ValidationProvider
              name="category"
              v-slot="{ errors }"
              rules="required"
            >
              <Dropdown
                v-model="hobby.categoryId"
                :options="categories"
                :filter="true"
                optionLabel="name"
                optionValue="id"
                class="w-50"
              />
              <ul v-if="errors.length" class="v-error">
                <li v-for="error in errors" v-bind:key="error">{{ error }}</li>
              </ul>
            </ValidationProvider>
          </div>

          <div class="p-offset-1 p-col-2 text-right">
            +18 Only:
          </div>
          <div class="p-col-7">
            <ValidationProvider
              name="age restriction"
              v-slot="{ errors }"
              rules="required"
            >
              <Checkbox v-model="hobby.plus18Only" :binary="true" />
              <ul v-if="errors.length" class="v-error">
                <li v-for="error in errors" v-bind:key="error">{{ error }}</li>
              </ul>
            </ValidationProvider>
          </div>

          <div class="p-offset-1 p-col-2 text-right">
            Hobby Picture:
          </div>
          <div class="p-col-7 d-flex flex-column">
            <div v-if="imgForceUpdate">
              <!-- when there is photoId -->
              <img
                v-if="!hobby.url && hobby.photoId"
                :src="
                  apiUrl + '/files/' + hobby.photoId
                "
                style="
                  width: 12rem;
                  height: 12rem;
                  border: solid 1px lightgray;
                "
              />
              <!-- else -->
              <!-- if no photo is selected yet -->
              <img
                v-if="!hobby.url && !hobby.photoId"
                src="@/assets/images/logo-200x200.png"
                style="
                  width: 12rem;
                  height: 12rem;
                  border: solid 1px lightgray;
                "
              />
              <!-- if selected but not uploaded yet -->
              <img
                v-if="hobby.url"
                :src="hobby.url"
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

          <div class="p-offset-1 p-col-2"></div>
          <div class="p-col-7">
            <Button
              type="button"
              label="Back"
              icon="pi pi-chevron-left"
              class="p-button-secondary"
              v-on:click="back()"
            />
            <Button
              v-if="hobby.id"
              label="Update"
              icon="pi pi-check"
              class="p-button-success"
              :disabled="invalid"
            />
            <Button
              v-if="!hobby.id"
              label="Create"
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

import { Component, Prop, Vue, Model } from 'vue-property-decorator'
import { CategoryService } from '../category/CategoryService'
import { Category } from '../category/Category'
import { HobbyService } from './HobbyService'
import { Hobby } from './Hobby'

@Component
export default class HobbyForm extends Vue {
  @Model() id!: number

  hobby: any /* Hobby */ = {
    plus18Only: false,
    description: '',
  } as any
  // hobby.file: hobby picture to upload
  // hobby.url: hobby picture preview
  imgForceUpdate = Math.random()

  categories: Category[] = []

  categoryApi = new CategoryService()
  hobbyApi = new HobbyService()

  mounted() {
    this.categoryApi
      .list()
      .then((resp: any) => (this.categories = resp.data.list))
      .catch((err: any) => console.log(err))

    if (this.id) {
      this.doLoad()
    }
  }

  doLoad() {
    this.hobbyApi
      .get(this.id)
      .then((resp: any) => (this.hobby = resp.data.hobby))
      .catch((err: any) => console.log(err))
  }

  clearFileSelect($event: any) {
    this.hobby.url = null
    this.hobby.file = null
  }

  onFileSelect($event: any) {
    if ($event.files && $event.files[0]) {
      this.hobby.file = $event.files[0]
      const reader = new FileReader()
      reader.onload = (e: any) => {
        this.hobby.url = e.target.result
        this.imgForceUpdate = Math.random()
      }
      reader.readAsDataURL(this.hobby.file)
    }
  }

  back() {
    window.history.back()
  }

  save() {
    if (this.hobby.id) {
      this.doUpdate()
    } else {
      this.doSave()
    }
  }

  getFormData() {
    const data = new FormData()
    if (this.hobby.id) {
      data.append('id', this.hobby.id) // only in update
    }
    data.append('name', this.hobby.name)
    data.append('description', this.hobby.description || '')
    data.append('categoryId', this.hobby.categoryId)
    data.append('plus18Only', this.hobby.plus18Only)
    data.append('file', this.hobby.file)
    return data
  }

  doSave() {
    this.hobbyApi
      .add(this.getFormData())
      .then((resp: any) => {
        Vue.toasted.show('Hobby has been added.', { duration: 5000 })
        this.back()
      })
      .catch((err: any) => {
        Vue.toasted.show('Failed to add the hobby.', { duration: 5000 })
        console.log(err)
      })
  }

  doUpdate() {
    this.hobbyApi
      .update(this.getFormData())
      .then((resp: any) => {
        Vue.toasted.show('Hobby has been updated.', { duration: 5000 })
        this.back()
      })
      .catch((err: any) => {
        Vue.toasted.show('Failed to update the hobby.', { duration: 5000 })
        console.log(err)
      })
  }
}
</script>

<style lang="less">
.hobby-form .p-dropdown-filter-container {
  width: 100%;
}
</style>
