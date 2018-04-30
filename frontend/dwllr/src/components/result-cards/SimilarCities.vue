<template>
  <div id="similar-cities">
    <similar-city v-for="city in cities" :key="city" :zip="city"></similar-city>
  </div>
</template>

<script>
import SimilarCity from './SimilarCity.vue';

import axios from 'axios';

export default {
  name: 'SimilarCities',

  props: ['cities'],

  components: {
    SimilarCity,
    axios
  },

  watch: {
    cities: (newa, olda) => {
      console.log('change:', newa, olda);
    }
  }
}
</script>

<style lang="scss">
#similar-cities {
  //height: 100px;
  //overflow-x: scroll;
  margin-top: -16px;
  padding-left: 4px;
}
</style>
