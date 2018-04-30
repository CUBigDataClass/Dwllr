<script>
  import {Doughnut, mixins} from 'vue-chartjs'

  export default {
    name: 'DemographicsChart',
    extends: Doughnut,
    mixins: [mixins.reactiveProp],
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

    watch: {
      'chartData': {
        handler(newData, oldData) {
          console.log(oldData);
          this.renderChart(this.chartData, this.options);
        }
      }
    },

    mounted () {
      //console.log(this.dataCollection)
      this.renderChart(this.dataCollection, this.options)
    }
  }
</script>
