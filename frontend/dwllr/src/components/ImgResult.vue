<template>
  <div id="img-result">

  </div>
</template>

<script>
import axios from 'axios'

export default {
  name: 'ImgResult',

  props: ['place'],

  mounted () {

  },

  watch: {
    place (newPlace, oldPlace) {
      const key = 'AIzaSyDUphyKwQ4lJqCpQjn8-F_FuGyTxjp7vV8';
      const mapsUrl = 'https://maps.googleapis.com/maps/api/geocode/json?latlng=';
      axios.get(mapsUrl + newPlace.coords.lat + ',' + this.place.coords.lng + '&sensor=false&key=' + key)
        .then(res => {
          console.log(res);
        })
        .catch(err => {
          console.log(err);
        })
    }
  }
}
</script>
