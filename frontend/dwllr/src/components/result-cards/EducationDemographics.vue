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
              '#53687E',
              '#8E9B90',
              '#C2B2B4',
              '#AFD2E9',
              '#FDE8E9',
              '#6B4E71',
              '#3A4454'
            ],
            data: [parseFloat(this.noHighSchool),parseFloat(this.highSchoolNoDiploma),parseFloat(this.highSchoolGraduate),parseFloat(this.someCollegeNoDegree),parseFloat(this.associatesDegree),parseFloat(this.bachelorsDegree),parseFloat(this.graduateDegree)]
          }
        ]}
      }
    }
  }
</script>
