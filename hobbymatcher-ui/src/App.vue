<template>
  <div id="app">
    <Header />
    <div class="py-5 app-body">
      <router-view />
    </div>
    <Footer />
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
