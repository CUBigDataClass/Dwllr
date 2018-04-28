<template>
  <div id="result">
    <h1>{{ place.name }}&nbsp; <span class="tertiary-font-color" style="font-size:36px;">{{ place.zip }}</span></h1>
  </div>
</template>

<script>

export default {
  name: 'Results',

  props: ['place']
}

</script>

<style lang="scss">
@import "../assets/css/style_settings.scss";

#result {
  position: absolute;
  top: 0;
  right: 0;
  z-index: 4;

  h1 {
      font-family: $title-font;
      font-weight: $title-font-weight;
      color: $secondary-font-color;
      font-size: 42px;
      padding-right: 150px;
  }
}

</style>
