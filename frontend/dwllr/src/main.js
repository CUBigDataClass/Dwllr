import Vue from 'vue'
import App from './App.vue'
import * as VueGoogleMaps from 'vue2-google-maps'

Vue.config.productionTip = false

Vue.use(VueGoogleMaps, {
  installComponents: true,
  load: {
    key: 'AIzaSyCYZTGHQL9tayIK6vzOl2evSVynVC41rLo', // yea i'm commiting this #yolo
    libraries: 'places'
  }
});

new Vue({
  render: h => h(App)
}).$mount('#app')
