<template>
  <demographics-chart
    :chartData="formatChartData()">
  </demographics-chart>
</template>

<script>
  import DemographicsChart from './DemographicsChart.vue'

  export default {
    name: 'SexDemographics',

    components: {
      DemographicsChart
    },

    props: ['malePercentage', 'femalePercentage'],

    methods: {
      formatChartData() {
        return {
          labels: ['Male', 'Female'],
          datasets: [{
            label: 'Sex Demographics',
            pointBackgroundColor: 'white',
            borderWidth: 1,
            backgroundColor: [
              '#C2B2B4',
              '#6B4E71'
            ],
            data: [parseFloat(this.malePercentage), parseFloat(this.femalePercentage)]
          }
        ]}
      }
    }
  }
</script>
