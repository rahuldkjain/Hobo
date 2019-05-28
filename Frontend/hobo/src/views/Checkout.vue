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
                        disabled
                        placeholder="Enter email">
                    </b-form-input>

            </b-form-group>

           
            <b-form-group id="name" label="Your Name:" label-for="name-box">
                <b-form-input
                id="name-box"
                type="text"
                v-model="form.name"
                disabled
                placeholder="Enter name">
            </b-form-input>
            </b-form-group>
            <b-form-group id="address1" label="Delivery Address:" label-for="address1-box">
                <b-form-input
                id="address1-box"
                type="text"
                v-model="form.address"
                placeholder="Enter Delivery Address">
            </b-form-input>
            </b-form-group>
            
            <b-form-group id="city" label="City" label-for="city-box">
                <b-form-input
                id="city-box"
                type="text"
                v-model="form.city"
                placeholder="Enter City">
            </b-form-input>
            </b-form-group>
            <b-form-group id="pincode" label="Pin Code" label-for="pincode-box">
                <b-form-input
                id="pincode-box"
                type="text"
                v-model="form.pincode"
                placeholder="Enter Pin Code">
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
import {mapGetters, mapActions} from 'vuex'; 
export default {
    data() {
      return {
        form: {
          email: '',
          name: '',
          address:'',
          city:'',
          pincode:'',
          phone:''
          
        },
        logIn: false,
        show: true
      }
    },
    mounted() {
        //console.log(localStorage.getItem("userDetails"))
        var userDetails = JSON.parse(localStorage.getItem("userDetails"))
        //console.log("user details "+userDetails)
        this.form.email = userDetails.emailId
        this.form.name = userDetails.name
        this.form.address = userDetails.address1
        this.form.city = userDetails.city
        this.form.pincode = userDetails.pincode
        this.form.phone = userDetails.phoneNumber
        //console.log(this.form.email)
    },
    methods: {
        onSubmit() {
        var today = new Date();
        var orderDate = today.getFullYear() + '-' + today.getMonth() + '-' +today.getDate()
        var deliveryDate = new Date(today)
        deliveryDate.setDate(deliveryDate.getDate()+4)
        deliveryDate = deliveryDate.getFullYear() + '-' + deliveryDate.getMonth() + '-' + deliveryDate.getDate()

        var guestDetails = {}
        if(localStorage.getItem("loggedIn") == 'true'){
             guestDetails["userId"] = JSON.parse(localStorage.getItem("userDetails")).emailId
        }
        else{
            guestDetails["userId"] = 0
        }
        
        guestDetails["userEmailId"] = this.form.email
        guestDetails["orderDate"] = orderDate
        guestDetails["deliveryDate"] = deliveryDate
        if(localStorage.getItem("loggedIn")){
            guestDetails["orderPrice"] = this.getTotalAmount
        }
        else{
            if(typeof(sessionStorage.getItem("orderDetails")) == "string"){
            guestDetails["orderPrice"] = JSON.parse(sessionStorage.getItem("orderDetails")).price
            }
            else{
                guestDetails["orderPrice"] = JSON.parse(sessionStorage.getItem("orderDetails"))[0].price
            }
        }
        
        guestDetails["address1"] = this.form.address1
        guestDetails["address2"] = this.form.address2
        guestDetails["city"] = this.form.city
        guestDetails["pincode"] = this.form.pincode

        console.log(guestDetails)

        alert(JSON.stringify(this.form));
        
        this.$store.dispatch("createOrder",guestDetails)
        
        // this.$router.push("/success");
        }
    },
    computed: {
        ...mapGetters(['getTotalAmount', 'getOrderProductNum','getOrderId','getCartQuantity','getOrder', 'getBuyNowProduct', 'getBuyNowProductQuantity'])
    },
    watch: {
        getOrderProductNum: function(newValue, oldValue){
            var payload ={}
            payload["orderId"] = this.getOrderId
            if(newValue!=this.getCartQuantity.length){
                var orderDetails = JSON.parse(sessionStorage.getItem("orderDetails"))[newValue]
                payload["productId"] = orderDetails["pid"]
                payload["merchantId"] = orderDetails["merchantId"]
                payload["quantity"] = this.getCartQuantity[newValue]
                payload["productPrice"] = orderDetails["price"]

                 this.$store.dispatch('createProductOrder',payload)
            }
            console.log("orderproduct payload: " + payload)
            //order is placed!
            this.$router.push("/success");
        },
        getOrderId: function(newValue, oldValue) {
                console.log('order:', newValue)
            
            
            var payload ={}
            payload["orderId"] = newValue
            sessionStorage.setItem("orderId", payload.orderId)
            console.log("buyNow: "+ sessionStorage.getItem("buyNow"))
            var orderDetails = JSON.parse(sessionStorage.getItem("orderDetails"))
            if(sessionStorage.getItem("buyNow") == "true"){
                payload["productId"] = orderDetails["pid"]
                payload["merchantId"] = orderDetails["merchantId"]
                payload["quantity"] = this.getBuyNowProductQuantity
                payload["productPrice"] = orderDetails["price"]

                this.$store.dispatch('createProductOrder',payload)
                console.log("orderproduct payload: " + payload)
                //order is placed!
             this.$router.push("/success");

            }else{
                
                    var orderDetails = JSON.parse(sessionStorage.getItem("orderDetails"))[0]
                    payload["productId"] = orderDetails["pid"]
                    payload["merchantId"] = orderDetails["merchantId"]
                    payload["quantity"] = this.getCartQuantity[0]
                    payload["productPrice"] = orderDetails["price"]

                    this.$store.dispatch('createProductOrder',payload)
                
                
            }
            
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