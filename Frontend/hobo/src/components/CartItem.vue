<template>

    <b-card bg-variant="light">
     
            <b-row id="products" v-for="(product,index) in cartItem" :key="index">
                <div class="cartItem">
                    <img :src='product.content.productImage'>
                    <div class="head">
                        <h3> name: {{product.content.productName}}</h3>

                        <h3 v-if="userLoggedIn"> price: {{product? product.content.productPrice : '' }}</h3>
                        <h3 v-if="!userLoggedIn"> price: {{cartItemDetails[index] ? cartItemDetails[index].price : '' }}</h3>
                        
                        <h3> Stock: {{getProductDetailsList[index] ? getProductDetailsList[index].stock : ''}}</h3>
                    </div>
                    <div class="quantity">
                        <!-- <Quantity/> -->

                        <!-- <b-form-group>
                            <b-form-select v-model="quantity[index]" class="mb-3">
                            
                            <option value="1">1</option>
                            <option value="2">2</option>
                            <option value="3">3</option>
                        </b-form-select> 
                        </b-form-group> -->

                        <b-form>
                        <b-form-input
                        id="quantity"
                        type="number"
                        v-model="quantity[index]"
                        required
                        placeholder="Enter quantity" @input="quantityChange(index)">
                        </b-form-input>
                        </b-form>

                    </div>
                    <div class="button">
                        <b-button @click="removeCartItem(product.productId,index)" variant="danger">Remove Item</b-button>
                    </div>
                </div>
            </b-row>
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
            cartItem: [],
            cartItemDetails: [],
            flag: 0
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
               
            }
            else{
                this.$store.dispatch('removeCartItem', this.cartItem[index].content.cartItemId)
                
                // remove from cartItemDetails too
            }
        },
       
        checkoutFunction() {
            this.quantity.forEach(value => {
                if(value == 0){
                    this.flag=1
                }
            })
            if(this.flag==0){
            this.$store.dispatch('cartQuantity',this.quantity)

                // console.log("on clicking checkout"+JSON.stringify(this.cartItem))
                var index = 0
                var cartItemList = []
                this.cartItem.forEach(eachCartItem => {
                    var payload = {}
                    eachCartItem.content.quantity = this.quantity[index]
                    index++
                    payload = eachCartItem.content

                    this.$store.dispatch("addCartItemList",payload)
                    cartItemList.push(payload)
                })

                console.log("our cart Item list "+JSON.stringify(cartItemList))
                
                

                for(var i=0;i<this.getCartQuantity.length;i++){
                    if(this.userLoggedIn){
                        var totalPrice = this.quantity[i]*this.cartItem[i].content.productPrice
                    }
                    else{
                        var totalPrice = this.quantity[i]*this.cartItemDetails[i].content.price
                    }
                    this.total.push(totalPrice)
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
                dummyProduct["pid"] = this.cartItem[index].productId
                dummyProduct["pname"] = this.cartItem[index].content.productName
                dummyProduct["price"] = this.cartItem[index].content.productPrice
                dummyProduct["image"] = this.cartItem[index].content.image
                dummyProduct["merchantId"] = this.cartItem[index].content.merchantId

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
            else{
                alert("quantity cannot be 0")
            }
        },
        quantityChange(index) {
            console.log("in quantity change function ")
            if(this.getProductDetailsList){
            if(this.quantity[index] > this.getProductDetailsList[index].stock ){
                alert("quantity is exceeding the stock")
                this.quantity[index] = "1"
                }
            }
        }

    },
    computed: {
        ...mapGetters(['getCartItems','getCartProductDetails','getLoggedIn','getCartProduct','getProductDetails','getCartImage', 'getCartProductPrice', 'getCartProductId','getCartQuantity','getCartProductMerchantId','getProductDetailsList'])
    },
    components: {
        Quantity
    },
    watch: {
        getCartProduct: function(newValue, oldValue){
            // console.log("getCartProduct: " + this.getCartProduct)
            // let unique = [...new Set(newValue)]
            //window.location.reload()
            this.cartItem = []
            // console.log("in watch before for "+this.cartItem.length)
            newValue.forEach(value => {
                var payload = {
                    productId: value.productId,
                    content: value
                }
                this.cartItem.push(payload)
                this.quantity.push("1")
            })
            console.log("in watch"+JSON.stringify(this.cartItem))
            
            this.cartItem.forEach(eachCartItem => {
                this.$store.dispatch("productDetails",eachCartItem.content.productId)
                
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
        this.cartItem = []
        // console.log("in mounted ")
        if(localStorage.getItem("loggedIn") == "false"){
            var keys = Object.keys(sessionStorage)
            // console.log(keys)
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


