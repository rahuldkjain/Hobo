<template>
    <div>
  <b-navbar toggleable="lg" type="dark" variant="info">
    <b-navbar-brand href="/">Hobo</b-navbar-brand>

    <b-navbar-toggle target="nav-collapse"></b-navbar-toggle>

    <b-collapse id="nav-collapse" is-nav>
      <b-navbar-nav>
          <!-- <router-link to="/">Home</router-link> -->
          <!-- <router-view/> -->
        <!-- <b-nav-item href="#">Link</b-nav-item> -->

        <b-link href="/category" class="category">
            <b-nav-item-dropdown text="All Categories" class="dropdown" center fluid>
                <b-dropdown-item href="#">EN</b-dropdown-item>
                <b-dropdown-item href="#">ES</b-dropdown-item>
                <b-dropdown-item href="#">RU</b-dropdown-item>
                <b-dropdown-item href="#">FA</b-dropdown-item>
            </b-nav-item-dropdown>
        </b-link>
        <!-- <b-nav-item href="#" disabled>Disabled</b-nav-item> -->
      </b-navbar-nav>

      <!-- Right aligned nav items -->
      <b-navbar-nav class="ml-auto">
        <b-nav-form>
          <b-form-input size="sm" class="mr-sm-2" v-model="searchValue" placeholder="Search" @input="myFunc()"></b-form-input>
          <b-button size="sm" class="my-2 my-sm-0" type="submit">Search</b-button>
        </b-nav-form>
        
      </b-navbar-nav>
      <b-navbar-nav>
        <LoggedIn/>
        
      </b-navbar-nav>
    </b-collapse>
  </b-navbar>
</div>
</template>
<script>
import { METHODS } from 'http';
import LoggedIn from '@/components/LoggedIn.vue';
export default {
    name: 'Navbar',
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


</style>


