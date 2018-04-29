import Vue from 'vue'
import App from './App.vue'
import * as VueGoogleMaps from 'vue2-google-maps'

Vue.config.productionTip = false

Vue.use(VueGoogleMaps, {
  installComponents: true,
  load: {
    key: 'AIzaSyDUphyKwQ4lJqCpQjn8-F_FuGyTxjp7vV8', // yea i'm commiting this #yolo
    libraries: 'places'
  }
});

new Vue({
  render: h => h(App)
}).$mount('#app')
