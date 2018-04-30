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
              'rgba(65, 184, 131, .8)',
              'rgba(228, 102, 81, .8)',
            ],
            data: [parseFloat(this.malePercentage), parseFloat(this.femalePercentage)]
          }
        ]}
      }
    }
  }
</script>