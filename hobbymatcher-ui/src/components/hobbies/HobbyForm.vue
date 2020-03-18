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
            Description
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
                v-model="hobby.category"
                :options="categories"
                :filter="true"
                placeholder="Check your hobby's category whether exists ..."
                optionLabel="label"
                optionValue="value"
                class="w-50"
              />
              <ul v-if="errors.length" class="v-error">
                <li v-for="error in errors" v-bind:key="error">{{ error }}</li>
              </ul>
            </ValidationProvider>
            <div class="mt-2">
              <small>
                (if you can find your hobby here, you can not create it)
              </small>
            </div>
          </div>
          <div class="p-offset-1 p-col-2"></div>
          <div class="p-col-7">
            <Listbox :options="categories" optionLabel="label" class="w-50" />
          </div>

          <div class="p-offset-1 p-col-2 text-right">
            Required over 18 years old
          </div>
          <div class="p-col-7">
            <ValidationProvider
              name="age restriction"
              v-slot="{ errors }"
              rules="required"
            >
              <Checkbox v-model="hobby.plus18" :binary="true" />
              <ul v-if="errors.length" class="v-error">
                <li v-for="error in errors" v-bind:key="error">{{ error }}</li>
              </ul>
            </ValidationProvider>
          </div>

          <div class="p-offset-1 p-col-2 text-right">
            Hobby Picture:
          </div>
          <div class="p-col-7 d-flex flex-column">
            <!-- TODO add photo upload and its validation -->
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
import { HobbyService } from './HobbyService'
import { Hobby } from './Hobby'

@Component
export default class HobbyForm extends Vue {
  @Model() model!: Hobby
  internal: Hobby = {} as any

  // eslint-disable-next-line space-before-function-paren
  get hobby(): any {
    return this.model || this.internal
  }
  // hobby.file: hobby picture to upload
  // hobby.url: hobby picture preview

  categories = [
    { label: 'Sport', value: 1 },
    { label: 'Entertainment', value: 2 },
    { label: 'Art', value: 3 }
  ]

  api = new HobbyService()

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
    data.append('category', this.hobby.category)
    data.append('plus18', this.hobby.plus18.toString())
    data.append('file', this.hobby.file)

    this.api
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
