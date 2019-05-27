<template>
  <div id="app">
    <div id="nav">
      <!-- <NavbarMerchant fixed="top"/> -->
      <Navbar fixed="top"/>
      
    </div>
    <router-view/>
  </div>
</template>

<script>
import Navbar from './components/Navbar.vue'
import NavbarMerchant from './components/NavbarMerchant.vue'
import { appendFile } from 'fs';
import {mapGetters, mapActions} from 'vuex';
export default {
  name: 'app',
  components: {
    Navbar,
    NavbarMerchant
  },
  computed: {
    ...mapGetters(['getMerchant'])
  },
  methods: {
    
  }
}
</script>


<style>
#app {
  font-family: 'Avenir', Helvetica, Arial, sans-serif;
  -webkit-font-smoothing: antialiased;
  -moz-osx-font-smoothing: grayscale;
  text-align: center;
  color: #2c3e50;
}
/* #nav {
  padding: 30px;
} */

#nav a {
  padding-top: 10px;
  font-weight: bold;
  color: #2c3e50;
}

#nav a.router-link-exact-active {
  color: #42b983;
}
</style>
