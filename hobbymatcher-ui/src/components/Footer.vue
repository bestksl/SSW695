<template>
  <div class="py-5 text-center footer">
    <div class="links">
      <router-link to="/">Home</router-link>
      <span>|</span>
      <router-link to="/about">About</router-link>
      <span>|</span>
      <router-link to="/register">Register</router-link>
      <span>|</span>
      <router-link to="/login">Login</router-link>
      <span>|</span>
      <router-link to="/hobbies/create">Create Hobby</router-link>
      <span>|</span>
      <router-link to="/events/create">Create Events</router-link>
    </div>
    <small class="d-inline-block pt-3">
      HobbyMatcher - 2020 All Right Reserved.
    </small>
  </div>
</template>

<script lang="ts">
import { Component, Prop, Vue } from 'vue-property-decorator'

@Component
export default class Header extends Vue {}
</script>

<style scoped lang="less">
.footer * {
  color: white;
  margin: 0 1rem;
}
.footer .links a {
  font-style: italic;
  text-decoration: underline;
}
</style>
