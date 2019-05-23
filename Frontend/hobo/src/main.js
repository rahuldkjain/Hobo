import Vue from 'vue'
import App from './App.vue'
import VueRouter from 'vue-router'
import router from './router.js'
import Vuex from 'vuex'
import store from './store/store.js'
import BootstrapVue from 'bootstrap-vue'
import 'bootstrap/dist/css/bootstrap.css'
import 'bootstrap-vue/dist/bootstrap-vue.css'
import StarRating from 'vue-star-rating'

// Vue.config.productionTip = false

Vue.use(VueRouter)
Vue.use(Vuex)
Vue.use(BootstrapVue)
Vue.use(StarRating)

new Vue({
  router,
  store,
  StarRating,
  render: h => h(App)
}).$mount('#app')
