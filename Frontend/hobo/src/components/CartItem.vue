<template>

    <b-card bg-variant="light">
        <b-row id="products" v-for="(product,index) in getCartProduct" :key="index">
            <div class="cartItem" id="getCartProductId[index]">
                <img :src='getCartImage[index]'>
                <div class="head">
                    <h3> name: {{product}}</h3>
                    <h3> price: {{getCartProductPrice ? getCartProductPrice[index] : '' }}</h3>

                </div>
                <div class="quantity">
                    <Quantity/>
                </div>
                <div class="button">
                    <b-button @click="removeCartItem(getCartProductId[index])" variant="danger">Remove Item</b-button>
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
            
        }
    },
    methods:{
        removeCartItem: function(pid){
            var keys = Object.keys(sessionStorage)
            console.log(keys)
            keys.forEach(key => {
                if(sessionStorage.getItem(key) == pid){
                    sessionStorage.removeItem(key)
                }
            })
            this.forceRerender();
        },
        forceRerender() {
           
            window.location.reload()
        }

    },
    computed: {
        ...mapGetters(['getLoggedIn','getCartProduct','getProductDetails','getCartImage', 'getCartProductPrice', 'getCartProductId'])
    },
    components: {
        Quantity
    },
    mounted() {
        console.log("in mounted ")
        if(this.getLoggedIn == false){
            var keys = Object.keys(sessionStorage)
            console.log(keys)
            keys.forEach(key => {
                var pid = sessionStorage.getItem(key)
                console.log("pid: " + pid)
                this.$store.dispatch('cartProduct', pid)
                this.$store.dispatch('cartProductPrice', pid)
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


