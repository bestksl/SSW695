<template>
  <div class="hobby-thumbs">
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
import { HobbyService } from './HobbyService'
import { Hobby } from './Hobby'

@Component
export default class HobbyThumbs extends Vue {
  @Model() model!: Hobby[]

  // eslint-disable-next-line space-before-function-paren
  get hobbies() {
    return this.model || []
  }
}
</script>

<style scoped lang="less"></style>
