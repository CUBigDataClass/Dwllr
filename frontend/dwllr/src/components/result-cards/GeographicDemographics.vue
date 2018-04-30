<template>
  <demographics-chart
    :chartData="formatChartData()">
  </demographics-chart>
</template>

<script>
  import DemographicsChart from './DemographicsChart.vue'

  export default {
    name: 'GeographicDemographics',

    components: {
      DemographicsChart
    },

    props: ['urbanPopulation', 'ruralPopulation'],

    methods: {
      formatChartData() {
        return {
          labels: ['Urban', 'Rural'],
          datasets: [{
            label: 'Geographic Demographics',
            pointBackgroundColor: 'white',
            borderWidth: 1,
            backgroundColor: [
              '#53687E',
              '#3A4454'
            ],
            data: [parseFloat(this.urbanPopulation), parseFloat(this.ruralPopulation)]
          }
        ]}
      }
    }
  }
</script>
