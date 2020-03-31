<template>
  <div class="comment py-2">
    <div class="d-flex">
      <div>
        <!-- when there is photoId -->
        <img
          v-if="comment.byUserPhotoId"
          :src="
            'http://localhost:8080/hobbymatcher/files/' + comment.byUserPhotoId
          "
          class="mr-1 user-photo rounded-circle"
        />
        <!-- else -->
        <img
          v-if="!comment.byUserPhotoId"
          src="@/assets/images/logo-200x200.png"
          class="mr-1 user-photo rounded-circle"
        />
      </div>
      <div class="ml-2">
        <div>{{ comment.byUserFirst }} {{ comment.byUserLast }}</div>
        <div>
          <small>{{ comment.content }}</small>
        </div>
      </div>
      <span class="flex-grow-1"></span>
      <div v-if="managable" class="manage-comment">
        <Button
          type="button"
          icon="pi pi-pencil"
          class="p-button-primary"
          v-on:click="doEdit()"
        />
        <Button
          type="button"
          icon="pi pi-times"
          class="p-button-danger"
          v-on:click="doDelete()"
        />
      </div>
    </div>
  </div>
</template>

<script lang="ts">
/* eslint-disable space-before-function-paren */

import { Component, Prop, Vue } from 'vue-property-decorator'
import { Comment } from './Comment'
import { ReviewService } from './ReviewService'
import { AuthService } from '../auth/AuthService'

@Component
export default class CommentView extends Vue {
  @Prop() model!: Comment

  comment: Comment = {} as any

  authApi = AuthService.getInstance()
  reviewApi = ReviewService.getInstance()

  get managable() {
    return (
      this.authApi.isLogin &&
      this.authApi.response.userId === this.comment.byUserId
    )
  }

  mounted() {
    this.comment = this.model
  }

  doReload() {
    this.$emit('doReload', null)
  }

  doDelete() {
    if (confirm('Are you sure?')) {
      this.reviewApi
        .delete(this.comment.id)
        .then((resp: any) => this.doReload())
        .catch((err: any) => console.log(err))
    }
  }
}
</script>

<style scoped lang="less">
.comment {
  background-color: #ecf0f1;
}
.user-photo {
  width: 32px;
  height: 32px;
  border: solid 1px lightgray;
}
.manage-comment button {
  height: 19px;
  width: 19px;
}
</style>
