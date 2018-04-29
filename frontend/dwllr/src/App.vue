<template>
  <div id="app">

    <GMap></GMap>

    <Background></Background>

    <div id="logo" :class="{'float': shrinkLogo}">
      <h1>Dwllr.</h1>
      <h2>Find your perfect place</h2>
    </div>
<!--
    <svg height="400" width="100%">
        <path d="M0,250 C50,30 274,250 1200,0" fill="none" stroke="#d3d3d3" stroke-width="1" />
        <path d="M800,18 C813,186 874,319 1000,18" style="transform: translate(-169px,-94px);" fill="white" />
    </svg>
-->

    <search></search>

    <!--<transition name="slide-fade">-->
    <result :show="showResults" :place="place"></result>
    <!--<img-result :show="showResults" :place="place"></img-result>-->
    <!--</transition>-->

  </div>
</template>


<script>
// Custom components
import GMap from './components/GMap.vue'
import Background from './components/Background.vue'
import Search from './components/Search.vue'
import Result from './components/Result.vue'
import ImgResult from './components/ImgResult.vue'
import Bus from './components/bus.js'

// 3rd party components
import axios from 'axios'

export default {
  name: 'app',

  components: {
    GMap,
    Background,
    Search,
    Result,
    ImgResult
  },

  mounted () {
    Bus.$on('search_submitted', this.transitionToResults);
    axios.get('/api/search', {
      params: {city: 80303}
    })
      .then(res => {
        console.log(res);
      })
      .catch(err => {
        console.log(err);
      });
  },

  methods: {
    transitionToResults (place) {
      this.shrinkLogo = true;

      if (!this.showResults) {
        setTimeout(() => {
          this.place = place;
          this.showResults = true;
        }, 1000);
      }

      else {
        this.place = place;
      }
    }
  },

  data () {
    return {
      showResults: false,
      shrinkLogo: false,
      place: {name: '', coords: '', zip: ''}
    }
  }

}
</script>


<style lang="scss">
@import "./assets/css/style_settings.scss";

// Import Roboto Condensed Bold for titles
// Import Cabin for body
@import url('https://fonts.googleapis.com/css?family=Cabin:400,700|Roboto+Condensed:700,400');

// Make app fill entire screen regardless of content, remove default body margins
html, body {
  width: 100%;
  min-height: 100%;
  margin: 0;
  overflow: hidden;
}

* {
  box-sizing: border-box;
}

#app {
  width: 100%;
  min-height: 100%;
  font-family: $body-font;
  color: $main-font-color;
  -webkit-font-smoothing: antialiased;
  -moz-osx-font-smoothing: grayscale;
}

// Universal utility styles
.tertiary-font-color {
  color: $tertiary-font-color;
}
.body-font {
  font-family: $body-font;
}
.slim-weight {
  font-weight: 400;
}

svg {
  position: absolute;
  z-index: 10;
  top: 0;
  left: 0;
}

#app > #logo {
  position: absolute;
  z-index: 3;
  top: 18px;
  left: 35px;
  //border-right: 1px solid $border-color;
  border-bottom: 0px solid $border-color;
  padding-right: 24px;
  padding-bottom: 12px;
  border-bottom-right-radius: 20px;
  //box-shadow: $box-shadow;
  background: rgba(255,255,255,0);
  transition: 0.75s ease;
  transform: translateZ(0) scale(1);
  will-change: transform;
  cursor: pointer;
}

#app > #logo > h1 {
  font-family: $title-font;
  font-size: 3em;
  margin: 10px 0px 0px 18px;
  color: $main-font-color;
  width: 50%;
}
#app > #logo > h2 {
  font-family: $title-font;
  font-size: 27px;
  font-weight: 400;
  color: $tertiary-font-color;
  margin: 0px 0px 0px 19px;
}
.float {
  //top: 15px !important;
  //left: 35px !important;
  transform: scale(0.8) translate(-50px, -26px)!important;
  //border-width: 1px !important;
  //border-radius: 10px !important;
  //box-shadow: 0 1px 2px 0 rgba(60,64,67,0.302), 0 1px 3px 1px rgba(60,64,67,0.149);
}


</style>
