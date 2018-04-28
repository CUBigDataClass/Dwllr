<template>
  <div id="search-by-city">
    <div class="container" :style="{ 'transform': translateData }">

      <transition name="fade">
        <h3 v-if="onDefault">
          Already fond of a particular city?
        </h3>
      </transition>

      <transition name="fade">
        <h4 v-if="onDefault">
          Find a city by name:
        </h4>
      </transition>

      <div class="search-form" :style="{ 'box-shadow': shadowData}">
        <!--<input placeholder="Enter a city name">-->
        <GmapAutocomplete @place_changed="setPlace">

        </GmapAutocomplete>
        <div class="send-button" @click="search()"></div>
      </div>

    </div>
  </div>
</template>

<script>
import Bus from './bus.js'

import * as VueGoogleMaps from 'vue2-google-maps'
import axios from 'axios'

export default {
  name: 'SearchByCity',

  components: {
    VueGoogleMaps
  },

  mounted () {
    Bus.$on('search_submitted', this.move);

  },

  methods: {

    search () {
      Bus.$emit('search_submitted');
    },

    setPlace(place) {

      // Get place object from GMaps
      this.place = place;
      console.log(this.place);

      // Get and store latlng for city
      this.coords = {
        lat: this.place.geometry.location.lat(),
        lng: this.place.geometry.location.lng()
      }
      console.log(this.coords);

      // Use latlng to hit GMaps api again to get and store zip code
      const mapsUrl = 'https://maps.googleapis.com/maps/api/geocode/json';
      const key = 'AIzaSyCYZTGHQL9tayIK6vzOl2evSVynVC41rLo';
      axios.get(mapsUrl + '?latlng=' + this.coords.lat + ',' + this.coords.lng + '&sensor=false&key=' + key)
        .then(res => {
          console.log(res.data);
          res.data.results.forEach(result => {
            if (result.types.includes('postal_code')) {
              result.address_components.forEach(component => {
                if (component.types.includes('postal_code')) {
                  console.log(component.long_name);
                  this.zip = component.long_name;
                  let place = {
                    name: this.place.formatted_address,
                    coords: this.coords,
                    zip: this.zip
                  }
                  Bus.$emit('search_submitted', place);
                }
              });
            }
          });
        })
        .catch(err => {
          console.log(err);
        });
    },

    move() {
      this.onDefault = false;
      this.translateData = 'translate( -' + (this.windowWidth/2 + this.windowWidth/4 - 245) + 'px, 140px)';
      this.shadowData = '0 1px 2px 0 rgba(60,64,67,0.302), 0 1px 3px 1px rgba(60,64,67,0.149)';
    },


  },

  data () {
    return {
      onDefault: true,
      windowWidth: window.innerWidth,
      translateData: 'translate(0px, 0px)',
      shadowData: '0px'
    }
  }
}
</script>

<style lang="scss">
@import "../assets/css/style_settings.scss";

#search-by-city {
  position: absolute;
  width: 50%;
  height: 100%;
  top: 0;
  right: 0;
  z-index: 3;
}

#search-by-city > .container {
  position: absolute;
  top: calc(20%);
  left: calc(50% - 225px);
  width: 500px;
  transition: 0.75s ease !important;
  transform: translate(0%, 0%);
  transform: translateZ(0);
  will-change: transform;
}

.move-to-bottom-left {
  //top: 500px !important;
  transition: 0.75s ease !important;
  transform: translate(0%, 140px) !important;
}

#search-by-city > .container {
  h3 {
    font-family: $body-font;
    font-weight: 400;
    font-size: 18px;
    color: $tertiary-font-color;
    margin-bottom: 14px;
  }
  h4 {
    font-family: $title-font;
    color: $secondary-font-color;
    font-size: 2em;
    margin: 0px 0px 24px 1px;
  }
  .search-form {
    position: absolute;
    width: 450px;
    top: 115px;
    left: 0;
    margin-top: -8px;
    padding: 4px 24px 4px 28px;
    border: 1px solid $border-color;
    border-radius: $border-radius;
    background: white;
    box-shadow: 0 1px 2px 0 rgba(60,64,67,0), 0 1px 3px 1px rgba(60,64,67,0);
    transition: 1s ease;
  }
  .send-button {
    position: absolute;
    top: 22px;
    right: 37px;
    height: 20px;
    width: 20px;
    background: url('../assets/img/send-button.png');
    background-size: cover;
    opacity: 0.3;
    cursor: pointer;
    transition: 0.15s ease;
  }
  .send-button:hover {
    opacity: 0.6;
    right: 35px;
  }
  input {
    border-bottom-right-radius: 0px;
  }


  .fade-enter-active, .fade-leave-active {
    transition: opacity .1s;
  }
  .fade-enter, .fade-leave-to /* .fade-leave-active below version 2.1.8 */ {
    opacity: 0;
  }
}
</style>
