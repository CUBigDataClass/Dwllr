<template>
  <div id="similar-cities">
    <span @click="unActivateResults">
      <similar-city v-for="city in cities" :key="city" :zip="city"></similar-city>
    </span>
  </div>
</template>

<script>
import SimilarCity from './SimilarCity.vue';
import Bus from '../bus.js';

import axios from 'axios';

export default {
  name: 'SimilarCities',

  props: ['cities'],

  components: {
    SimilarCity,
    Bus,
    axios
  },

  methods: {
    unActivateResults () {
      Bus.$emit('unactivate-results');
    }
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
