<template>
  <div class="hobby-page">
    <div class="p-grid">
      <div class="p-col-10 p-offset-1 text-left">
        <Button
          label="Back"
          icon="pi pi-chevron-left"
          class="p-button-secondary"
          v-on:click="back()"
        />
      </div>
      <div class="p-col-10 p-offset-1">
        <hr />
      </div>
    </div>

    <HobbyView v-model="hobby" />

    <RecentEvents class="mt-4" />

    <BlogsListing class="mt-4" />
  </div>
</template>

<script lang="ts">
import { Component, Prop, Vue, Model } from 'vue-property-decorator'
import { HobbyService } from '../components/hobbies/HobbyService'
import { Hobby } from '../components/hobbies/Hobby'

@Component
export default class ViewHobby extends Vue {
  api = new HobbyService()

  hobby: Hobby = {} as any

  // eslint-disable-next-line space-before-function-paren
  back() {
    window.history.back()
  }

  // eslint-disable-next-line space-before-function-paren
  mounted() {
    this.api
      .get(this.$route.query.id)
      .then((resp: any) => (this.hobby = resp.data.hobby))
      .catch((err: any) => console.log(err))
  }
}
</script>

<style lang="less"></style>
