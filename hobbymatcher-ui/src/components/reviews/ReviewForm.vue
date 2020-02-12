<template>
  <div class="review-form">
    <div class="p-grid">
      <div class="p-col-12">
        <Textarea
          id="event-description"
          v-model="review.reviewText"
          rows="2"
          class="w-100"
          placeholder="..."
        />
      </div>
      <div class="p-col-12 d-flex align-items-center">
        <span class="pb-2">Rating: </span>
        <Rating v-model="review.rating" class="ml-2" />
        <span style="flex-grow: 1;"></span>
        <Button
          label="Write Review"
          icon="pi pi-check"
          class="p-button-primary"
        />
      </div>
    </div>
  </div>
</template>

<script lang="ts">
import { Component, Prop, Vue } from 'vue-property-decorator'
import { Review } from './review'

@Component
export default class ReviewForm extends Vue {
  @Prop() model!: Review
  review: Review = {} as Review
}
</script>

<style scoped lang="less">
.review-form {
  background-color: #ecf0f1;
}
</style>
