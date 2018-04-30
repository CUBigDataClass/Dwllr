<script>
  import {Doughnut, mixins} from 'vue-chartjs'

  export default {
    name: 'DemographicsChart',
    extends: Doughnut,
    props: ['chartData'],

    data() {
      return {
        dataCollection: this.chartData,
        options: {
          legend: {
            display: true
          },
          responsive: true,
          maintainAspectRatio: false
        }
      }
    },
    mounted () {
      console.log(this.dataCollection)
      this.renderChart(this.dataCollection, this.options)
    }
  }
</script>