<template>
  <demographics-chart
    :chartData="formatChartData()">
  </demographics-chart>
</template>

<script>
  import DemographicsChart from './DemographicsChart.vue'

  export default {
    name: 'AgeDemographics',

    components: {
      DemographicsChart
    },

    props: ['lessThanTen','tenToNineteen','twentyToTwentynine','thirtyToThirtynine','fourtyToFourtynine','fiftyToFiftynine','sixtyToSixtynine','seventyToSeventynine','eightyAndUp'],

    methods: {
      formatChartData() {
        return {
          labels: ['<10', '10-19', '20-29', '30-39', '40-49', '50-59', '60-69', '70-79', '>=80'],
          datasets: [{
            label: 'Age Demographics',
            pointBackgroundColor: 'white',
            borderWidth: 1,
            backgroundColor: [
              'rgba(65, 184, 131, .8)',
              'rgba(228, 102, 81, .8)',
              'rgba(0, 216, 255, .8)',
              'rgba(0, 216, 255, .8)',
              'rgba(0, 216, 255, .8)',
              'rgba(0, 216, 255, .8)',
              'rgba(155, 89, 182, .8)',
              'rgba(155, 89, 182, .8)',
              'rgba(155, 89, 182, .8)'
            ],
            data: [parseFloat(this.lessThanTen), parseFloat(this.tenToNineteen),parseFloat(this.twentyToTwentynine), parseFloat(this.thirtyToThirtynine), parseFloat(this.fourtyToFourtynine), parseFloat(this.fiftyToFiftynine),parseFloat(this.sixtyToSixtynine), parseFloat(this.seventyToSeventynine) ,parseFloat(this.eightyAndUp)]
          }
        ]}
      }
    }
  }
</script>









