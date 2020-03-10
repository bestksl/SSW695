<template>
  <div class="hobby-thumb">
    <img src="@/assets/images/logo-200x200.png" class="cover" />
    <span class="hobby-title">{{ hobby.name }}</span>
  </div>
</template>

<script lang="ts">
import { Component, Prop, Vue } from 'vue-property-decorator'
import { Hobby } from './Hobby'

@Component
export default class HobbyThumb extends Vue {
  @Prop() model!: Hobby
  hobby: Hobby = {
    name: 'Jogging',
    description: 'This is a fun hobby.',
    plus18: false
  } as Hobby
}
</script>

<style scoped lang="less">
.hobby-thumb {
  position: relative;
  width: 220px;
  height: 160px;
  overflow: hidden;
  border: solid 1px lightgray;
  cursor: pointer;
}
.cover {
  width: 100%;
  height: 100%;
  opacity: 0.75;
}
.hobby-title {
  position: absolute;
  top: 30%;
  left: 0;
  right: 0;
  text-align: center;
  font-size: 1.25rem;
  max-height: 100px;
  overflow: hidden;
}
</style>
