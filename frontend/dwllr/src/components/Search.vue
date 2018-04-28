<template>
  <div id="search">
    <search-by-attr></search-by-attr>
    <search-by-city></search-by-city>
  </div>
</template>


<script>
import SearchByAttr from './SearchByAttr.vue'
import SearchByCity from './SearchByCity.vue'

export default {
  name: 'Search',

  components: {
    SearchByAttr,
    SearchByCity
  }
}
</script>


<style lang="scss">
@import "../assets/css/style_settings.scss";

#search {
  position: absolute;
  top: 0;
  left: 0;
  z-index: 2;
  width: 100%;
  height: 100%;
}

#search label {
  font-size: 1.06em;
  font-weight: 700;
  font-family: $title-font;
  color: $secondary-font-color;
}
#search .secondary-label {
  font-size: 14px;
  font-weight: 400;
  color: $tertiary-font-color;
  margin-left: 2px;
}

#search input {
  font-family: $body-font;
  font-size: 18px;
  font-weight: 400;
  color: $tertiary-font-color;
  padding: 10px 14px 8px 14px;
  margin: 6px 6px 12px 0px;
  width: 100%;
  outline: none;
  border: 0px;
  border-bottom: 1px solid $border-color;
  background: #fff;
  border-bottom-left-radius: 15px;
  transition: 0.2s ease;
}
#search input:hover {
  border-color: #c3c3c3;
  border-radius: 10px;
}
#search input:focus {
  border-color: #a3a3a3;
  border-radius: 0px;
}
</style>
