<template>
  <router-link :to="'/hobbies/view?id=' + hobby.id">
    <div class="hobby-thumb">
      <img
        v-if="!hobby.photoId"
        src="@/assets/images/logo-200x200.png"
        class="cover"
      />
      <img
        v-if="hobby.photoId"
        :src="API_URL + '/files/' + hobby.photoId"
        class="cover"
      />
      <span class="hobby-title">{{ hobby.name }}</span>
      <router-link
        v-if="userId == hobby.createdById"
        :to="'/hobbies/edit?id=' + hobby.id"
        class="edit-btn"
      >
        <Button type="button" icon="pi pi-pencil" class="p-button-secondary" />
      </router-link>
    </div>
  </router-link>
</template>

<script lang="ts">
/* eslint-disable space-before-function-paren */

import { Component, Prop, Vue, Model } from 'vue-property-decorator'
import { Hobby } from './Hobby'
import { apiUrl } from '../Api'

@Component
export default class HobbyThumb extends Vue {
  API_URL = apiUrl

  @Model() model!: Hobby
  @Model() userId!: number

  get hobby() {
    return this.model || {}
  }
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
  background-color: rgba(211, 211, 211, 0.75);
}
.edit-btn {
  position: absolute;
  right: 0.5rem;
  top: 0.5rem;
}
</style>
