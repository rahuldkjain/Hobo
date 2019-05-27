<template>
<div>
    <div v-if="checkLogIn">
        <b-navbar-nav>
            <b-nav-item class="login"><b-link @click="getUserProfile(userDetails.emailId)"> {{userDetails.name}}</b-link></b-nav-item>

            <b-nav-item class="login"><router-link to="/cart"><img class="cartIcon" src="https://i.imgur.com/8zfcGiW.png"></router-link></b-nav-item>
        
            <b-button size="sm" @click="logoutFunction" class="login" variant="danger">Logout</b-button>
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
        },
        getUserProfile(emailid){
            this.$router.push('/profile/'+emailid)
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
    
    height: 40px;
    width: 50px;
}
@media screen and (max-width: 2000px) {
    .login{
        font-size: 15px;
        text-align: left;
        height: 40px;
    }
}
@media screen and (max-width: 700px) {
    .login{
        text-align: left;
       
    }
}
</style>


