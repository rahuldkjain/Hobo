<template>

    <b-card bg-variant="light">
        <b-row >
            <b-col>
            <h2>Product</h2>
            </b-col>
        </b-row>
        <b-row>
            <b-col>
                <img :src='getProduct.productImage'>
            </b-col>
            <b-col>
                <b-row>
                <span class="texttop"><b>Product name: </b>{{getProduct.productName}}</span>
                </b-row>
                
                <b-row>
                <span class="text"><b>Product Description:</b> {{getProduct.description}}</span>
                </b-row>
                <b-row>    
                <span class="text"><b>Product Price:</b> ₹ {{getProductDetails[selected] ? getProductDetails[selected].price : ''}}</span>
                </b-row>
                <b-row>
                <span class="text"><b>Product Attributes: </b>{{getProduct.attributes}}</span>
                </b-row>

            </b-col>
            <b-col >
              
                    <b-button class="button" @click="addToCart(getProduct.productId, getProductDetails[0].merchantId, getProductDetails[0].price, getProduct.productName, getProduct.productImage)" variant="primary">Add to Cart</b-button>
                    <b-button @click="directCheckout(getProduct.productId)" class="button" variant="success">Buy</b-button><br><br><br>
                    <span class="textmerchant">Merchant Details</span>
                    <br>
                    <b-form-select id="merchantDetails" v-model="selected">
                            <option :value="null">Select Merchant </option>
                            <option :value="index" v-for="(product,index) in getProductDetails" :key="index" row="text">>{{product.merchantName}}</option>
                        
                    </b-form-select>

        
               
            </b-col>
        </b-row>
    </b-card>
    <!-- <h1> HELLOO </h1> -->
</template>
<script>
import {mapGetters, mapActions} from 'vuex'; 
export default {
    name: 'ProductDetails',
    data() {
        return {
            product: {
                
                    image: 'https://i.imgur.com/VgoUWI5.jpg',
                    name: 'product 1',
                    price: '15000'
                },
                selected: 0,
            
            
        }
    },
    created() {
           this.$store.dispatch('productPage', this.$route.params.id)
           this.$store.dispatch('productDetails', this.$route.params.id)
    },
    computed: {
        ...mapGetters(['getProduct','getProductDetails','getLoggedIn', 'getBuyNowProductId', 'getBuyNowProductPrice', 'getUserCartItem'])
    },
    methods: {
        addToCart(pid, merchantId, productPrice, productName, productImage) {
            
            if(localStorage.getItem("loggedIn") == "false"){
                // console.log("not logged in")
                var product_number = sessionStorage.length + 1
                var productValues = {'pid':pid}
                sessionStorage.setItem('product' + product_number, JSON.stringify(productValues))
                alert("the item is added")
            }
            else{
                var userDetails = JSON.parse(localStorage.getItem("userDetails"))
                var payload = {
                        'userEmail': userDetails.emailId,
                        'productId': pid,
                        'merchantId': merchantId,
                        'quantity': 1,
                        'productPrice': productPrice,
                        'productName': productName,
                        'productImage': productImage
                }
                this.$store.dispatch('addItemToCart', payload)
            }
        },
        directCheckout(pid) {
            if(this.getLoggedIn == false){
                // console.log("not logged in")
                var product_number = sessionStorage.length + 1
                var productValues = {'pid':pid}
                sessionStorage.setItem('product' + product_number, JSON.stringify(productValues))
                alert("direct checkout")

                this.$store.dispatch('buyNowProduct', pid)
                this.$store.dispatch('buyNowProductPrice', pid)

            }
            else{
                
            }
        }
    },
    watch: {
        getBuyNowProductPrice: function(newValue, oldValue) {
                console.log('buyNowProductPrice:', newValue)
            
            //order is placed!
            this.$router.push("/checkout");
        },
        getUserCartItem: function(newValue, oldValue){
            alert("Item added to cart")
        }
    },

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