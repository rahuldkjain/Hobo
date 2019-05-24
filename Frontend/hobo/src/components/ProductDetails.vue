<template>

    <b-card bg-variant="light">
        <b-row v-if="!checkCurrentPath">
            <b-col>
            <h2>Your Products</h2>
            </b-col>
        </b-row>
        <b-row>
            <b-col>
                <img :src='product.image'>
            </b-col>
            <b-col>
                <b-row>
                <span class="text">Product name: {{product.name}}</span>
                </b-row>
                <b-row>    
                <span class="textprice">Product Price:{{product.price}}</span>
                </b-row>
            </b-col>
            <b-col v-if="checkCurrentPath">
              
                    <b-button class="button" variant="primary">Add to Cart</b-button>
                    <router-link to="/checkout"><b-button class="button" variant="success">Buy</b-button></router-link><br><br><br>
                    <span class="textmerchant">Merchant Details</span>
               
            </b-col>
        </b-row>
    </b-card>
    <!-- <h1> HELLOO </h1> -->
</template>
<script>
export default {
    name: 'ProductDetails',
    data() {
        return {
            product: {
                
                    image: 'https://i.imgur.com/VgoUWI5.jpg',
                    name: 'product 1',
                    price: '15000'
                }
            
        }
    },
    methods: {
        func() {
            console.log(this.$route.path);
        },
        mounted: {
            checkCurrentPath() {
            console.log(this.$route.path);
            if(this.$route.path == '/checkout')
                return false;
            else
                return true;
            }
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
.text{
    font-size: 22px;
    margin-top: 20%;
    margin-left:0
}
.textprice{
    font-size: 22px;
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