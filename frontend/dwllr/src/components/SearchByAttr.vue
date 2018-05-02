<template>
  <div id="search-by-attr">
    <div class="container" :style="{ 'transform': translateData }">

      <transition name="fade">
        <h3 v-if="onDefault">
          What characteristics make up your perfect city?
        </h3>
      </transition>

      <transition name="fade">
        <h4 v-if="onDefault">
          Find cities by attributes:
        </h4>
      </transition>

      <div
        class="search-form"
        :class="{darken: isFocused}"
        :style="{'box-shadow': shadowData}"
        @keyup.enter="search()"
        >

        <div class="input-group">
          <label>Population <span class="secondary-label"># people</span></label>
          <input placeholder="Enter a number" @focus="isFocused = true" @blur="isFocused = false" v-model="population">
        </div>

        <div class="input-group">
          <label>Average Rent <span class="secondary-label">$ per month</span></label>
          <input placeholder="Enter a number" @focus="isFocused = true" @blur="isFocused = false" v-model="rent">
        </div>

        <div class="input-group">
          <label>Average Income <span class="secondary-label">$ per year</span></label>
          <input placeholder="Enter a number" @focus="isFocused = true" @blur="isFocused = false" v-model="income">
        </div>

        <div class="input-group">
          <label>Unemployment <span class="secondary-label">% of people</span></label>
          <input placeholder="Enter a number" @focus="isFocused = true" @blur="isFocused = false" v-model="unemployment">
        </div>

        <button @click="search">Search for cities<div class="send-arrow"></div></button>

      </div>
    </div>
  </div>
</template>

<script>
import Bus from './bus.js';

import axios from 'axios'

export default {
  name: 'SearchByAttr',

  // components: {
  //   axios
  // },

  mounted () {
    Bus.$on('search_submitted', this.move);
  },

  methods: {

    search () {
      //Bus.$emit('search_submitted');
      this.getCities()
    },

    move () {
      this.onDefault = false;
      this.translateData = 'translate(-' + (this.windowWidth/4 - 255) +'px, -185px)';
      this.shadowData = '0 1px 2px 0 rgba(60,64,67,0.302), 0 1px 3px 1px rgba(60,64,67,0.149)';
    },

    getAttributes() {
      var obj = {};

      obj = this.addSantizedData(obj, "population", this.population);
      obj = this.addSantizedData(obj, "averageincome", this.income);
      obj = this.addSantizedData(obj, "medianrent", this.rent);
      obj = this.addSantizedData(obj, "unemployment", this.unemployment);

      return obj;
    },

    addSantizedData(obj, key, value) {
      var valueAsFloat = parseFloat(value)
      if (!isNaN(valueAsFloat)) {
        obj[key] = valueAsFloat;
      }

      return obj;
    },

    getCities() {
      axios.get('/api/search', {
        params: this.getAttributes()
      })
        .then(res => {
          //console.log(res.data);
          let zipResults = res.data.cities.slice(0, 4);
          Bus.$emit('start_loading');
          Bus.$emit('search_attr_submitted', zipResults);
          console.log(zipResults);
        })
        .catch(err => {
          console.log(err);
        })
    }

  },

  data () {
    return {
      isFocused: false,
      onDefault: true,
      windowWidth: window.innerWidth,
      translateData: 'translate(0px, 0px)',
      shadowData: '0px',
      population: '',
      income: '',
      rent: '',
      unemployment: ''
    }
  }
}
</script>

<style lang="scss">
@import "../assets/css/style_settings.scss";

#search-by-attr {
  position: absolute;
  width: 50%;
  height: 100%;
  top: 0;
  left: 0;
  z-index: 3;
}

#search-by-attr > .container {
  position: absolute;
  top: calc(20%);
  left: calc(50% - 225px);
  width: 500px;
  transition: 0.75s ease;
  transform: translateZ(0);
  will-change: transform;
}

.move-to-top-left {
  top: -5px !important;
  left: 20px !important;
}

#search-by-attr > .container {
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
    background: #fff;
    top: 115px;
    left: 0;
    width: 450px;
    margin-top: -8px;
    padding: 12px 24px 24px 28px;
    border: 1px solid $border-color;
    border-radius: $border-radius;
    box-shadow: 0 1px 2px 0 rgba(60,64,67,0.0), 0 1px 3px 1px rgba(60,64,67,0.0);
    transition: 1s ease;
  }
  .sub-heading {
    font-size: 26px;
    margin-left: 12px;
    line-height: 60px;
  }
  button {
    margin-top: 12px;
    margin-bottom: -6px;
    font-size: 18px;
    font-family: $body-font;
    color: $tertiary-font-color;
    padding: 10px;
    width: 100%;
    border: 0px;
    outline: none;
    border: 1px solid $border-color;
    border-radius: $border-radius;
    position: relative;
    cursor: pointer;
    transition: 0.15s ease;

    .send-arrow {
      background: url('../assets/img/send-button.png');
      background-size: cover;
      width: 20px;
      height: 20px;
      position: absolute;
      top: 12px;
      right: 12px;
      opacity: 0.3;
      transition: 0.15s ease;
    }

  }
  button:hover {
    box-shadow: $box-shadow;
    //border: 1px solid rgba(255,255,255,0);
    .send-arrow {
      opacity: 0.6;
      right: 10px;
    }
  }
  button:active {
    box-shadow: $secondary-box-shadow;
  }
}

.input-group {
  margin: 16px 0px -4px 0px;
}

.darken {
  //background: #f0f0f0;
}

.fade-enter-active, .fade-leave-active {
  transition: opacity .2s;
}
.fade-enter, .fade-leave-to /* .fade-leave-active below version 2.1.8 */ {
  opacity: 0;
}
</style>
