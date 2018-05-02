<template>
  <div id="similar-city" @click="emitSearched()" :class="{'active': active}">
    <h1>{{ name }}</h1>
    <h2>{{ zip }}</h2>
  </div>
</template>

<script>
import Bus from '../bus.js'

import axios from 'axios'

export default {
  name: 'SimilarCity',

  props: ['zip', 'active'],

  components: {
    Bus,
    axios
  },

  methods: {
    emitSearched () {
      Bus.$emit('search_submitted', this.place);
    }
  },

  mounted () {
    setTimeout(() => {
      const mapsUrl = 'https://maps.googleapis.com/maps/api/geocode/json';
      const key = 'AIzaSyDUphyKwQ4lJqCpQjn8-F_FuGyTxjp7vV8';
      axios.get(mapsUrl + '?address=' + this.zip + '&key=' + key)
        .then(res => {
          console.log(res);
          this.name = res.data.results[0].formatted_address.slice(0, -11);
          let place = res.data.results[0];
          this.place = {
            name: place.formatted_address.slice(0, -6),
            coords: {
              lat: place.geometry.location.lat,
              lng: place.geometry.location.lng
            },
            zip: this.zip
          }
        })
        .catch(err => {
          alert(err);
        })

    }, 800);

  },

  data () {
    return {
      name: '...',
      place: {}
    }
  }
}

</script>

<style lang="scss">
@import "../../assets/css/style_settings.scss";

#similar-city {
  display: inline-block;
  width: fit-content;
  border: 1px solid $border-color;
  border-radius: $border-radius;
  padding: 6px 24px 12px 20px;
  margin-top: 14px;
  margin-right: 18px;
  cursor: pointer;
  transition: 0.15s ease;

  h1 {
    font-family: $title-font;
    color: $secondary-font-color;
    font-size: 18px;
    line-height: 18px;
    margin-top: 11px;
  }
  h2 {
    font-family: $body-font;
    color: $tertiary-font-color;
    font-size: 14px;
    line-height: 0px;
    margin-top: -8px;
  }
}

#similar-city:hover {
  box-shadow: $box-shadow;
}

#similar-city:active {
  box-shadow: $secondary-box-shadow;
}
</style>
