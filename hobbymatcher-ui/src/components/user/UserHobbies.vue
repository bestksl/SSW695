<template>
  <div class="hobby-thumbs">
    <div class="mb-4">
      <SelectButton
        v-model="selected"
        :options="[
          { title: 'Created Hobbies', value: 'created' },
          { title: 'Subscribed Hobbies', value: 'subscribed' }
        ]"
        optionLabel="title"
        optionValue="value"
        @input="reload()"
      />
    </div>
    <div v-if="!hobbies || hobbies.length == 0" class="text-center">
      No Hobbies
    </div>
    <HobbyThumb
      v-for="hobby of hobbies"
      :key="hobby.id"
      :model="hobby"
      class="d-inline-block mr-4 mb-4"
    />
  </div>
</template>

<script lang="ts">
import { Component, Prop, Vue, Model } from 'vue-property-decorator'
import { ProfileService } from './ProfileService'
import { Hobby } from '../hobbies/Hobby'

@Component
export default class UserHobbies extends Vue {
  selected: any = 'created'
  hobbies: Hobby[] = []

  profileApi = ProfileService.getInstance()

  // eslint-disable-next-line space-before-function-paren
  mounted() {
    this.reload()
  }

  // eslint-disable-next-line space-before-function-paren
  reload() {
    this.profileApi
      .loadHobbies(this.selected)
      .then((resp: any) => (this.hobbies = resp.data.list))
      .catch((err: any) => console.log(err))
  }
}
</script>

<style scoped lang="less"></style>
