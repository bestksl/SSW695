<template>
  <div class="event-view-page">
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
    <EventView />
    <div class="p-grid">
      <hr class="p-col-10 p-offset-1 py-0 my-3" />
    </div>
    <Reviews />

    <div class="p-grid">
      <div class="p-col-10 p-offset-1 mt-4">
        <strong>Events you may interested in:</strong>
      </div>
      <div class="p-col-10 p-offset-1">
        <EventThumbs />
      </div>
    </div>
  </div>
</template>
