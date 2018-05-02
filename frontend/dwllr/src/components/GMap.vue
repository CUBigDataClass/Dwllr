<template>
  <div id="g-map">
    <gmap-map style="width:100%; height:100%;" :zoom="zoom" :center="{lat:this.coords.lat+1, lng:this.coords.lng+0.6}" ref="map">
      <gmap-marker
        v-for="(m, index) in markers"
        :key="index"
        :position="m"
      ></gmap-marker>
    </gmap-map>
  </div>
</template>

<script>
import Bus from './bus.js'

import * as VueGoogleMaps from 'vue2-google-maps'

export default {
  name: 'GMap',

  components: {
    Bus,
    VueGoogleMaps
  },

  mounted () {
    Bus.$on('search_submitted', this.updateMarker);
    Bus.$on('close_search_attr_results', () => {
      //this.center = "{lat: 40.0902, lng: -98.7129}";
      this.$refs.map.panTo({lat: 40.0902, lng: -98.7129});
      this.zoom = 5;
    });
    this.$refs.map.panTo({lat:this.coords.lat+2, lng:this.coords.lng+1.6});

  },

  methods: {

    updateMarker(place) {
      console.log('test');
      console.log(place);
      //this.coords = place.coords;
      this.zoom = 6.2;
      this.markers = [place.coords];
      this.$refs.map.panTo({lat:place.coords.lat-0.5, lng:place.coords.lng+0.8});
    }

  },

  data () {
    return {
      coords: {lat: 40.0902, lng: -98.7129}, // Default to US coords
      zoom: 5,
      markers: []
    }
  }
}
</script>

<style lang="scss">
#g-map {
  width: 100%;
  height: 100%;
  position: absolute;
  top: 0;
  left: 0;
}
</style>
