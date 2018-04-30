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
              'rgba(65, 184, 131, .8)',
              'rgba(228, 102, 81, .8)'
            ],
            data: [parseFloat(this.urbanPopulation), parseFloat(this.ruralPopulation)]
          }
        ]}
      }
    }
  }
</script>