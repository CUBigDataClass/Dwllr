<template>
  <div id="default-background">

    <div class="filter" :class="{'zero-opacity': !visible}"></div>
    <div class="partial-filter" :class="{'super-inset': visible}"></div>

    <transition name="fade">
      <div id="top" v-if="visible"></div>
    </transition>

    <transition name="fade">
      <div id="or-container" v-if="visible">
        or
      </div>
    </transition>

    <transition name="fade">
      <div id="bottom" v-if="visible"></div>
    </transition>

  </div>
</template>

<script>
import Bus from './bus.js';

export default {
  name: 'DefaultBackground',

  components: {
    Bus
  },

  mounted () {
    Bus.$on('search-clicked', this.fadeOut);
  },

  methods: {
    fadeOut () {
      this.visible = false;
    }
  },

  data () {
    return {
      visible: true
    }
  }
}
</script>

<style lang="scss">
@import "../assets/css/style_settings.scss";

#default-background {
  position: absolute;
  top: 0;
  left: 0;
  z-index: 1;
  width: 100%;
  height: 100%;
  background: $bg-color;
  background: url('http://oobrien.com/wordpress/wp-content/uploads/2016/07/googlemaps_july2016.jpg');

}

// Vertical seperators
#top {
  content: '';
  width: $search-seperator-width;
  height: 38%;
  background: #aaaaaa;
  position: absolute;
  top: 0;
  left: 50%;
  border-radius: 4px;
  transition: 0.15s ease;
}
#bottom {
  content: '';
  width: $search-seperator-width;
  height: 48%;
  background: #aaaaaa;
  position: absolute;
  bottom: 0;
  left: 50%;
  border-radius: 4px;
  transition: 0.15s ease !important;
}

 // Or in vertical seperators
#default-background > #or-container {
  font-family: $title-font;
  font-size: 42px;
  color: #aaaaaa;
  text-align: center;
  position: absolute;
  width: 100%;
  top: calc(45% - 30px);
  z-index: 2;
  transition: 0.15s ease;
}

.filter {
  position: absolute;
  width: 100%;
  height: 100%;
  background: #fff;
  opacity: 0.7;
  transition: 1s ease;
}
.partial-filter {
  position: absolute;
  width: 100%;
  height: 100%;
  box-shadow: inset 100px 175px 400px 20px #fffdf9;
  transition: 1.5s ease !important;
}
.super-inset {
  box-shadow: inset 400px 400px 400px 40px rgba(255,255,255,1);
}
.zero-opacity {
  opacity: 0;
}
</style>
