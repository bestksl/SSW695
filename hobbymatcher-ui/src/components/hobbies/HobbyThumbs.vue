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
import { Hobby } from './Hobby'

@Component
export default class HobbyThumbs extends Vue {
  @Model() model!: Hobby
  hobbies: Hobby[] = [
    {
      name: 'Jogging',
      description: 'This is a fun hobby.',
      plus18: false
    } as Hobby
  ]

  // eslint-disable-next-line space-before-function-paren
  constructor() {
    super()

    for (let i = 0; i < 11; i++) {
      this.hobbies.push(this.hobbies[0])
    }
  }
}
</script>

<style scoped lang="less"></style>
