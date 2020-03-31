<template>
  <div class="review p-2">
    <div class="d-flex">
      <div>
        <!-- when there is photoId -->
        <img
          v-if="review.byUserPhotoId"
          :src="
            'http://localhost:8080/hobbymatcher/files/' + review.byUserPhotoId
          "
          class="mr-1 user-photo rounded-circle"
        />
        <!-- else -->
        <img
          v-if="!review.byUserPhotoId"
          src="@/assets/images/logo-200x200.png"
          class="mr-1 user-photo rounded-circle"
        />
      </div>
      <div class="ml-2">
        <div>{{ review.byUserFirst }} {{ review.byUserLast }}</div>
        <div class="d-flex align-items-center pt-1">
          <i class="fas fa-comments"></i>
          <span class="ml-1">{{ (review.comments || []).length }}</span>
          <Rating v-model="review.rate" class="ml-2 pt-1" :readonly="true" />
        </div>
        <div>
          <small>{{ review.content }}</small>
        </div>
      </div>
    </div>
    <div class="ml-5">
      <hr v-if="review.comments" />
      <CommentView
        v-for="comment of review.comments"
        v-bind:key="comment.id"
        :model="comment"
      />
    </div>
    <CommentForm
      :type="type"
      :oId="oId"
      :pId="review.id"
      v-on:saved="saved()"
    />
  </div>
</template>

<script lang="ts">
import { Component, Prop, Vue } from 'vue-property-decorator'
import { ReviewService } from './ReviewService'
import { Review } from './Review'

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

  // eslint-disable-next-line space-before-function-paren
  saved() {
    this.$emit('saved', null)
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
</style>
