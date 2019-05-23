import Vue from 'vue'
import Vuex from 'vuex'
import module from './module.js'

Vue.use(Vuex)

export default new Vuex.Store({
    modules: {
        module
    }
})
