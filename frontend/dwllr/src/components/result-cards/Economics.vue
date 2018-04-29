<template>
  <div id="economics">
    <div class="third">
      <h1><span class="tertiary-font-color" style="padding-right: 4px;">$</span>{{ numberWithCommas(income) }}</h1>
      <h2 class="sub">average income</h2>
    </div>
    <div class="third">
      <h1><span class="tertiary-font-color" style="padding-right: 4px;">$</span>{{ numberWithCommas(rent) }}</h1>
      <h2 class="sub">median rent</h2>
    </div>
    <div class="third">
      <h1><span class="tertiary-font-color" style="padding-right: 4px;"></span>{{ unemployment }}<span class="tertiary-font-color" style="padding-left: 4px">%</span></h1>
      <h2 class="sub">unemployment</h2>
    </div>
  </div>
</template>

<script>

export default {
  name: 'Economics',

  props: ['income', 'unemployment', 'rent'],

  methods: {

    // Shamelessly stolen from:
    // https://stackoverflow.com/questions/2901102/how-to-print-a-number-with-commas-as-thousands-separators-in-javascript
    numberWithCommas (x) {
      return x.toString().replace(/\B(?=(\d{3})+(?!\d))/g, ",");
    }

  },

  mounted() {
  }

}
</script>

<style lang="scss">
@import "../../assets/css/style_settings.scss";

#economics {
  margin-top: -22px;
  margin-bottom: -8px;

  .third {
    width: 33%;
    padding-left: 6px;
    display: inline-block;
  }
  h1 {
    color: $secondary-font-color;
    font-size: 32px;
    text-align: center;
  }
  h2 {
    margin-top: -14px;
    text-align: center;
  }
  .sub {
    font-size: 18px;
    color: $tertiary-font-color;
  //  margin-top: -40px;
    padding-left: 6px;
  }
}
</style>
