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
        <div>{{ review.userName }}</div>
        <div class="d-flex align-items-center pt-1">
          <i class="fas fa-comments"></i>
          <span class="ml-1">{{ review.reviewsCount }}</span>
          <Rating v-model="review.rating" class="ml-2 pt-1" />
        </div>
        <div>
          <small>{{ review.reviewText }}</small>
        </div>
        <div class="mt-2">
          <Button
            label="Helpful"
            icon="pi pi-thumbs-up"
            class="p-button-secondary py-0 helpful-btn"
          />
        </div>
      </div>
    </div>
    <div :class="['subreviews', 'review-level-' + myIndent]">
      <hr v-if="review.reviews.length" />
      <ReviewView
        v-for="sub of review.reviews"
        :key="sub.id"
        :model="sub"
        :indent="indent + 1"
        class="mt-3"
      />
    </div>
  </div>
</template>

<script lang="ts">
import { Component, Prop, Vue } from 'vue-property-decorator'
import { Review } from './Review'

@Component
export default class ReviewView extends Vue {
  @Prop() indent!: number
  @Prop() model!: Review

  myIndent = 1
  review: Review = {
    id: 1,
    userPhoto: '@/assets/images/profile-photo.png',
    userName: 'Mohammad Hassany',
    datetime: new Date('2020-01-10'),
    rating: 3,
    reviewText: 'A nice review from someone who support this.',
    positiveVoteCount: 20,
    reviewsCount: 1,
    reviews: [
      {
        id: 1,
        userPhoto: '@/assets/images/profile-photo.png',
        userName: 'Mohammad Hassany',
        datetime: new Date('2020-01-10'),
        rating: 3,
        reviewText: 'A nice review from someone who support this.',
        positiveVoteCount: 20,
        reviewsCount: 1,
        reviews: []
      }
    ]
  } as Review

  // eslint-disable-next-line space-before-function-paren
  constructor() {
    super()
    if (this.model) {
      this.review = this.model
    }
    if (this.indent) {
      this.myIndent = this.indent
    }
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
