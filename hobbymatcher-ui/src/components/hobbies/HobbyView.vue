<template>
  <div class="hobby-form">
    <div class="p-grid">
      <div class="p-offset-1 p-col-2 d-flex flex-column text-center">
        <div>
          <img
            src="@/assets/images/logo-200x200.png"
            style="width: 12rem; height: 12rem; border: solid 1px lightgray;"
          />
        </div>
        <div>
          <Button label="Subscribe" class="p-button-secondary" />
        </div>
      </div>
      <div class="p-col-7">
        <h3>Welcome to {{ hobby.name }} community</h3>
        <p>{{ hobby.description }}</p>
        <small v-if="hobby.plus18">+18 only</small>
      </div>
    </div>
  </div>
</template>

<script lang="ts">
import { Component, Prop, Vue, Model } from 'vue-property-decorator'
import { Hobby } from './Hobby'

@Component
export default class HobbyView extends Vue {
  @Model() model!: Hobby
  hobby: Hobby = {
    name: 'Jogging',
    description: 'It is a very great hobby',
    plus18: true
  } as Hobby
}
</script>

<style lang="less">
.hobby-form .p-dropdown-filter-container {
  width: 100%;
}
</style>
