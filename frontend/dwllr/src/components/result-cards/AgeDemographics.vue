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
              '#53687E',
              '#8E9B90',
              '#C2B2B4',
              '#AFD2E9',
              '#FDE8E9',
              '#6B4E71',
              '#3A4454',
              '#0B132B',
              '#2C302E'
            ],
            data: [parseFloat(this.lessThanTen), parseFloat(this.tenToNineteen),parseFloat(this.twentyToTwentynine), parseFloat(this.thirtyToThirtynine), parseFloat(this.fourtyToFourtynine), parseFloat(this.fiftyToFiftynine),parseFloat(this.sixtyToSixtynine), parseFloat(this.seventyToSeventynine) ,parseFloat(this.eightyAndUp)]
          }
        ]}
      }
    }
  }
</script>
