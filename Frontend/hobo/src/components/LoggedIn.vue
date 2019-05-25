<template>
<div>
    <div v-if="checkLogIn">
        <b-navbar-nav>
            <b-nav-item class="login"><router-link to="/profile"> {{userDetails.name}}</router-link></b-nav-item>
            <b-nav-item class="login"><router-link to="/cart"><img class="cartIcon" src="https://i.imgur.com/8zfcGiW.png"></router-link></b-nav-item>
        
            <b-button @click="logoutFunction" class="login">Logout</b-button>
        </b-navbar-nav>
    </div>
    <div v-else>
        <b-navbar-nav>
            <b-nav-item class="login"><router-link to="/login">Login</router-link></b-nav-item>
            <b-nav-item class="login"><router-link to="/cart"><img class="cartIcon" src="https://i.imgur.com/8zfcGiW.png"></router-link></b-nav-item>
        </b-navbar-nav>
    </div>
</div>
</template>
<script>
import {mapGetters, mapActions} from 'vuex';
export default {
    name: 'LoggedIn',
    data() {
        return {
        checkLogIn: false,
        userDetails: {}
        }
    },
    methods: {
        logoutFunction: function() {
            console.log("in logout")
            this.checkLogIn = false
            this.userDetails = {}
            localStorage.setItem("loggedIn",false)
            localStorage.removeItem("userDetails")
            this.$store.dispatch('checkLogout')

            window.location.assign("/")
            // this.$router.push('/')
        }
    },
    computed: {
        ...mapGetters(['getLoggedIn','getUser','getMerchant'])
        
    },

watch: {
    //  console.log(this.$store.getters.getLoggedIn);
    getLoggedIn : function(newVal,oldVal){
        console.log("new value " + newVal);
        
        window.location.reload()
        
    }

},
mounted() {
   
    this.checkLogIn = localStorage.getItem("loggedIn")  
    this.userDetails = JSON.parse(localStorage.getItem("userDetails"))
}
}
</script>
<style scoped>
.cartIcon{
    height: 50px;
    width: 50px;
}
@media screen and (max-width: 2000px) {
    .login{
        font-size: 25px;
        text-align: left;
        padding-left:5%;
    }
}
@media screen and (max-width: 700px) {
    .login{
        text-align: left;
        padding-left:0;
    }
}
</style>


