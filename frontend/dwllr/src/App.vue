<template>
  <div id="app">

    <div id="logo" :class="{'float': !isDefault}">
      <h1>Dwllr.</h1>
      <h2>Find your perfect place</h2>
    </div>

    <DefaultBackground></DefaultBackground>

    <Search></Search>

  </div>
</template>


<script>
import DefaultBackground from './components/DefaultBackground.vue'
import Search from './components/Search.vue'
import Bus from './components/bus.js'
import axios from 'axios'

export default {
  name: 'app',

  components: {
    DefaultBackground,
    Search
  },

  mounted () {
    Bus.$on('search-clicked', this.toMap);
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
    toMap () {
      this.isDefault = false;
    }
  },

  data () {
    return {
      isDefault: true
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
  top: 40px;
  left: 80px;
  //border-right: 1px solid $border-color;
  border-bottom: 0px solid $border-color;
  padding-right: 24px;
  padding-bottom: 12px;
  border-bottom-right-radius: 20px;
  //box-shadow: $box-shadow;
  background: rgba(255,255,255,0);
  transition: 0.75s ease;
  transform: translateZ(0) scale(1.6);
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
  transform: scale(1.2) translate(-40px, -22px)!important;
  //border-width: 1px !important;
  //border-radius: 10px !important;
  //box-shadow: 0 1px 2px 0 rgba(60,64,67,0.302), 0 1px 3px 1px rgba(60,64,67,0.149);
}
</style>
