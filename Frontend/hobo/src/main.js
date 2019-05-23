import Vue from 'vue'
import App from './App.vue'
import VueRouter from 'vue-router'
import router from './router.js'
import Vuex from 'vuex'
import store from './store/store.js'
import BootstrapVue from 'bootstrap-vue'
import 'bootstrap/dist/css/bootstrap.css'
import 'bootstrap-vue/dist/bootstrap-vue.css'
import { library } from '@fortawesome/fontawesome-svg-core'
import { faCoffee } from '@fortawesome/free-solid-svg-icons'
import FontAwesomeIcon from '@fortawesome/vue-fontawesome'
// import store from './store'
// import Axios from 'axios'

// Vue.config.productionTip = false

Vue.use(VueRouter)
Vue.use(Vuex)
Vue.use(BootstrapVue)

library.add(faCoffee)

Vue.component('font-awesome-icon', FontAwesomeIcon)
new Vue({
  router,
  store,
  render: h => h(App)
}).$mount('#app')
