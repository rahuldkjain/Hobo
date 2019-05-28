<template>

    <b-card bg-variant="light">
     
            <b-row id="products" v-for="(product,index) in cartItem" :key="index">
                <div class="cartItem" id="getCartProductId[index]">
                    <img :src='product.content.image[index]'>
                    <div class="head">
                        <h3> name: {{product.content.name}}</h3>
                        <h3> price: {{cartItemDetails[index] ? cartItemDetails[index].price : '' }}</h3>

                    </div>
                    <div class="quantity">
                        <!-- <Quantity/> -->

                        <b-form-group>
                            <b-form-select v-model="quantity[index]" class="mb-3">
                            <!-- <option :value="null">Quantity</option> -->
                            <option value="1">1</option>
                            <option value="2">2</option>
                            <option value="3">3</option>
                        </b-form-select>
                
                        </b-form-group>

                    </div>
                    <div class="button">
                        <b-button @click="removeCartItem(product.productId,index)" variant="danger">Remove Item</b-button>
                    </div>
                </div>
            </b-row>
       
        <!-- <div v-else>
            <b-row id="products" v-for="(product,index) in getCartProduct" :key="index">
                <div class="cartItem" id="getCartProductId[index]">
                    <img :src='getCartImage[index]'>
                    <div class="head">
                        <h3> name: {{product.productName}}</h3>
                        <h3> price: ₹ {{product.productPrice}}</h3>

                    </div>
                    <div class="quantity">
                        <Quantity/> -->

                        <!-- <b-form-group>
                         <b-form-select v-model="quantity[index]" class="mb-3">
                            <option :value="null">Quantity</option>
                            <option value="1">1</option>
                            <option value="2">2</option>
                            <option value="3">3</option>
                        </b-form-select>
                
                        </b-form-group>

                    </div>
                    <div class="button">
                        <b-button @click="removeCartItem(product.productId,index)" variant="danger">Remove Item</b-button>
                    </div>
                </div>
            </b-row>
        </div> -->
        
        
        <b-button class="success" @click="checkoutFunction" variant="danger">Checkout</b-button>
    </b-card>

</template>
<script>

