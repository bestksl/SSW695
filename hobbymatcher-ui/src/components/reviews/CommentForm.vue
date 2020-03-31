<template>
  <div class="comment-form ml-5">
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
import { Component, Prop, Vue } from 'vue-property-decorator'
import { ReviewService } from './ReviewService'
import { Review } from './Review'

@Component
export default class CommentForm extends Vue {
  @Prop() type!: string
  @Prop() oId!: number
  @Prop() pId!: number

  // eslint-disable-next-line space-before-function-paren
  get hiddenRules() {
    return !this.comment.content
  }

  comment: Review = {} as Review

  commentApi = ReviewService.getInstance()

  // eslint-disable-next-line space-before-function-paren
  save() {
    this.comment.ownerType = this.type
    this.comment.ownerId = this.oId
    this.comment.parentId = this.pId

    this.commentApi
      .post(this.comment)
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
