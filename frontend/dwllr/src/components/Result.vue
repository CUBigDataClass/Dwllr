<template>
  <div id="result">

    <transition name="slide-down-fade">
      <h1 v-if="show">{{ place.name.slice(0,-5).replace(/[0-9]/g, '') }}&nbsp; <span class="tertiary-font-color" style="">{{ place.zip }}</span></h1>
    </transition>

    <div class="scroll-box"
      :class="{'scroll-box': true, 'shadow': showShadow}"
      :style="height"
      v-if="show"
      v-on:scroll="scrollFunc"
      ref="scrollbox"
    >
      <!-- Result Cards for city stats-->
      <transition name="slide-up-fade">
        <result-card v-if="loadFirst" name="Similar Cities" :key="triggerTransition">
          <SimilarCities :cities="cities"></SimilarCities>
        </result-card>
      </transition>

      <transition name="slide-up-fade">
        <result-card v-if="loadSecond" name="Economy" :key="triggerTransition">
          <economics
            :income="stats.averageincome"
            :unemployment="stats.unemployment"
            :rent="stats.medianrent"
          ></economics>
        </result-card>
      </transition>

      <transition name="slide-up-fade">
        <result-card v-if="loadThird" name="Population" :key="triggerTransition">
          <population :population="stats.population"></population>
        </result-card>
      </transition>

      <transition name="slide-up-fade">
        <result-card v-if="loadFourth" name="Gender" :key="triggerTransition">
          <sex-demographics
            :malePercentage="stats.malePercentage"
            :femalePercentage="stats.femalePercentage">
          </sex-demographics>
        </result-card>
      </transition>


      <transition name="slide-up-fade">
        <result-card v-if="loadFifth" name="Education" :key="triggerTransition">
          <education-demographics
            :noHighSchool='stats.noHighSchool'
            :highSchoolNoDiploma='stats.highSchoolNoDiploma'
            :highSchoolGraduate='stats.highSchoolGraduate'
            :someCollegeNoDegree='stats.someCollegeNoDegree'
            :associatesDegree='stats.associatesDegree'
            :bachelorsDegree='stats.bachelorsDegree'
            :graduateDegree='stats.graduateDegree'>
          </education-demographics>
        </result-card>
      </transition>

      <transition name="slide-up-fade">
        <result-card v-if="loadFifth" name="Age" :key="triggerTransition">
          <age-demographics
            :lessThanTen="stats.lessThanTen"
            :tenToNineteen="stats.tenToNineteen"
            :twentyToTwentynine="stats.twentyToTwentynine"
            :thirtyToThirtynine="stats.thirtyToThirtynine"
            :fourtyToFourtynine="stats.fourtyToFourtynine"
            :fiftyToFiftynine="stats.fiftyToFiftynine"
            :sixtyToSixtynine="stats.sixtyToSixtynine"
            :seventyToSeventynine="stats.seventyToSeventynine"
            :eightyAndUp="stats.eightyAndUp">
          </age-demographics>
        </result-card>
      </transition>


      <transition name="slide-up-fade">
        <result-card v-if="loadFifth" name="Race" :key="triggerTransition">
          <racial-demographics
            :whitePercentage="stats.whitePercentage"
            :blackPercentage="stats.blackPercentage"
            :nativeAmericanPercentage="stats.nativeAmericanPercentage"
            :asianPercentage="stats.asianPercentage"
            :pacificIslanderPercentage="stats.pacificIslanderPercentage"
            :otherPercentage="stats.otherPercentage"
            :twoOrMoreRacePercentage="stats.twoOrMoreRacePercentage">
          </racial-demographics>
        </result-card>
      </transition>

      <transition name="slide-up-fade">
        <result-card v-if="loadFifth" name="Geography" :key="triggerTransition">
          <geographic-demographics
            :urbanPopulation="stats.urbanPopulation"
            :ruralPopulation="stats.ruralPopulation">
          </geographic-demographics>
        </result-card>
      </transition>
    </div>
  </div>
</template>

<script>
import ResultCard from './result-cards/ResultCard.vue'
import SimilarCities from './result-cards/SimilarCities.vue'
import Population from './result-cards/Population.vue'
import Economics from './result-cards/Economics.vue'
import RacialDemographics from './result-cards/RacialDemographics.vue'
import SexDemographics from './result-cards/SexDemographics.vue'
import AgeDemographics from './result-cards/AgeDemographics.vue'
import GeographicDemographics from './result-cards/GeographicDemographics.vue'
import EducationDemographics from './result-cards/EducationDemographics.vue'

import axios from 'axios'

