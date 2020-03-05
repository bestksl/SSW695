<template>
  <div class="review-form">
    <div class="p-grid">
      <div class="p-col-10 p-offset-1">
        <strong>Reviews:</strong>
      </div>
      <div class="p-col-10 p-offset-1">
        <ReviewForm />
      </div>
      <div class="p-col-10 p-offset-1">
        <ReviewView />
      </div>
    </div>
  </div>
</template>

<script lang="ts">
import { Component, Prop, Vue } from 'vue-property-decorator'
import { Review } from './Review'

@Component
export default class Reviews extends Vue {
  @Prop() model!: Review[]
  reviews: Review[] = []
}
</script>

<style scoped lang="less"></style>
