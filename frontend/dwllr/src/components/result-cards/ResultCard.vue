<template>
  <div id="result-card">
    <h1>{{ name }}</h1>
    <slot></slot>
  </div>
</template>

<script>

export default {
  name: 'ResultCard',

  props: ['name']
}
</script>

<style lang="scss">
@import "../../assets/css/style_settings.scss";

#result-card {
  width: 95%;
  margin-left: -2%;
  background: #fff;
  padding: 0px 30px 26px 30px;
  border: 1px solid $border-color;
  border-radius: $border-radius;
  box-shadow: $box-shadow;
  margin-top: 0px;
  margin-bottom: 18px;

  h1 {
    font-size: 18px;
    font-family: $title-font;
    color: $main-font-color;
    margin-top: 26px;
  }
}


</style>
