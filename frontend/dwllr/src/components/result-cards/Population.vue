<template>
  <div id="population">
    <!-- population density woud be better!! -->
    <h1>{{ numberWithCommas(population) }}<span class="sub">people</span></h1>
  </div>
</template>

<script>

export default {
  name: 'Population',

  props: ['population'],

  methods: {

    // Shamelessly stolen from:
    // https://stackoverflow.com/questions/2901102/how-to-print-a-number-with-commas-as-thousands-separators-in-javascript
    numberWithCommas (x) {
      return x.toString().replace(/\B(?=(\d{3})+(?!\d))/g, ",");
    }

  },

  data () {
    return {
    }
  }
}
</script>

<style lang="scss">
@import "../../assets/css/style_settings.scss";

#population {
  margin-top: -10px;
  padding-left: 26px;
  margin-bottom: -20px;

  h1 {
    color: $secondary-font-color;
    font-size: 32px;
  }
  .sub {
    font-size: 18px;
    color: $tertiary-font-color;
  //  margin-top: -40px;
    padding-left: 10px;
  }
}
</style>
