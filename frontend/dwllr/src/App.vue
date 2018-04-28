<template>
  <div id="app">

    <GMap></GMap>

    <Background></Background>

    <div id="logo" :class="{'float': shrinkLogo}">
      <h1>Dwllr.</h1>
      <h2>Find your perfect place.</h2>
    </div>

    <Search></Search>

    <transition name="slide-fade">
      <Result v-if="showResults" :place="place"></Result>
    </transition>

  </div>
</template>


<script>
// Custom components
import GMap from './components/GMap.vue'
import Background from './components/Background.vue'
import Search from './components/Search.vue'
import Result from './components/Result.vue'
import Bus from './components/bus.js'

// 3rd party components
import axios from 'axios'

export default {
  name: 'app',

  components: {
    GMap,
    Background,
    Search,
    Result
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
@import url('https://fonts.googleapis.com/css?family=Cabin|Roboto+Condensed:700,400');

// Make app fill entire screen regardless of content, remove default body margins
html, body {
  width: 100%;
  min-height: 100%;
  margin: 0;
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

#app > #logo {
  position: absolute;
  z-index: 3;
  top: 30px;
  left: 70px;
  //border-right: 1px solid $border-color;
  border-bottom: 0px solid $border-color;
  padding-right: 24px;
  padding-bottom: 12px;
  border-bottom-right-radius: 20px;
  //box-shadow: $box-shadow;
  background: rgba(255,255,255,0);
  transition: 0.75s ease;
  transform: translateZ(0) scale(1.5);
  will-change: transform;
}

#app > #logo > h1 {
  font-family: $title-font;
  margin: 10px 0px 0px 18px;
}
#app > #logo > h2 {
  font-family: $title-font;
  font-size: 18px;
  font-weight: 400;
  color: $tertiary-font-color;
  margin: 0px 0px 0px 19px;
}
.float {
  //top: 15px !important;
  //left: 35px !important;
  transform: scale(1.2) translate(-30px, -12px)!important;
  //border-width: 1px !important;
  //border-radius: 10px !important;
  //box-shadow: 0 1px 2px 0 rgba(60,64,67,0.302), 0 1px 3px 1px rgba(60,64,67,0.149);
}

.slide-fade-enter-active {
  transition: all .5s ease;
}
.slide-fade-leave-active {
  transition: all .8s cubic-bezier(1.0, 0.5, 0.8, 1.0);
}
.slide-fade-enter, .slide-fade-leave-to
/* .slide-fade-leave-active below version 2.1.8 */ {
  transform: translateY(-100px);
  //opacity: 0;
}
</style>
