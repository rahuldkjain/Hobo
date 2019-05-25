<template>
<b-row>
<b-col sm="6">
    <b-card bg-variant="light" class="text-center formcard">
                <h4>Shipping Details</h4>
                
                <b-form v-if="show">
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

           
            <b-form-group id="name" label="Your Name:" label-for="name-box">
                <b-form-input
                id="name-box"
                type="text"
                v-model="form.name"
                required
                placeholder="Enter name">
            </b-form-input>
            </b-form-group>
            <b-form-group id="address1" label="Address 1:" label-for="address1-box">
                <b-form-input
                id="address1-box"
                type="text"
                v-model="form.address1"
                required
                placeholder="Enter Address1">
            </b-form-input>
            </b-form-group>
            <b-form-group id="address2" label="Address 2:" label-for="address2-box">
                <b-form-input
                id="address2-box"
                type="text"
                v-model="form.address2"
                required
                placeholder="Enter Address2">
            </b-form-input>
            </b-form-group>
            <b-form-group id="city" label="City" label-for="city-box">
                <b-form-input
                id="city-box"
                type="text"
                v-model="form.city"
                required
                placeholder="Enter City">
            </b-form-input>
            </b-form-group>
            <b-form-group id="pincode" label="Pin Code" label-for="pincode-box">
                <b-form-input
                id="pincode-box"
                type="text"
                v-model="form.pincode"
                required
                placeholder="Enter Pin Code">
            </b-form-input>
            </b-form-group>
            <!-- <b-form-group id="phone" label="Your Phone No:" label-for="phone-box">
                <b-form-input
                id="phone-box"
                type="text"
                v-model="form.phone"
                required
                placeholder="Enter phone no">
            </b-form-input>
            </b-form-group> -->

      

        <b-button variant="primary" @click="onSubmit" class="checkoutButton">Buy</b-button>
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
          address1:'',
          address2:'',
          city:'',
          pincode:''
          
        },
        logIn: false,
        show: true
      }
    },
    methods: {
        onSubmit(evt) {
             evt.preventDefault();
        var today = new Date();
        var orderDate = today.getDate() + '/' + today.getMonth() + '/' +today.getFullYear()
        var deliveryDate = new Date(today)
        deliveryDate.setDate(deliveryDate.getDate()+4)
        deliveryDate = deliveryDate.getDate() + '/' + deliveryDate.getMonth() + '/' + deliveryDate.getFullYear()

        var guestDetails = {}
        guestDetails["userId"] = 0
        guestDetails["userEmailId"] = this.form.email
        guestDetails["orderDate"] = orderDate
        guestDetails["deliveryDate"] = deliveryDate
        guestDetails["orderPrice"] = JSON.parse(sessionStorage.getItem("orderDetails"))[0].price
        guestDetails["address1"] = this.form.address1
        guestDetails["address2"] = this.form.address2
        guestDetails["city"] = this.form.city
        guestDetails["pincode"] = this.form.pincode

        console.log(guestDetails)

        alert(JSON.stringify(this.form));
        
        this.$store.dispatch("createOrder",guestDetails)
        
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