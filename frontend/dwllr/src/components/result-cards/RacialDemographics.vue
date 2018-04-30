<template>
  <demographics-chart
    :chartData="formatChartData()">
  </demographics-chart>
</template>

<script>
  import DemographicsChart from './DemographicsChart.vue'

  export default {
    name: 'RacialDemographics',

    components: {
      DemographicsChart
    },

    props: ['whitePercentage','blackPercentage','nativeAmericanPercentage','asianPercentage','pacificIslanderPercentage','otherPercentage','twoOrMoreRacePercentage'],

    methods: {
      formatChartData() {
        return {
          labels: ['White', 'Black or African American', 'American Indian and Alaska Native', 'Asian', 'Native Hawaiian and Other Pacific Islander', 'Some Other Race', 'Two or More Races'],
          datasets: [{
            label: 'Racial Demographics',
            pointBackgroundColor: 'white',
            borderWidth: 1,
            backgroundColor: [
              '#53687E',
              '#8E9B90',
              '#C2B2B4',
              '#AFD2E9',
              '#FDE8E9',
              '#6B4E71',
              '#3A4454'
            ],
            data: [parseFloat(this.whitePercentage), parseFloat(this.blackPercentage), parseFloat(this.nativeAmericanPercentage), parseFloat(this.asianPercentage), parseFloat(this.pacificIslanderPercentage), parseFloat(this.otherPercentage), parseFloat(this.twoOrMoreRacePercentage)]
          }
        ]}
      }
    }
  }
</script>
