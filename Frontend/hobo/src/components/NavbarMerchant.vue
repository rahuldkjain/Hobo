<template>
    <div>
  <b-navbar toggleable="lg" type="dark" variant="info">
    <b-navbar-brand href="/"><h2>Hobo</h2></b-navbar-brand>

    <b-navbar-toggle target="nav-collapse"></b-navbar-toggle>

    <b-collapse id="nav-collapse" is-nav>
      <b-navbar-nav>
          
      </b-navbar-nav>

      <!-- Right aligned nav items -->
      <b-navbar-nav class="ml-auto">
        
        
      </b-navbar-nav>
      <b-navbar-nav>
        <b-nav-item class="login"><router-link to="/addproduct">Add Product</router-link></b-nav-item>
        <b-nav-item class="login" href="/">Logout</b-nav-item>
        
      </b-navbar-nav>
    </b-collapse>
  </b-navbar>
</div>
</template>
<script>
import { METHODS } from 'http';
import LoggedIn from '@/components/LoggedIn.vue';
export default {
    name: 'NavbarMerchant',
    data() {
        return {
            searchValue: ''
        }
    },
    props: {
        fixed: String
       
    },
    methods: {
        myFunc() {
            console.log(this.searchValue);
        }
    },
    components: {
        LoggedIn
    }
}
</script>
<style scoped>
#nav a .category {
    padding-top: 0;
}
/* .login {
    padding-left:100px;
} */
@media screen and (max-width: 2000px) {
    .login{
        font-size: 25px;
        
        padding-left:5%;
    }
}
@media screen and (max-width: 700px) {
    .login{
       
        padding-left:0;
    }
}

</style>


