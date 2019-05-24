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
                        disabled
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
                    :disabled="validated==1"
                    placeholder="Enter name">
                    </b-form-input>
                    </b-col>
                    
                </b-row>
            </b-form-group>
            <b-form-group id="address" label="Your Address:" label-for="address-box">
                <b-row>
                    <b-col sm="8">

                    <b-form-input
                    id="address-box"
                    type="text"
                    v-model="form.address"
                    :disabled="validated==1"
                    placeholder="Enter Address">
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
                    :disabled="validated==1"
                    placeholder="Enter phone no">
                    </b-form-input>
                    </b-col>
                   
                </b-row>
            </b-form-group>

      

        <b-button type="submit" variant="primary">Change</b-button>
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
          address:'abcd 24th main road 11th cross',
          phone:'123456'
          
        },
            validated: true,
            show: true
        }
    },
    methods: {
        editFunc(obj) {
           
            this.validated = !this.validated
        }
        
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


