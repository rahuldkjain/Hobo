<template>
    <div>
        <b-row>
        <b-col sm="7">
        <b-card bg-variant="light" class="text-center card">
            <b-row>
            <b-col>
                <h3 style="text-align:left">Personal Information</h3>
            </b-col>
            <b-col>
                    <b-button class="editButton" @click="editFunc">Edit</b-button>
                      
            </b-col>
            </b-row>
                <b-form  v-if="show">
                <b-form-group
                    id="email"
                    label="Email address:"
                    label-for="email-box">
                    <b-row>
                        <b-col sm="8">
                        <b-form-input
                        size="4"  
                        id="email-box"
                        v-model="form.email"
                        type="email"
                        :placeholder='form.email'>
                        </b-form-input>
                        </b-col>
                        
                    </b-row>
            </b-form-group>

            <b-form-group id="name" label="Your Name:" label-for="name-box">
                <b-row>
                    <b-col sm="8">
                    <b-form-input
                    id="name-box"
                    type="text"
                    v-model="form.name"
                    placeholder="Enter name">
                    </b-form-input>
                    </b-col>
                    
                </b-row>
            </b-form-group>
            <b-form-group id="password" label="Your Password:" label-for="password-box">
                <b-row>
                    <b-col sm="8">

                    <b-form-input
                    id="password-box"
                    type="text"
                    v-model="form.password"
                    placeholder="Enter Password">
                    </b-form-input>
                    </b-col>
                    
                </b-row>
            </b-form-group>
            <b-form-group id="gender" label="Gender:" label-for="gender-box">
                <b-row>
                    <b-col sm="8">

                    <b-form-input
                    id="gender-box"
                    type="text"
                    v-model="form.gender"
                    placeholder="Enter Gender">
                    </b-form-input>
                    </b-col>
                   
                </b-row>
            </b-form-group>
            <b-form-group id="dateOfBirth" label="Your DOB:" label-for="dob-box">
                <b-row>
                    <b-col sm="8">

                    <b-form-input
                    id="dob-box"
                    type="text"
                    v-model="form.dateOfBirth"
                    placeholder="YYYY-MM-DD">
                    </b-form-input>
                    </b-col>
                </b-row>
            </b-form-group>
            <b-form-group id="address1" label="Your Address1:" label-for="address1-box">
                <b-row>
                    <b-col sm="8">

                    <b-form-input
                    id="address1-box"
                    type="text"
                    v-model="form.address1"
                    placeholder="Enter Address1">
                    </b-form-input>
                    </b-col>
                    
                </b-row>
            </b-form-group>
            <b-form-group id="address2" label="Your Address2:" label-for="address2-box">
                <b-row>
                    <b-col sm="8">

                    <b-form-input
                    id="address2-box"
                    type="text"
                    v-model="form.address2"
                    placeholder="Enter Address2">
                    </b-form-input>
                    </b-col>
                    
                </b-row>
            </b-form-group>
            <b-form-group id="city" label="Your City:" label-for="city-box">
                <b-row>
                    <b-col sm="8">

                    <b-form-input
                    id="city-box"
                    type="text"
                    v-model="form.city"
                    placeholder="Enter City">
                    </b-form-input>
                    </b-col>
                    
                </b-row>
            </b-form-group>
            <b-form-group id="state" label="Your State:" label-for="state-box">
                <b-row>
                    <b-col sm="8">

                    <b-form-input
                    id="state-box"
                    type="text"
                    v-model="form.state"
                    placeholder="Enter State">
                    </b-form-input>
                    </b-col>
                    
                </b-row>
            </b-form-group>
            <b-form-group id="phone" label="Your Phone No:" label-for="phone-box">
                <b-row>
                    <b-col sm="8">

                    <b-form-input
                    id="phone-box"
                    type="text"
                    v-model="form.phone"
                    placeholder="Enter phone no">
                    </b-form-input>
                    </b-col>
                   
                </b-row>
            </b-form-group>
            <b-form-group id="pincode" label="Your Pincode:" label-for="pincode-box">
                <b-row>
                    <b-col sm="8">

                    <b-form-input
                    id="pincode-box"
                    type="text"
                    v-model="form.pincode"
                    placeholder="Enter Pincode">
                    </b-form-input>
                    </b-col>
                    
                </b-row>
            </b-form-group>

      

        <b-button @click="editProfile" variant="primary">Change</b-button>
        <!-- <b-button type="reset" variant="danger">Reset</b-button> -->
        </b-form>
               
      </b-card>
        </b-col>
        <b-col>
            <h3>Order History</h3>
        </b-col>
        </b-row>
    </div>
</template>
<script>
export default {
    data() {
        return {
            form: {
          email: 'mehak@gmail.com',
          name: 'mehak',
          address1:'abcd 24th main road 11th cross',
          phone:'123456',
            dateOfBirth: '',
            address2: '',
            city: '',
            state: '',
            pincode: ''
        },
            validated: true,
            show: true
        }
    },
    methods: {
        editFunc(obj) {
           
            this.validated = !this.validated
        },
        editProfile(){
            var payload = {
                'emailId': this.form.email,
                'name': this.form.name,
                'address1': this.form.address1,
                'phoneNumber': this.form.phone,
                'dateOfBirth': this.form.dateOfBirth,
                'address2': this.form.address2,
                'city': this.form.city,
                'state': this.form.state,
                'pincode': this.form.pincode,
                'password': this.form.password
            }
            this.$store.dispatch('changeUserDetails',payload)
        }
        
    },
    mounted(){
        var userDetails = JSON.parse(localStorage.getItem("userDetails"))
        this.form.email = userDetails.emailId
        this.form.name = userDetails.name
        this.form.address1 = userDetails.address1
        this.form.phone = userDetails.phoneNumber
        this.form.dateOfBirth = userDetails.dateOfBirth
        this.form.address2 = userDetails.address2
        this.form.city = userDetails.city
        this.form.state = userDetails.state
        this.form.pincode = userDetails.pincode
        this.form.password = userDetails.password
    }
}
</script>
<style scoped>
.card{
    margin: 5%
}
form{
    text-align: left;
}
</style>


