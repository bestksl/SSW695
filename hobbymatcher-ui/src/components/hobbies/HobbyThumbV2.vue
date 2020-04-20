<template>
  <div  >
    <input v-model="content"/>
    What you type： {{ content }}
  </div>
</template>

<script lang="ts">
/* eslint-disable space-before-function-paren */

import { Component, Prop, Vue } from 'vue-property-decorator'
import { Hobby } from './Hobby'

@Component
export default class HobbyThumbV2 extends Vue {
  @Prop() model!: Hobby
  content: string = ' sdlkfjsld'
}
</script>

<style scoped lang="less">

</style>
