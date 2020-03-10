<template>
  <div id="app">
    <Header />
    <div class="py-5 app-body">
      <router-view />
    </div>
    <Footer />
    <hr />
    <div class="only-in-dev text-center">
      <div>--- only in development ---</div>
      <router-link to="/hobbies/create">Create Hobby</router-link>
      <span class="mx-2">|</span>
      <router-link to="/hobbies/view">View Hobby</router-link>
      <span class="mx-2">|</span>
      <router-link to="/events/create">Create Event</router-link>
      <span class="mx-2">|</span>
      <router-link to="/events/view">View Event</router-link>
      <span class="mx-2">|</span>
      <router-link to="/search/more-events">More Events</router-link>
    </div>
  </div>
</template>

<style lang="less">
// import bootstrap css utility classes
@import (less) './assets/stylesheets/bootstrap-utilities.min.css';

// reset body
body {
  margin: 0;
  padding: 0;
  width: 100%;
  height: 100%;
  font-family: 'Trebuchet MS', 'Lucida Sans Unicode', 'Lucida Grande',
    'Lucida Sans', Arial, sans-serif;
  background-color: #34495e;
}

// fix unnecessary horizontal scroll
.p-grid {
  margin-left: 0 !important;
  margin-right: 0 !important;
}

.app-body {
  background-color: white;
}

.only-in-dev {
  * {
    color: white;
  }
}
</style>
