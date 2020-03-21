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
                placeholder="Check your hobby's category whether exists ..."
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
            <div>
              <img
                v-if="!hobby.url"
                src="@/assets/images/logo-200x200.png"
                style="width: 12rem; height: 12rem; border: solid 1px lightgray;"
              />
              <img
                v-if="hobby.url"
                :src="hobby.url"
                style="width: 12rem; height: 12rem; border: solid 1px lightgray;"
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
import { Component, Prop, Vue, Model } from 'vue-property-decorator'
import { CategoryService } from '../category/CategoryService'
import { Category } from '../category/Category'
import { HobbyService } from './HobbyService'
import { Hobby } from './Hobby'

@Component
export default class HobbyForm extends Vue {
  @Model() model!: Hobby
  internal: Hobby = {} as any

  categories: Category[] = []

  // eslint-disable-next-line space-before-function-paren
  get hobby(): any {
    return this.model || this.internal
  }
  // hobby.file: hobby picture to upload
  // hobby.url: hobby picture preview

  categoryApi = new CategoryService()
  hobbyApi = new HobbyService()

  // eslint-disable-next-line space-before-function-paren
  mounted() {
    this.categoryApi
      .list()
      .then((resp: any) => (this.categories = resp.data.list))
      .catch((err: any) => console.log(err))
  }

  // eslint-disable-next-line space-before-function-paren
  clearFileSelect($event: any) {
    this.hobby.url = null
    this.hobby.file = null
  }

  // eslint-disable-next-line space-before-function-paren
  onFileSelect($event: any) {
    if ($event.files && $event.files[0]) {
      this.hobby.file = $event.files[0]
      const reader = new FileReader()
      reader.onload = (e: any) => (this.hobby.url = e.target.result)
      reader.readAsDataURL(this.hobby.file)
    }
  }

  // eslint-disable-next-line space-before-function-paren
  back() {
    window.history.back()
  }

  // eslint-disable-next-line space-before-function-paren
  save() {
    const data = new FormData()
    data.append('name', this.hobby.name)
    data.append('description', this.hobby.description)
    data.append('categoryId', this.hobby.categoryId)
    data.append('plus18Only', this.hobby.plus18Only)
    data.append('file', this.hobby.file)

    this.hobbyApi
      .add(data)
      .then((resp: any) => {
        Vue.toasted.show('Hobby has been added.', { duration: 5000 })
        this.back()
      })
      .catch((err: any) => {
        Vue.toasted.show('Failed to add the hobby.', { duration: 5000 })
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
