<template>
  <div class="review-form">
     
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
