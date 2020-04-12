<template>
  <div class="hobby-form">
    <div class="p-grid">
      <div class="p-offset-1 p-col-3 d-flex flex-column text-center">
        <div>
          <img
            v-if="hobby.photoId"
            :src="'http://localhost:8080/hobbymatcher/files/' + hobby.photoId"
            style="width: 12rem; height: 12rem; border: solid 1px lightgray;"
          />
        </div>
        <div>
          <Button
            v-if="status === ''"
            label="Follow"
            icon="pi pi-plus"
            class="p-button-primary"
            v-on:click="manageFollowship('follow')"
          />
          <Button
            v-if="status === 'followed'"
            label="Unfollow"
            icon="pi pi-times"
            class="p-button-warning"
            v-on:click="manageFollowship('cancel')"
          />
        </div>
      </div>
      <div class="p-col-7">
        <h3>Welcome to {{ hobby.name }} community</h3>
        <p>{{ hobby.description || '' }}</p>
        <small v-if="hobby.plus18Only">+18 only</small>
      </div>
    </div>
  </div>
</template>

<script lang="ts">
/* eslint-disable space-before-function-paren */

import { Component, Prop, Vue, Model } from 'vue-property-decorator'
import { HobbyService } from './HobbyService'
import { Hobby } from './Hobby'
import { AuthService } from '../auth/AuthService'

@Component
export default class HobbyView extends Vue {
  @Model() model!: Hobby

  status: string = '' // '', 'followed'
  get hobby() {
    return this.model || {}
  }

  authApi = AuthService.getInstance()
  hobbyApi = HobbyService.getInstance()

  mounted() {
    this.authApi.ifLogin(() => this.doCheckFollowship())
  }

  doCheckFollowship() {
    this.hobbyApi
      .getFollowship(this.hobby.id)
      .then((resp: any) => (this.status = resp.data.status))
      .catch((err: any) => console.log(err))
  }

  manageFollowship(action: string) {
    this.hobbyApi
      .manageFollowship(this.hobby.id, action)
      .then((resp: any) => (this.status = resp.data.status))
      .catch((err: any) => console.log(err))
  }
}
</script>

<style lang="less">
.hobby-form .p-dropdown-filter-container {
  width: 100%;
}
</style>
