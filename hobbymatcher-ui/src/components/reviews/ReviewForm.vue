<template>
  <div class="review-form">
    <ValidationObserver v-slot="{ invalid }">
      <form @submit.prevent="save">
        <div class="p-grid">
          <div class="p-col-12">
            <ValidationProvider
              name="review content"
              v-slot="{ errors }"
              rules="max:256"
            >
              <Textarea
                id="event-description"
                v-model="review.content"
                rows="2"
                class="w-100"
                placeholder="..."
              />
              <ul v-if="errors.length" class="v-error">
                <li v-for="error in errors" v-bind:key="error">{{ error }}</li>
              </ul>
            </ValidationProvider>
          </div>
          <div class="p-col-12 d-flex align-items-center">
            <span class="pb-2">Rating: </span>
            <div>
              <ValidationProvider
                name="rating"
                v-slot="{ errors }"
                rules="numeric|min_value:1|max_value:5"
              >
                <Rating v-model="review.rate" class="ml-2" />
                <ul v-if="errors.length" class="v-error">
                  <li v-for="error in errors" v-bind:key="error">
                    {{ error }}
                  </li>
                </ul>
              </ValidationProvider>
            </div>

            <span style="flex-grow: 1;"></span>
            <Button
              label="Write Review"
              icon="pi pi-check"
              class="p-button-primary"
              :disabled="invalid || hiddenRules"
            />
          </div>
        </div>
      </form>
    </ValidationObserver>
  </div>
</template>

<script lang="ts">
/* eslint-disable space-before-function-paren */

import { Component, Prop, Vue, Model } from 'vue-property-decorator'
import { ReviewService } from './ReviewService'
import { Review } from './Review'

@Component
export default class ReviewForm extends Vue {
  @Prop() type!: string
  @Prop() oId!: number

  @Model() model!: Review
  review: Review = {} as any

  reviewApi = ReviewService.getInstance()

  get hiddenRules() {
    return !this.review.content
  }

  mounted() {
    if (this.model) {
      this.review = this.model
    }
  }

  save() {
    this.review.ownerType = this.type
    this.review.ownerId = this.oId

    this.reviewApi
      .postORput(this.review)
      .then((resp: any) => {
        this.review = {} as any
        Vue.toasted.show('Posted', { duration: 5000 })
        this.$emit('doReload', null)
      })
      .catch((err: any) => console.log(err))
  }
}
</script>

<style scoped lang="less">
.review-form {
  background-color: #ecf0f1;
}
</style>