import Quantity from '@/components/Quantity.vue'
import {mapGetters, mapActions} from 'vuex'; 
export default {
    name: 'CartItem',
    data() {
        return {
            quantity: ["1","1","1 "],
            total: [],
            totalAmount: 0,
            orderDetails: [],
            userLoggedIn: false,
            cartItem: [],
            cartItemDetails: [],
        }
    },
    methods:{
        removeCartItem: function(pid,index){
            if(localStorage.getItem("loggedIn") == "false"){
                var keys = Object.keys(sessionStorage)
                // console.log(keys)
                keys.forEach(key => {
                    if(JSON.parse(sessionStorage.getItem(key)).pid == pid){
                        sessionStorage.removeItem(key)
                        this.quantity.splice(index,1)
                        this.cartItem.splice(index,1)
                        this.cartItemDetails.splice(index,1)
                    }
                })
                // this.forceRerender();
            }
            else{
                this.$store.dispatch('removeCartItem', this.cartItem[index].content.cartItemId)
                // this.forceRerender()

                // remove from cartItemDetails too
            }
        },
        // forceRerender() {
           
        //     window.location.reload()
        // },
        checkoutFunction() {

            this.$store.dispatch('cartQuantity',this.quantity)

            // if(!this.userLoggedIn){
                for(var i=0;i<this.getCartQuantity.length;i++){
                    var totalPrice = this.quantity[i]*this.cartItemDetails[i].content.price
                    this.total.push(totalPrice)
                 }
            // }
            // else{
            //     for(var i=0;i<this.getCartQuantity.length;i++){
            //     var totalPrice = this.getCartQuantity[i]*this.getCartProduct[i].productPrice
            //     this.total.push(totalPrice)
            //     }
            // }
            
            
            this.total.forEach(price => {
                this.totalAmount += price
            })
            console.log(this.totalAmount)
            this.$store.dispatch('checkoutAmount',this.totalAmount)
            
            // add orderDetails to the session storage
           if(localStorage.getItem("loggedIn") == "true"){
               for(var index=0;index<this.getCartQuantity.length;index++){
                var dummyProduct = {}
                dummyProduct["userId"] = JSON.parse(localStorage.getItem("userDetails")).emailId
                dummyProduct["pid"] = this.cartItem[index].productId
                dummyProduct["pname"] = this.cartItem[index].content.productName
                dummyProduct["price"] = this.cartItemDetails[index].content.price
                dummyProduct["image"] = this.cartItem[index].content.image
                dummyProduct["merchantId"] = this.cartItemDetails[index].content.merchantId

                this.orderDetails.push(dummyProduct)
            }
           }
            else{
                for(var index=0;index<this.getCartQuantity.length;index++){
                    var dummyProduct = {}
                    dummyProduct["userId"] = 0
                    dummyProduct["pid"] = this.cartItem[index].productId
                    dummyProduct["pname"] = this.cartItem[index].content.productName
                    dummyProduct["price"] = this.cartItemDetails[index].content.price
                    dummyProduct["image"] = this.cartItem[index].content.image
                    dummyProduct["merchantId"] = this.cartItemDetails[index].content.merchantId
                    this.orderDetails.push(dummyProduct)
                }
            }

            sessionStorage.setItem("orderDetails",JSON.stringify(this.orderDetails))

            this.$router.push('/checkout')
        }

    },
    computed: {
        ...mapGetters(['getCartItems','getCartProductDetails','getLoggedIn','getCartProduct','getProductDetails','getCartImage', 'getCartProductPrice', 'getCartProductId','getCartQuantity','getCartProductMerchantId'])
    },
    components: {
        Quantity
    },
    watch: {
        getCartProduct: function(newValue, oldValue){
            console.log("getCartProduct: " + this.getCartProduct)
            //window.location.reload()
            this.cartItem = []
            newValue.forEach(value => {
                var payload = {
                    productId: value.productId,
                    content: value
                }
                this.cartItem.push(payload)
            })
        },
        getCartProductDetails: function(newValue, oldValue){
            this.cartItemDetails = []
            newValue.forEach(value => {
                var payload = {
                    productId: value.productId,
                    content: value
                }
                this.cartItemDetails.push(payload)
            })
        },
    },
    mounted() {
        console.log("in mounted ")
        if(localStorage.getItem("loggedIn") == "false"){
            var keys = Object.keys(sessionStorage)
            console.log(keys)
            keys.forEach(key => {
                var pid = JSON.parse(sessionStorage.getItem(key)).pid
                console.log("pid: " + pid)
                this.$store.dispatch('cartProduct', pid)
                this.$store.dispatch('cartProductDetails', pid)

            })
            }
        else{
            this.userLoggedIn = true
            var emailId = JSON.parse(localStorage.getItem("userDetails")).emailId
            this.$store.dispatch('cartItems', emailId)
            this.getCartProduct.forEach(product =>{
                this.$store.dispatch('cartProduct', product.productId)
                this.$store.dispatch('cartProductDetails', product.productId)
            })
        }
    }
}
</script>
<style scoped>
a{
    color:white;
}
a:hover{
    color:white;
    text-decoration: underline;
}
.cartItem {
    border: 2px solid grey;
    width:100%;
    padding:0;
    margin:2%;
}
img{
    height:300px;
    margin:2% 10%;
    float: left;
}
.head{
    margin-top:10%;
    margin-right:0;
    float:left;
}
.quantity{
    margin-top: 5%;
    margin-left: 5%;
    margin-right: 5%;
    float:left;
}
.button{
    margin-top: 10%;
    margin-left: 5%;
    margin-right: 5%;
}
.success{
    margin:3%;
    font-size:30px;
    color:white;
}
#products{
   
    margin-bottom: 0;
}
</style>


