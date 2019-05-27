<template>

    <b-card bg-variant="light">
        <div v-if="!userLoggedIn">
            <b-row id="products" v-for="(product,index) in getCartProduct" :key="index">
                <div class="cartItem" id="getCartProductId[index]">
                    <img :src='getCartImage[index]'>
                    <div class="head">
                        <h3> name: {{product}}</h3>
                        <h3> price: {{getCartProductPrice[index] ? getCartProductPrice[index] : '' }}</h3>

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
                        <b-button @click="removeCartItem(getCartProductId[index],index)" variant="danger">Remove Item</b-button>
                    </div>
                </div>
            </b-row>
        </div>
        <div v-else>
            <b-row id="products" v-for="(product,index) in getCartProduct" :key="index">
                <div class="cartItem" id="getCartProductId[index]">
                    <img :src='getCartImage[index]'>
                    <div class="head">
                        <h3> name: {{product.productName}}</h3>
                        <h3> price: {{product.productPrice}}</h3>

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
        </div>
        
        
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
            quantity: [],
            total: [],
            totalAmount: 0,
            orderDetails: [],
            userLoggedIn: false,
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
                    }
                })
                this.forceRerender();
            }
            else{
                this.$store.dispatch('removeCartItem', this.getCartProduct[index].cartItemId)
                this.forceRerender()
            }
        },
        forceRerender() {
           
            window.location.reload()
        },
        checkoutFunction() {
            // console.log("quantity on checkout "+this.quantity)
            // console.log("price "+this.getCartProductPrice)

            this.$store.dispatch('cartQuantity',this.quantity)

            if(!this.userLoggedIn){
                for(var i=0;i<this.getCartQuantity.length;i++){
                var totalPrice = this.getCartQuantity[i]*this.getCartProductPrice[i]
                this.total.push(totalPrice)
                }
            }
            else{
                for(var i=0;i<this.getCartQuantity.length;i++){
                var totalPrice = this.getCartQuantity[i]*this.getCartProduct[i].productPrice
                this.total.push(totalPrice)
                }
            }
            
            
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
                dummyProduct["pid"] = this.getCartProduct[index].productId
                dummyProduct["pname"] = this.getCartProduct[index].productName
                dummyProduct["price"] = this.getCartProduct[index].productPrice
                dummyProduct["image"] = this.getCartProduct[index].productImage
                dummyProduct["merchantId"] = this.getCartProduct[index].merchantId

                this.orderDetails.push(dummyProduct)
            }
           }
            else{
                for(var index=0;index<this.getCartQuantity.length;index++){
                    var dummyProduct = {}
                    dummyProduct["userId"] = 0
                    dummyProduct["pid"] = this.getCartProductId[index]
                    dummyProduct["pname"] = this.getCartProduct[index]
                    dummyProduct["price"] = this.getCartProductPrice[index]
                    dummyProduct["image"] = this.getCartImage[index]
                    dummyProduct["merchantId"] = this.getCartProductMerchantId[index]

                    this.orderDetails.push(dummyProduct)
                }
            }

            sessionStorage.setItem("orderDetails",JSON.stringify(this.orderDetails))

            this.$router.push('/checkout')
        }

    },
    computed: {
        ...mapGetters(['getLoggedIn','getCartProduct','getProductDetails','getCartImage', 'getCartProductPrice', 'getCartProductId','getCartQuantity','getCartProductMerchantId'])
    },
    components: {
        Quantity
    },
    watch: {
        getCartProduct: function(newValue, oldValue){
            console.log("getCartProduct: " + this.getCartProduct)
            //window.location.reload()
        }
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
                this.$store.dispatch('cartProductPrice', pid)

            })
            }
        else{
            this.userLoggedIn = true
            var emailId = JSON.parse(localStorage.getItem("userDetails")).emailId
            this.$store.dispatch('cartItems', emailId)
            this.getCartProduct.forEach(product =>{
                this.$store.dispatch('cartProduct', product.productId)
                this.$store.dispatch('cartProductPrice', product.productId)
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


