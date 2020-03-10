<template>
  <div class="hobby-page">
    <div class="p-grid">
      <div class="p-col-10 p-offset-1 text-left">
        <Button
          label="Back"
          icon="pi pi-chevron-left"
          class="p-button-secondary"
        />
      </div>
      <div class="p-col-10 p-offset-1">
        <hr />
      </div>
    </div>

    <HobbyView />

    <div class="p-grid">
      <div class="p-col-10 p-offset-1">
        <hr />
      </div>
    </div>

    <RecentEvents />
  </div>
</template>
