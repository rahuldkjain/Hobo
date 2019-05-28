<template>

    <b-card bg-variant="light">
        <b-row >
            <b-col>
            <h2>Product</h2>
            </b-col>
        </b-row>
        <b-row>
            <b-col>
                <img :src='product.image'>
            </b-col>
            <b-col>
                <b-row>
                <span class="texttop"><b>Product name: </b>{{product.pName}}</span>
                </b-row>
                
                <b-row>
                <span class="text"><b>Product Description:</b> {{product.description}}</span>
                </b-row>
                <b-row>    
                <span class="text"><b>Product Price:</b> ₹ {{product.merchants[selected].price}}</span>
                </b-row>
                <b-row>
                <span class="text"><b>Product Attributes: </b>{{product.attributes}}</span>
                </b-row>

            </b-col>
            <b-col >
              
                    <b-button class="button" @click="addToCart(product.merchants[selected].mId)" variant="primary">Add to Cart</b-button>
                    <b-button @click="directCheckout()" class="button" variant="success">Buy</b-button><br><br><br>
                    <span class="textmerchant">Merchant Details</span>
                    <br>
                    <b-form-select id="merchantDetails" v-model="selected">
                            <option :value="null">Select Merchant </option>
                            <option :value="index" v-for="(product,index) in product.merchants" :key="index" row="text">>{{product.mName}}</option>
                        
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
                    pName: '',
                    pId: '',
                    image: [],
                    description: '',
                    category: '',
                    subCategory: '',
                    brand: '',
                    merchants: [
                        // {
                        //     mId: null,
                        //     price: null,
                        //     mName: null
                        // }
                    ],
                    attributes: {

                    }
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
    watch: {
        getProduct: function(newValue, oldValue){
            this.product.pName = newValue.productName
            this.product.pId = newValue.productId
            this.product.image = newValue.productImage
            this.product.description = newValue.description
            this.product.category = newValue.category
            this.product.subCategory = newValue.subCategory
            this.product.attributes = newValue.attributes
            this.product.brand = newValue.productBrand
        },
        getProductDetails: function(newValue, oldValue){
            newValue.forEach(value => {
                var prodDetails = {
                    mId: value.merchantId,
                    price: value.price,
                    mName: value.merchantName
                }
                this.product.merchants.push(prodDetails)
            });
        },
        getLoggedIn: function(newValue, oldValue){

        },
        getBuyNowProductId: function(newValue, oldValue){

        },
        getBuyNowProductPrice: function(newValue, oldValue) {
                console.log('buyNowProductPrice:', newValue)
            //order is placed!
            this.$router.push("/checkout");
        },
        getUserCartItem: function(newValue, oldValue){
            alert("Item added to cart")
        }
    },
    methods: {
        addToCart(merchantId) {
            
            if(localStorage.getItem("loggedIn") == "false"){
                // console.log("not logged in")
                var product_number = sessionStorage.length + 1
                var productValues = {'pid':this.product.pId}
                sessionStorage.setItem('product' + product_number, JSON.stringify(productValues))
                alert("the item is added")
            }
            else{
                var userDetails = JSON.parse(localStorage.getItem("userDetails"))
                var payload = {
                        'userEmail': userDetails.emailId,
                        'productId': this.product.pId,
                        'merchantId': merchantId,
                        'quantity': 1,
                        'productPrice': this.product.merchants[this.selected].price,
                        'productName': this.product.pName,
                        'productImage': this.product.image
                }
                this.$store.dispatch('addItemToCart', payload)
            }
        },
        directCheckout() {
           // if(this.getLoggedIn == false){
                // console.log("not logged in")
                var product_number = sessionStorage.length + 1
                var productValues = {'pid': this.product.pId}
                sessionStorage.setItem('product' + product_number, JSON.stringify(productValues))
                alert("direct checkout")

                this.$store.dispatch('buyNowProduct', {pid: this.product.pId, success: this.removalSuccess})
                this.$store.dispatch('buyNowProductPrice', this.product.pId)

            //}
           // else{
                
           // }
        },
        removalSuccess(id) {
            console.log('***', id)
            //alert("Item added to cart")
        }
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