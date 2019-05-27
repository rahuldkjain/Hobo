<template>
    <div>
  <b-navbar toggleable="lg" type="dark" class="navbar">
    <b-navbar-brand class="brand"><b-link href="/"><h2>Hobo</h2></b-link></b-navbar-brand>
    <b-navbar-toggle target="nav-collapse"></b-navbar-toggle>
    <b-collapse id="nav-collapse" is-nav>
      <b-navbar-nav>
          <!-- <router-link to="/">Home</router-link> -->
          <!-- <router-view/> -->
        <!-- <b-nav-item href="#">Link</b-nav-item> -->
        <b-link href="/category" class="category">
            <b-nav-item-dropdown text="All Categories" class="dropdown" center fluid>
                <b-dropdown-item @click="goToCategory(3)">Clothing</b-dropdown-item>
                <b-dropdown-item @click="goToCategory(1)">Electronics</b-dropdown-item>
                <b-dropdown-item @click="goToCategory(2)">Sports</b-dropdown-item>
               
                <!-- <b-dropdown-item href="#">FA</b-dropdown-item> -->
            </b-nav-item-dropdown>
        </b-link>
        <!-- <b-nav-item href="#" disabled>Disabled</b-nav-item> -->
      </b-navbar-nav>
      <!-- Right aligned nav items -->
      <b-navbar-nav class="ml-auto">
        <b-nav-form>
          <!-- <b-form-input size="sm" class="mr-sm-2" v-model="searchValue" @input="searchFunc" placeholder="Search"> -->
            <!-- <Dropdown
            :options="options"
            name="dropdown"
            :maxItem="10"
            
            v-on:filter="getDropdownValues"
            placeholder="Search">
            </Dropdown> -->
    
                    <b-form-input
                        size="4"  
                        id="search"
                        v-model="searchQuery"
                        type="text"
                        placeholder="Type to search">
                    </b-form-input>

           
          <b-button size="sm" class="my-2 my-sm-0" @click="search" type="submit">Search</b-button>
        </b-nav-form>
        
        </b-navbar-nav>
      <b-navbar-nav>
        <LoggedIn/>
        <!-- <div v-if="getSearchData">
        {{getSearchData[0]}}
        </div>    -->
        
      </b-navbar-nav>
    </b-collapse>
  </b-navbar>
</div>
</template>
<script>
import { METHODS } from 'http';
import LoggedIn from '@/components/LoggedIn.vue';
import Dropdown from 'vue-simple-search-dropdown';
import {mapGetters, mapActions} from 'vuex';
import Vue from 'vue'
Vue.use(Dropdown);
export default {
    name: 'Navbar',
    data() {
        return {
            searchQuery: '',
            options: [{name: 'mehak'}],
            category: ''
        }
    },
    props: {
        fixed: String
       
    },
    computed: {
        ...mapGetters(['getSearchData']),
        
    },
    methods: {
        goToCategory(pid) {
          
          if(pid==1)
            this.category = "Electronics"
          else if(pid==2)
            this.category = "Sports"
          else
            this.category = "Clothing"
          this.$store.dispatch("categoryProducts",this.category)
          this.$router.push("/category/"+pid)
        },
        getDropdownValues(value) {
          console.log(value);
          this.searchValue = value;
          if(value.length>3){
                this.$store.dispatch('checkSearch',value)
            }
         
        },
        search(evt) {
          evt.preventDefault()

           this.$router.push("/search/"+this.searchQuery)
          // var query = this.searchQuery
          // console.log(query)
          // this.$store.dispatch("checkSearch",query)
        }
    },
    // watch: {
    //     getSearchData: function(newValue, oldValue) {
    //         console.log('search changed:', newValue)
            
    //         this.$router.push("/search/"+this.searchQuery)
    //     }
    // },
    
    components: {
        LoggedIn,
        Dropdown
    },
}
</script>
<style scoped>
#nav a .category {
    padding-top: 0;
}
.navbar{
  background-color: #FDCA40;
  
}

</style>