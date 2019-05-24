<template>
<b-row>
<b-col sm="6">
    <b-card bg-variant="light" class="text-center formcard">
                <h4>Shipping Details</h4>
                
                <b-form @submit="onSubmit" v-if="show">
                <b-form-group
                    id="email"
                    label="Email address:"
                    label-for="email-box">
    
                    <b-form-input
                        size="4"  
                        id="email-box"
                        v-model="form.email"
                        type="email"
                        required
                        placeholder="Enter email">
                    </b-form-input>

            </b-form-group>

            <b-form-group id="password" label="Your Password:" label-for="password-box">
                <b-form-input
                id="password-box"
                type="password"
                v-model="form.password"
                required
                placeholder="Enter password">
            </b-form-input>
            </b-form-group>
            <b-form-group id="name" label="Your Name:" label-for="name-box">
                <b-form-input
                id="name-box"
                type="text"
                v-model="form.name"
                required
                placeholder="Enter name">
            </b-form-input>
            </b-form-group>
            <b-form-group id="address" label="Your Address:" label-for="address-box">
                <b-form-input
                id="address-box"
                type="text"
                v-model="form.address"
                required
                placeholder="Enter Address">
            </b-form-input>
            </b-form-group>
            <b-form-group id="phone" label="Your Phone No:" label-for="phone-box">
                <b-form-input
                id="phone-box"
                type="text"
                v-model="form.phone"
                required
                placeholder="Enter phone no">
            </b-form-input>
            </b-form-group>

      

        <b-button type="submit" variant="primary" class="checkoutButton">Submit</b-button>
        <!-- <b-button type="reset" variant="danger">Reset</b-button> -->
        </b-form>
               
      </b-card>
       </b-col>
       <b-col>
        <ProductDetailsShipping/>
        </b-col>
</b-row>
</template>
<script>
import ProductDetails from '@/components/ProductDetails';
import ProductDetailsShipping from '@/components/ProductDetailsShipping';
export default {
    data() {
      return {
        form: {
          email: '',
          password: '',
          name: '',
          address:''
          
        },
        logIn: false,
        show: true
      }
    },
    methods: {
        onSubmit(evt) {
             evt.preventDefault();
        alert(JSON.stringify(this.form));
        console.log(this.$store.getters.getLoggedIn);
        this.$router.push("/success");
        }
    },
    components: {
        ProductDetails,
        ProductDetailsShipping
    }

}
</script>
<style scoped>

.formcard{
    margin: 10%;
    margin-right: 0;
}

</style>