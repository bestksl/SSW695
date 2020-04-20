<template>
  <div class="comment-form">
    <ValidationObserver v-slot="{ invalid }">
      <form @submit.prevent="save" class="d-flex align-items-center">
        <ValidationProvider
          name="comment content"
          v-slot="{ errors }"
          rules="max:256"
          class="flex-grow-1 mr-2"
        >
          <Textarea
            id="event-description"
            v-model="comment.content"
            rows="1"
            class="w-100 mt-1"
            style="font-size: 0.75rem;"
            placeholder="..."
          />
          <ul v-if="errors.length" class="v-error">
            <li v-for="error in errors" v-bind:key="error">{{ error }}</li>
          </ul>
        </ValidationProvider>
        <Button
          label="Post"
          class="p-button-primary"
          style="font-size: 0.75rem;"
          :disabled="invalid || hiddenRules"
        />
      </form>
    </ValidationObserver>
  </div>
</template>

<script lang="ts">
/* eslint-disable space-before-function-paren */

import { Component, Prop, Vue, Model } from 'vue-property-decorator'
import { ReviewService } from './ReviewService'
import { Review } from './Review'
import { Comment } from './Comment'

@Component
export default class CommentForm extends Vue {
  @Prop() type!: string
  @Prop() oId!: number
  @Prop() pId!: number

  @Model() model!: Comment
  comment: Comment = {} as any

  commentApi = ReviewService.getInstance()

  get hiddenRules() {
    return !this.comment.content
  }

  mounted() {
    if (this.model) {
      this.comment = this.model
    }
  }

  save() {
    this.comment.ownerType = this.type
    this.comment.ownerId = this.oId
    this.comment.parentId = this.pId

    this.commentApi
      .postORput(this.comment)
      .then((resp: any) => {
        this.comment = {} as any
        Vue.toasted.show('Posted', { duration: 5000 })
        this.$emit('doReload', null)
      })
      .catch((err: any) => console.log(err))
  }
}
</script>

<style scoped lang="less">
.comment-form {
  background-color: #ecf0f1;
}
</style>
