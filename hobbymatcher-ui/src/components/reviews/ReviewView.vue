<template>
  <div class="review p-2">
    <div class="d-flex">
      <div>
        <img
          src="@/assets/images/profile-photo.png"
          class="mr-1 user-photo rounded-circle"
        />
      </div>
      <div class="ml-2">
        <div>{{ review.byUserFirst }} {{ review.byUserLast }}</div>
        <div class="d-flex align-items-center pt-1">
          <i class="fas fa-comments"></i>
          <span class="ml-1">{{ (review.reviews || []).length }}</span>
          <Rating v-model="review.rate" class="ml-2 pt-1" :readonly="true" />
        </div>
        <div>
          <small>{{ review.content }}</small>
        </div>
        <!-- <div class="mt-2">
          <Button
            label="Helpful"
            icon="pi pi-thumbs-up"
            class="p-button-secondary py-0 helpful-btn"
          />
        </div> -->
      </div>
    </div>
    <div :class="['subreviews', 'review-level-' + indent]">
      <hr v-if="review.reviews" />
      <ReviewView
        v-for="subreview of review.reviews"
        :key="subreview.id"
        :model="subreview"
        :indent="indent + 1"
        class="mt-3"
      />
    </div>
  </div>
</template>

<script lang="ts">
import { Component, Prop, Vue } from 'vue-property-decorator'
import { Review } from './Review'
import { ReviewService } from './ReviewService'

@Component
export default class ReviewView extends Vue {
  @Prop() type!: string
  @Prop() oId!: number

  @Prop() indent!: number
  @Prop() model!: Review

  review: Review = {} as any

  // eslint-disable-next-line space-before-function-paren
  mounted() {
    this.review = this.model
  }
}
</script>

<style scoped lang="less">
.review {
  background-color: #ecf0f1;
}
.user-photo {
  width: 48px;
  height: 48px;
  border: solid 1px lightgray;
}
.helpful-btn {
  font-size: 0.75rem;
}
.review-level-1 {
  padding-left: 1.5rem;
}
.review-level-2 {
  padding-left: 3rem;
}
</style>
