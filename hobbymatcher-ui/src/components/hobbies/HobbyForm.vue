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
                src="@/assets/images/logo-200x200.png"
                style="width: 12rem; height: 12rem; border: solid 1px lightgray;"
              />
            </div>
            <div>
              <Button label="Upload" class="p-button-secondary" />
            </div>
          </div>

          <div class="p-offset-1 p-col-2"></div>
          <div class="p-col-7">
            <Button
              label="Back"
              icon="pi pi-chevron-left"
              class="p-button-secondary"
              v-on:click="window.history.back()"
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
import { Hobby } from './Hobby'

@Component
export default class HobbyForm extends Vue {
  @Model() model!: Hobby
  hobby: Hobby = {} as Hobby
  categories = [
    { label: 'Sport', value: 1 },
    { label: 'Entertainment', value: 2 },
    { label: 'Art', value: 3 }
  ]
}
</script>

<style lang="less">
.hobby-form .p-dropdown-filter-container {
  width: 100%;
}
</style>
