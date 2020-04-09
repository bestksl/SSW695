<template>
  <div class="hobby-thumbs">
    <div class="d-flex mb-4">
      <SelectButton
        v-model="selected"
        :options="[
          { title: 'Created Hobbies', value: 'created' },
          { title: 'Followed Hobbies', value: 'followed' }
        ]"
        optionLabel="title"
        optionValue="value"
        @input="reload()"
      />

      <span class="flex-grow-1"></span>

      <router-link to="/hobbies/create">
        <Button
          type="button"
          label="Create a Hobby"
          icon="pi pi-plus"
          class="p-button-secondary"
        />
      </router-link>
    </div>
    <div v-if="!hobbies || hobbies.length == 0" class="text-center">
      No Hobbies
    </div>
    <HobbyThumb
      v-for="hobby of hobbies"
      :key="hobby.id"
      :model="hobby"
      :userId="userId"
      class="d-inline-block mr-4 mb-4"
    />
  </div>
</template>

<script lang="ts">
/* eslint-disable space-before-function-paren */
import { Component, Prop, Vue, Model } from 'vue-property-decorator'
import { ProfileService } from './ProfileService'
import { Hobby } from '../hobbies/Hobby'
import { AuthService } from '../auth/AuthService'

@Component
export default class UserHobbies extends Vue {
  selected: any = 'created'
  hobbies: Hobby[] = []

  profileApi = ProfileService.getInstance()
  authApi = AuthService.getInstance()

  get userId() {
    return this.authApi!.response.userId
  }

  mounted() {
    this.reload()
  }

  reload() {
    this.profileApi
      .loadHobbies(this.selected)
      .then((resp: any) => (this.hobbies = resp.data.list))
      .catch((err: any) => console.log(err))
  }
}
</script>

<style scoped lang="less"></style>
