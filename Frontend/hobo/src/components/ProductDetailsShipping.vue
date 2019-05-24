<template>

    <b-card bg-variant="light">
        <b-row >
            <b-col>
            <h2>Your Products</h2>
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
                <span class="text"><b>Product Price:</b>{{product.price}}</span>
                </b-row>
            </b-col>
            
        </b-row>
    </b-card>
    <!-- <h1> HELLOO </h1> -->
</template>
<script>
import {mapGetters, mapActions} from 'vuex'; 
export default {
    name: 'ProductDetailsShipping',
    data() {
        return {
            product: {
                
                    image: 'https://i.imgur.com/VgoUWI5.jpg',
                    name: 'product 1',
                    price: '15000'
                }
            
        }
    },
    computed: {
        ...mapGetters(['getProduct'])
    }
    // methods: {
    //     func() {
    //         console.log(this.$route.path);
    //     }
    // },
    //     mounted: {
    //         checkCurrentPath() {
    //         console.log(this.$route.path);
    //         if(this.$route.path == '/checkout')
    //             return false;
    //         else
    //             return true;
    //         }
    //     }

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