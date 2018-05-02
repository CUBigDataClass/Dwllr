<template>
  <div id="search-by-attr-result">
    <h1>{{ name }}</h1>
    <h2>{{ zip }}</h2>
  </div>
</template>

<script>

export default {
  name: 'SearchByAttrResult'

  props: ['zip'],

  data () {
    return {
      name: '...'
    }
  }
}
</script>

<style>
</style>
