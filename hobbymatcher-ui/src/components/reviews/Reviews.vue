<template>
  <div class="review-form">
    <div class="p-grid">
      <div class="p-col-10 p-offset-1">
        <strong>Reviews:</strong>
      </div>
      <div v-if="authApi.isLogin" class="p-col-10 p-offset-1">
        <ReviewForm :type="type" :oId="oId" v-on:doReload="reload()" />
      </div>
      <div class="p-col-10 p-offset-1">
        <ReviewView
          :key="randIdPerfix + '-' + review.id"
          v-for="review of reviews"
          :model="review"
          :type="type"
          :oId="oId"
          v-on:doReload="reload()"
        />
      </div>
    </div>
  </div>
</template>

<script lang="ts">
import { Component, Prop, Vue } from 'vue-property-decorator'
import { Review } from './Review'
import { AuthService } from '../auth/AuthService'
import { ReviewService } from './ReviewService'

@Component
export default class Reviews extends Vue {
  @Prop() type!: string
  @Prop() oId!: number

  @Prop() model!: Review[]

  reviews: Review[] = []
  randIdPerfix = 0

  authApi = AuthService.getInstance()
  reviewApi = ReviewService.getInstance()

  // eslint-disable-next-line space-before-function-paren
  mounted() {
    this.reload()
  }

  // eslint-disable-next-line space-before-function-paren
  reload() {
    this.reviewApi
      .load(this.type, this.oId)
      .then((resp: any) => {
        this.reviews = resp.data.list
        this.randIdPerfix = Math.random()
      })
      .catch((err: any) => console.log(err))
  }
}
</script>

<style scoped lang="less"></style>
