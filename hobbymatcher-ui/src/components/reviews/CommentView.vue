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
    </div>
  </div>
</template>

<script lang="ts">
import { Component, Prop, Vue } from 'vue-property-decorator'
import { Comment } from './Comment'
import { ReviewService } from './ReviewService'

@Component
export default class CommentView extends Vue {
  @Prop() model!: Comment

  comment: Comment = {} as any

  // eslint-disable-next-line space-before-function-paren
  mounted() {
    this.comment = this.model
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
</style>
