<template>

    <b-card bg-variant="light">
        <b-row id="products" v-for="(product,index) in getCartProduct" :key="index">
            <div class="cartItem" id="getCartProductId[index]">
                <img :src='getCartImage[index]'>
                <div class="head">
                    <h4> Name: {{product}}</h4>
                    <h4> price: {{getCartProductPrice ? getCartProductPrice[index] : '' }}</h4>
                    <h4>Quantity: {{getCartQuantity[index]}}</h4>
                </div>
                <div>
                    <!-- <Quantity/> -->
                    <h3> Total: {{getCartQuantity[index]*getCartProductPrice[index]}}  </h3>
                </div>
            
                
            </div>
        </b-row>

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
            total: []
            
        }
    },
    computed: {
        ...mapGetters(['getProduct','getCartQuantity','getCartProductPrice','getCartProduct','getCartImage','getCartProductId','getTotalAmount'])
    },
    mounted(){
        console.log("quantity on shipping page "+this.getCartQuantity)
        
            for(productPrice in this.total){
                this.totalAmount += productPrice
            }
            console.log("total "+this.totalAmount)
            
        
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