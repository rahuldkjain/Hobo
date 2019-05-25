<template>

    <b-card bg-variant="light">
        <b-row id="products" v-for="(product,index) in getCartProduct" :key="index">
            <div class="cartItem">
                <img :src='getCartImage[index]'>
                <div class="head">
                    <h3> name: {{product}}</h3>

                </div>
                <div class="quantity">
                    <Quantity/>
                </div>
                <div class="button">
                    <b-button variant="danger">Remove Item</b-button>
                </div>
            </div>
        </b-row>
        
        <b-button class="success" variant="danger"><router-link to="/checkout">Checkout</router-link></b-button>
    </b-card>

</template>
<script>

import Quantity from '@/components/Quantity.vue'
import {mapGetters, mapActions} from 'vuex'; 
export default {
    name: 'CartItem',
    data() {
        return {
            products: [{}],
            productDetails: [{}]
        }
    },
    computed: {
        ...mapGetters(['getLoggedIn','getCartProduct','getProductDetails','getCartImage'])
    },
    components: {
        Quantity
    },
    mounted() {
        if(this.getLoggedIn == false){
            var len = sessionStorage.length;
            console.log(len)
            for(var i = 1; i <= len; i++ ){
                var pid = sessionStorage.getItem("product"+ i)
                console.log("pid: " + pid)
                this.$store.dispatch('cartProduct', pid)
            }
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
    margin-top: 10%;
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