export default {
  name: 'Results',

  props: ['place', 'show'],

  components: {
    ResultCard,
    SimilarCities,
    Population,
    Economics,
    RacialDemographics,
    SexDemographics,
    AgeDemographics,
    GeographicDemographics,
    EducationDemographics,
    axios
  },

  mounted () {
    console.log(this.cities);
  },

  methods: {
    upateCities (place) {
      axios.get('/api/search', {
        params: {city: place.zip}
      })
        .then(res => {
          console.log(res.data.data);
          console.log(this.similarCities);
          this.cities = res.data.data.similarCities;
          this.state = res.data.data.stats;
          console.log(this.similarCities);
        })
        .catch(err => {
          console.log(err);
        })

    },

    scrollFunc() {
      //console.log(this.$refs.scrollbox);
      if (this.$refs.scrollbox.scrollTop !== 0) {
        this.showShadow = true;
      }
      else {
        this.showShadow = false;
      }
    },

    loadWaterfall () {
      setTimeout(() => {
        this.$data.loadFirst = true;
        setTimeout(() => {
          this.$data.loadSecond = true;
          setTimeout(() => {
            this.$data.loadThird = true;
            setTimeout(() => {
              this.$data.loadFourth = true;
              setTimeout(() => {
                this.$data.loadFifth = true;
              }, 100);
            }, 100);
          }, 100);
        }, 100);
      }, 20);
    }
  },

  watch: {
    place: function (newPlace, oldPlace) {
      this.loadFirst = false;
      this.loadSecond = false;
      this.loadThird = false;
      this.loadFourth = false;
      this.loadFifth= false;
      this.place = newPlace;
      axios.get('/api/search', {
        params: {city: this.place.zip}
      })
        .then(res => {
          this.$data.stats = res.data.data.stats;
          this.$data.cities = res.data.data.similarCities;
          //this.$data.triggerTransition = !this.$data.triggerTransition;
          this.loadWaterfall();
        })
        .catch(err => {
          console.log(err);
        });
    },

    show: function (newShow, oldShow) {
      if (newShow && !oldShow) {

      }
    }
  },

  data () {
    return {
      stats: {},
      cities: [],
      loadFirst: false,
      loadSecond: false,
      loadThird: false,
      loadFourth: false,
      loadFifth: false,
      height: 'height:' + (window.innerHeight + 103) + 'px',
      showShadow: false,
      triggerTransition: false
    }
  }
}

</script>

<style lang="scss">
@import "../assets/css/style_settings.scss";

#result {
  position: absolute;
  top: 0;
  right: 0;
  z-index: 4;
  width: 600px;
  transform: translateZ();

  h1 {
      font-family: $title-font;
      font-weight: $title-font-weight;
      color: $secondary-font-color;
      font-size: 48px;
      margin-top: 16px;
      margin-bottom: 22px;
  }

  .scroll-box {
    border-top: 1px solid rgba(211,211,211,0.6);
    border-top-left-radius: 8px;
    overflow-y: auto;
    padding-left: 2px;
    padding-top: 25px;
    padding-bottom: 240px;
    margin-top: -10px;
    position: absolute;
    z-index: 2;
    width: calc(100% + 30px);
    margin-left: -25px;
  }
  .scroll-box::before {
    content: "";
    position: fixed;
    width: 80%;
    height: 4px;
    //background: green;
    z-index: 2;
    transform:translateY(-32px);
    box-shadow: $secondary-box-shadow;
    opacity: 0;
    transition: 0.15s ease;
  }
  .scroll-box.shadow::before {
    opacity: 1;
  }
  .scroll-box.shadow {
    //border-color: $border-color;
  }
}


.slide-down-fade-enter-active {
  transition: all .5s ease;
}
.slide-down-fade-leave-active {
  transition: all .8s cubic-bezier(1.0, 0.5, 0.8, 1.0);
}
.slide-down-fade-enter, .slide-down-fade-leave-to
/* .slide-fade-leave-active below version 2.1.8 */ {
  transform: translateY(-100px);
  opacity: 0;
}

.slide-up-fade-enter-active {
  transition: all .7s ease;
}
.slide-up-fade-leave-active {
  transition: all 0.2s cubic-bezier(1.0, 0.5, 0.8, 1.0);
}
.slide-up-fade-enter
/* .slide-fade-leave-active below version 2.1.8 */ {
  transform: translateY(1000px);
  opacity: 0;
}
.slide-up-fade-leave-to {
  transform: translateY(-1000px);
  opacity: 0;
}

#doughnut-chart {
  width: 100% !important;
  height: 200px !important;
  margin-top: -8px;
}

::-webkit-scrollbar-track {
    background: transparent;
}
</style>
