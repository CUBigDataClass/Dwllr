<template>
  <div id="result">

    <transition name="slide-down-fade">
      <h1 v-if="show">{{ place.name.slice(0,-5) }}&nbsp; <span class="tertiary-font-color" style="">{{ place.zip }}</span></h1>
    </transition>

    <div class="scroll-box" :style="height">
      <!-- Result Cards for city stats-->
      <transition name="slide-up-fade">
        <result-card v-if="loadFirst" name="Similar Cities">
          <SimilarCities :cities="cities"></SimilarCities>
        </result-card>
      </transition>

      <transition name="slide-up-fade">
        <result-card v-if="loadSecond" name="Population">
          <population :population="stats.population"></population>
        </result-card>
      </transition>

      <transition name="slide-up-fade">
        <result-card v-if="loadThird" name="Economy">
          <economics
            :income="stats.averageincome"
            :unemployment="stats.unemployment"
            :rent="stats.medianrent"
          ></economics>
        </result-card>
      </transition>

      <transition name="slide-up-fade">
        <result-card v-if="loadFourth" name="Geographic Demographics">
          <geographic-demographics
            :urbanPopulation="stats.urbanPopulation"
            :ruralPopulation="stats.ruralPopulation">
          </geographic-demographics>
        </result-card>
      </transition>

      <transition name="slide-up-fade">
        <result-card v-if="loadFifth" name="Education Demographics">
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
        <result-card v-if="loadFifth" name="Age Demographics">
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
        <result-card v-if="loadFifth" name="Sex Demographics">
          <sex-demographics
            :malePercentage="stats.malePercentage"
            :femalePercentage="stats.femalePercentage">
          </sex-demographics>
        </result-card>
      </transition>

      <transition name="slide-up-fade">
        <result-card v-if="loadFifth" name="Racial Demographics">
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

    }
  },

  watch: {
    place: function (newPlace, oldPlace) {
      console.log(this);
      this.place = newPlace;
      console.log(newPlace);
      console.log('prop changed', newPlace, oldPlace);
      axios.get('/api/search', {
        params: {city: this.place.zip}
      })
        .then(res => {
          console.log(res.data.data);
          console.log(this.similarCities);
          this.$data.stats = res.data.data.stats;
          console.log(this.$data.stats.population);
          this.$data.cities = res.data.data.similarCities;
          console.log(this.$data.similarCities);
        })
        .catch(err => {
          console.log(err);
        });
    },

    show: function (newShow, oldShow) {
      if (newShow && !oldShow) {
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
                }, 60);
              }, 60);
            }, 60);
          }, 60);
        }, 60);
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
      height: 'height:' + (window.innerHeight + 103) + 'px'
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
  width: 560px;
  transform: translateZ();

  h1 {
      font-family: $title-font;
      font-weight: $title-font-weight;
      color: $secondary-font-color;
      font-size: 48px;
      margin-top: 24px;
      margin-bottom: 22px;
  }

  .scroll-box {
    overflow-y: auto;
    padding-left: 2px;
    padding-top: 5px;
    padding-bottom: 240px;
    margin-top: -10px;
    position: absolute;
    z-index: 2
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
  transition: all .5s ease;
}
.slide-up-fade-leave-active {
  transition: all .8s cubic-bezier(1.0, 0.5, 0.8, 1.0);
}
.slide-up-fade-enter, .slide-up-fade-leave-to
/* .slide-fade-leave-active below version 2.1.8 */ {
  transform: translateY(1000px);
  opacity: 0;
}
</style>
