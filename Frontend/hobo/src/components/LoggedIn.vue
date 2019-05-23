<template>
<div>
    <div v-if="getLoggedIn">
        <b-navbar-nav>
            <b-nav-item class="login"><router-link to="/profile"> Profile</router-link></b-nav-item>
            <b-nav-item class="login"><router-link to="/cart"><img class="cartIcon" src="https://i.imgur.com/8zfcGiW.png"></router-link></b-nav-item>
        
            <b-nav-item href="/" class="login">Logout</b-nav-item>
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
    method: {
        
    },
    computed: {
        ...mapGetters(['getLoggedIn'])
        
    },
//     mounted: function () {
//   this.$nextTick(function () {
//     // Code that will run only after the
//     // entire view has been rendered
//     console.log(this.$store.getters.getLoggedIn);
//     localStorage.setItem('loggedInFlag',this.$store.getters.getLoggedIn);
//   })
//     },

watch: {
    //  console.log(this.$store.getters.getLoggedIn);
    getLoggedIn : function(newVal,oldVal){
        console.log("new value " + newVal);
    } 
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


