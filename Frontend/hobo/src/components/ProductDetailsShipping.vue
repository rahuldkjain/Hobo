<template>

    <b-card bg-variant="light">
        <div v-if="userLoggedIn">
            <div v-if="!buyNow">
                <b-row id="products" v-for="(product,index) in getCartProduct" :key="index">
                    <div class="cartItem" id="getCartProductId[index]">
                        <img :src='product.productImage'>
                        <div class="head">
                            <h4> Name: {{product.productName}}</h4>
                            <h4> price: {{product.productPrice}}</h4>
                            <h4>Quantity: {{getCartQuantity[index]}}</h4>
                        </div>
                        <div>
                            <!-- <Quantity/> -->
                            <h3> Total: {{getCartQuantity[index]*product.productPrice}}  </h3>
                        </div>
                    
                        
                    </div>
                </b-row>
            </div>
            <!-- <div v-else>
                
            </div> -->
        </div>
        <div v-else-if="!userLoggedIn">
            <div v-if="buyNow">
                <b-row id="products" v-for="(product,index) in getCartProduct" :key="index">
                    <div class="cartItem" id="getCartProductId[index]">
                        <img :src='getCartImage[index]'>
                        <div class="head">
                            <h4> Name: hi{{product}}</h4>
                            <h4> price: {{getCartProductPrice ? getCartProductPrice[index] : '' }}</h4>
                            <h4>Quantity: {{getCartQuantity[index]}}</h4>
                        </div>
                        <div>
                            <!-- <Quantity/> -->
                            <h3> Total: {{getCartQuantity[index]*getCartProductPrice[index]}}  </h3>
                        </div>
                    </div>
                </b-row>
            </div>
            <div v-else>
                <b-row id="products" >
                <div class="buyNowItem" id="getBuyNowProductId">
                    <img :src='getBuyNowProductImage'>
                    <div class="head">
                        <h4> Name: bn{{getBuyNowProduct}}</h4>
                        <h4> price: {{getBuyNowProductPrice ? getBuyNowProductPrice : '' }}</h4>
                        <h4>Quantity: {{getBuyNowProductQuantity}}</h4>
                    </div>
                    <div>
                        <!-- <Quantity/> -->
                        <h3> Total: {{getBuyNowProductQuantity*getBuyNowProductPrice}}  </h3>
                    </div>
                
                    
                </div>
            </b-row>
            </div>
        </div>
        <!-- <div v-if="!buyNow">
            <div v-if="!userLoggedIn">
                <b-row id="products" v-for="(product,index) in getCartProduct" :key="index">
                    <div class="cartItem" id="getCartProductId[index]">
                        <img :src='getCartImage[index]'>
                        <div class="head">
                            <h4> Name: hi{{product}}</h4>
                            <h4> price: {{getCartProductPrice ? getCartProductPrice[index] : '' }}</h4>
                            <h4>Quantity: {{getCartQuantity[index]}}</h4>
                        </div>
                        <div>
                            
                            <h3> Total: {{getCartQuantity[index]*getCartProductPrice[index]}}  </h3>
                        </div>
                    </div>
                </b-row>
            </div>

            <div v-else>
                <b-row id="products" v-for="(product,index) in getCartProduct" :key="index">
                    <div class="cartItem" id="getCartProductId[index]">
                        <img :src='product.productImage'>
                        <div class="head">
                            <h4> Name: {{product.productName}}</h4>
                            <h4> price: {{product.productPrice}}</h4>
                            <h4>Quantity: {{getCartQuantity[index]}}</h4>
                        </div>
                        <div>
                            
                            <h3> Total: {{getCartQuantity[index]*product.productPrice}}  </h3>
                        </div>
                    
                        
                    </div>
                </b-row>
            </div>
        </div>
        <div v-else>
            <div v-if="!userLoggedIn">
                <b-row id="products" >
                <div class="buyNowItem" id="getBuyNowProductId">
                    <img :src='getBuyNowProductImage'>
                    <div class="head">
                        <h4> Name: bn{{getBuyNowProduct}}</h4>
                        <h4> price: {{getBuyNowProductPrice ? getBuyNowProductPrice : '' }}</h4>
                        <h4>Quantity: {{getBuyNowProductQuantity}}</h4>
                    </div>
                    <div>
                        
                        <h3> Total: {{getBuyNowProductQuantity*getBuyNowProductPrice}}  </h3>
                    </div>
                
                    
                </div>
            </b-row>
            </div>

            <div v-else>
                <b-row id="products" v-for="(product,index) in getCartProduct" :key="index">
                    <div class="cartItem" id="getCartProductId[index]">
                        <img :src='product.productImage'>
                        <div class="head">
                            <h4> Name:bn {{product.productName}}</h4>
                            <h4> price: {{product.productPrice}}</h4>
                            <h4>Quantity: {{product.quantity}}</h4>
                        </div>
                        <div>
                            
                            <h3> Total: {{product.quantity*product.productPrice}}  </h3>
                        </div>
                    
                        
                    </div>
                </b-row>
            </div>
            
        </div> -->
        

        <div>
            <h2> TOTAL AMOUNT : {{getTotalAmount}} </h2>
                
        </div>
    </b-card>
    <!-- <h1> HELLOO </h1> -->
</template>
<script>
import {mapGetters, mapActions} from 'vuex'; 
export default {
    name: 'ProductDetailsShipping',
    data() {
        return {
            total: [],
            buyNow: false,
            userLoggedIn: false,
        }
    },
    computed: {
        ...mapGetters(['getProduct','getCartQuantity','getCartProductPrice','getCartProduct','getCartImage','getCartProductId','getTotalAmount', 'getBuyNowProduct', 'getBuyNowProductPrice','getBuyNowProductQuantity', 'getBuyNowProductImage', 'getBuyNowProductId', 'getBuyNowProductMerchantId'])
    },
    mounted(){
        sessionStorage.setItem("buyNow", false)
        this.userLoggedIn = localStorage.getItem("loggedIn")
        console.log("quantity on shipping page "+this.getCartQuantity)
        if(this.getBuyNowProduct){
            this.buyNow = true
            sessionStorage.setItem("buyNow", true)
            console.log("quantity on shipping page "+this.getBuyNowProduct)
            var Product = {}
            Product["userId"] = 0
            Product["pid"] = this.getBuyNowProductId
            Product["pname"] = this.getBuyNowProduct
            Product["price"] = this.getBuyNowProductPrice
            Product["image"] = this.getBuyNowProductImage
            Product["merchantId"] = this.getBuyNowProductMerchantId
            sessionStorage.setItem("orderDetails",JSON.stringify(Product))
        }
        else{
            console.log("user logged in"+this.userLoggedIn)
            if(!this.userLoggedIn){
                for(productPrice in this.total){
                    this.totalAmount += productPrice
                }
                console.log("total "+this.totalAmount)
            }
            else{
                for(product in this.getCartProduct){
                    this.totalAmount += product.productPrice * product.quantity
                }
                console.log("total" + this.totalAmount)
            }
            
        }
            
        
    },
    methods: {
        
    }

    }

</script>
<style scoped>
img{
    height:300px;
    margin:4%;
    float: left;
}
.head{
    margin-top:10%;
    margin-right:0;

}
.texttop{
    font-size: 20px;
    margin-top: 20%;
    margin-left:0
}
.text{
    font-size: 20px;
}
.button{
    margin-top:20%;
    margin-left: 5%;
    font-size: 20px;
}
.textmerchant{
   font-size: 20px;
}
.card{
    margin: 10%;
 
}
</style>