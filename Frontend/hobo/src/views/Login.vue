<template>
  <div >
      
      <br>
      <b-row>
          
            <b-col sm="6">
            <b-card bg-variant="light" class="text-center cardlogin">
                <h4>Login</h4>
                <b-form @submit="onLogin" v-if="show">
                <b-form-group
                    id="login-email"
                    label="Email address:"
                    label-for="login-email-box">
    
                    <b-form-input
                        size="4"  
                        id="login-email-box"
                        v-model="form.loginemail"
                        type="email"
                        required
                        placeholder="Enter email">
                    </b-form-input>

            </b-form-group>

            <b-form-group id="login-password" label="Your Password:" label-for="login-password-box">
                <b-form-input
                id="login-password-box"
                type="password"
                v-model="form.loginpassword"
                required
                placeholder="Enter password">
            </b-form-input>
            </b-form-group>

      

        <b-button variant="primary" @click="onLogin">Login</b-button>
        <!-- <b-button type="reset" variant="danger">Reset</b-button> -->
        </b-form>
      </b-card>

        <!-- {{getLoggedIn}}
        <b-card class="mt-3" header="Form Data Result">
        <pre class="m-0">{{ form }}</pre>
        </b-card> -->
      </b-col>
        
          
      <b-col sm="5">
      <b-card bg-variant="light" class="text-center cardsignup">
          <h4>Signup</h4>
        <b-form @submit="onSignup" v-if="show">
             <b-form-group
                id="signup-name"
                label="Name:"
                label-for="signup-name-box">
    
            <b-form-input
                size="4"  
                id="signup-name-box"
                v-model="form.signupname"
                type="text"
                minlength="3"
                required
                placeholder="Enter name">
            </b-form-input>

        </b-form-group>

        <!-- <b-form-invalid-feedback :state="nameState">
            Enter at least 3 characters
        </b-form-invalid-feedback> -->
        <b-form-group
                id="signup-phone"
                label="Phone Number:"
                label-for="signup-phone-box">
    
            <b-form-input
                size="4"  
                id="signup-phone-box"
                v-model="form.signupphone"
                type="tel"
                pattern='[0-9]{10}'
                required
                placeholder="Enter Phone">
            </b-form-input>

        </b-form-group>
        <b-form-group
                id="signup-dob"
                label="Date Of Birth:"
                label-for="signup-dob-box">
    
            <b-form-input
                size="4"  
                id="signup-dob-box"
                v-model="form.signupdob"
                type="date"
                value="dd/mm/yy"
                required
                placeholder="Enter date of birth">
            </b-form-input>

        </b-form-group>            
            <b-form-group
                id="signup-email"
                label="Email address:"
                label-for="signup-email-box">
    
            <b-form-input
                size="4"  
                id="signup-email-box"
                v-model="form.signupemail"
                type="email"
                required
                placeholder="Enter email">
            </b-form-input>

        </b-form-group>

      <b-form-group id="signup-password" label="Your Password:" label-for="signup-password-box">
        <b-form-input
          id="signup-password-box"
          type="password"
          v-model="form.signuppswd"
          required
          placeholder="Enter Password"
        ></b-form-input>
      </b-form-group>

      

      <b-button type="submit" variant="primary">Submit</b-button>
      <!-- <b-button type="reset" variant="danger">Reset</b-button> -->
    </b-form>
      </b-card>

    
      </b-col>
          
      </b-row>
  </div>
</template>

<script>
import Navbar from '@/components/Navbar.vue';
import {mapGetters, mapActions} from 'vuex';
import { setTimeout } from 'timers';
  export default {
    data() {
      return {
        form: {
          loginemail: '',
          loginpassword: '',
          signupemail:'',
          signupname:'',
          signupphone:'',
          signupdob:''
        },
        logIn: false,
        show: true
      }
    },
    props: {
        
    },
    computed: {
     ...mapGetters(['getLoggedIn', 'getUser']),

    },
    methods: {
      onLogin() {
        var userDetails = { 'emailId': this.form.loginemail,'password': this.form.loginpassword}
        console.log(userDetails)
        this.$store.dispatch('checkLogin',userDetails) 

      },
      onSignup(evt) {
          evt.preventDefault()
        alert(JSON.stringify(this.form))
        this.$router.push("/")
      }
    },
    watch : {
        getLoggedIn: function(newValue, oldValue) {
            console.log('****:', newValue)
            
            localStorage.setItem("loggedIn", newValue)
            var userDetails = JSON.stringify(this.getUser)
            localStorage.setItem("userDetails",userDetails)

            // JSON.parse(localStorage.getItem('userDetails'))
            window.location.assign("/")
            this.$router.push("/")
        }
    },
    components: {
        Navbar
    }
  }
</script>
<style scoped>
.cardlogin{
    margin-left: 10%;
    margin-right: 10%;
}
.cardsignup{
    margin-left:0;
}
</style>


