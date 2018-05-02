<template>
  <div id="search-by-attr-results">
    <h1>Search Results</h1>
    <span v-for="(zip, index) in zips" :key="index" @click="updateActive(index)">
      <!--
      <similar-city v-for="(zip, index) in zips"
        :key="index"
        :zip="zip"
        @click="updateActive"
      ></similar-city>
    -->
      <similar-city :zip="zip" :active="isActive(index)"></similar-city>
    </span>
    <div id="close" @click="closeMe()">X</div>
  </div>
</template>

<script>
import SimilarCity from './result-cards/SimilarCity.vue'
import Bus from './bus.js'

//import axios from 'axios'

export default {
  name: 'SearchByAttrResults',

  props: ['zips'],

  components: {
    SimilarCity
  },

  mounted () {
    Bus.$on('unactivate-results', () => {
      this.active = [false, false, false, false];
      this.activeIndex = -1;
    })
  },

  methods: {
    updateActive (index) {
      this.activeIndex = index;
      this.active = [false, false, false, false];
      this.active[this.activeIndex] = true;
      //console.log(index);
    },
    isActive (index) {
      if (this.active[index]) {
        return true;
      }
      return false;
    },
    closeMe () {
      Bus.$emit('close_search_attr_results');
    }
  },

  computed: {

  },

/*
  mounted () {
    Bus.$on('search_attr_submitted', zips => {
      console.log('huh');
      this.zips = zips.slice(0, 5);
      let firstZip = zips[0];

      const mapsUrl = 'https://maps.googleapis.com/maps/api/geocode/json';
      const key = 'AIzaSyDUphyKwQ4lJqCpQjn8-F_FuGyTxjp7vV8';
      axios.get(mapsUrl + '?address=' + firstZip + '&key=' + key)
        .then(res => {
          console.log(res);
          let place = res.data.results[0];
          let placeObj = {
            name: place.formatted_address.slice(0, -5),
            coords: {
              lat: place.geometry.location.lat,
              lng: place.geometry.location.lng
            },
            zip: firstZip
          };
          Bus.$emit('search_submitted', placeObj);
        })
        .catch(err => {
          alert(err);
        })
    });
  },
*/
  data () {
    return {
      active: [true, false, false, false],
      activeIndex: 0
    }
  }
}
</script>

<style lang="scss">
@import "../assets/css/style_settings.scss";

#search-by-attr-results {
  z-index: 3;
  position: absolute;
  bottom: 0;
  left: 0;
  padding-bottom: 25px;
  padding-left: 30px;
  //background: #fffdf9;
  width: 100%;

  #similar-city {
    background: white;
    box-shadow: $box-shadow;

    h1 {
      padding-bottom: 10px;
    }
  }
  #similar-city:hover {
    box-shadow: $secondary-box-shadow;
  }
  #similar-city:active {
    box-shadow: 0 10px 20px rgba(0,0,0,0.19), 0 6px 6px rgba(0,0,0,0.23);
  }
  #similar-city.active {
    box-shadow: 0 10px 20px rgba(0,0,0,0.19), 0 6px 6px rgba(0,0,0,0.23);
  }

  #close {
    font-family: $body-font;
    font-weight: 700;
    font-size: 20px;
    color: $tertiary-font-color;
    position: absolute;
    top: 60px;
    left: 660px;
    cursor: pointer;
  }
  #close:hover {
    color: #E8483D;
  }
  #close:active {
    color: $main-font-color;
  }
}
#search-by-attr-results >  h1 {
  font-size: 2.4em;
  color: $secondary-font-color;
  font-family: $title-font;
  transform: translateY(28px);
  padding-left: 8px;
}
#search-by-attr-results::before {
  content: '';
  width: 100%;
  height: 100%;
  position: absolute;
  top: 0;
  left: 0;
  background: linear-gradient(to top, rgba(255, 255, 243, 1), rgba(255, 255, 243, 1), rgba(255, 255, 243, 0));
  z-index: -1;

}

</style>
