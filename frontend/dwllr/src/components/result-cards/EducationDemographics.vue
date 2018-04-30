<template>
  <demographics-chart
    :chartData="formatChartData()">
  </demographics-chart>
</template>

<script>
  import DemographicsChart from './DemographicsChart.vue'

  export default {
    name: 'EducationDemographics',

    components: {
      DemographicsChart
    },

    props: ['noHighSchool', 'highSchoolNoDiploma', 'highSchoolGraduate', 'someCollegeNoDegree', 'associatesDegree', 'bachelorsDegree', 'graduateDegree'],

    methods: {
      formatChartData() {
        return {
          labels: ['No High School', 'Some High School', 'High School Graduate', 'Some College', 'Associates Degree', 'Bachelors Degree', 'Graduate Degree'],
          datasets: [{
            label: 'Education Demographics',
            pointBackgroundColor: 'white',
            borderWidth: 1,
            backgroundColor: [
              'rgba(65, 184, 131, .8)',
              'rgba(228, 102, 81, .8)',
              'rgba(0, 216, 255, .8)',
              'rgba(0, 216, 255, .8)',
              'rgba(0, 216, 255, .8)',
              'rgba(0, 216, 255, .8)',
              'rgba(155, 89, 182, .8)'
            ],
            data: [parseFloat(this.noHighSchool),parseFloat(this.highSchoolNoDiploma),parseFloat(this.highSchoolGraduate),parseFloat(this.someCollegeNoDegree),parseFloat(this.associatesDegree),parseFloat(this.bachelorsDegree),parseFloat(this.graduateDegree)]
          }
        ]}
      }
    }
  }
</script>