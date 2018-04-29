<template>
  <demographics-chart
    :chartData="formatChartData()"
    :options="{}">
  </demographics-chart>
</template>

<script>
  import DemographicsChart from './DemographicsChart.vue'

  export default {
    name: 'Racial Demographics',

    components: {
      DemographicsChart
    },

    props: ['whitePercentage','blackPercentage','nativeAmericanPercentage','asianPercentage','pacificIslanderPercentage','otherPercentage','twoOrMoreRacePercentage'],

    methods: {
      formatChartData() {
        return {
          labels: ['White', 'Black or African American', 'American Indian and Alaska Native', 'Asian', 'Native Hawaiian and Other Pacific Islander', 'Some Other Race', 'Two or More Races'],
          datasets: {
            backgroundColor: [
              'rgba(65, 184, 131, .8)',
              'rgba(228, 102, 81, .8)',
              'rgba(0, 216, 255, .8)',
              'rgba(0, 216, 255, .8)',
              'rgba(0, 216, 255, .8)',
              'rgba(0, 216, 255, .8)',
              'rgba(155, 89, 182, .8)'
            ],
            data: [parseFloat(this.whitePercentage), parseFloat(this.blackPercentage), parseFloat(this.nativeAmericanPercentage), parseFloat(this.asianPercentage), parseFloat(this.pacificIslanderPercentage), parseFloat(this.otherPercentage), parseFloat(this.twoOrMoreRacePercentage)]
          }
        }
      }
    }
  }
</script>