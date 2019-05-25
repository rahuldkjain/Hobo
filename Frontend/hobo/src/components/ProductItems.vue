<template>
    <b-container class="bv-example-row row">
        <b-row>
            
                <b-col sm="3" id="products" v-for="(product,index) in products" :key="index">
                    <!-- <img class="productItem" :src='product.productImage[0]'> -->
                    <!-- <div>Product: {{product.productName}}</div>
                    <div>Price: {{product.description}}</div>
                    <div>Category: {{product.category}}</div> -->
                    <div>
                       
                        <b-card
                            :title="product.productName"
                            :img-src="product.productImage[0]"
                            img-alt="Image"
                            img-top
                            tag="article"
                            style="max-width: 20rem;"
                            class="mb-2"
                        >
                            <b-card-text>
                            {{product.description}}
                            </b-card-text>
                             <!-- <router-link to="/product"> -->
                            <b-button @click="goToProduct(product.productId)"  variant="primary">Product Page</b-button>
                             <!-- </router-link> -->
                        </b-card>
                       
                    </div>
                
                </b-col>
                <div>
                   <!-- <b-button @click="getProducts">FetchProds</b-button> -->
                  
                </div>
            
        </b-row>
        
    </b-container>
</template>
<script>
import {mapGetters, mapActions} from 'vuex'
export default {
    name: 'ProductItems',
    data() {
        return {
            items: [1,2,3],
            products: [
                
            ],
            images: [
                'https://picsum.photos/300/150/?image=41','https://picsum.photos/300/150/?image=41','https://picsum.photos/300/150/?image=41',
                'https://picsum.photos/300/150/?image=41','https://picsum.photos/300/150/?image=41'
            ],
        
        }
    },
    methods: {
       getProducts: function(){
           
           this.$store.dispatch('allProducts');
           this.products = this.$store.getters.getAllProducts
           console.log(this.products)
       },
       goToProduct (pid){
           this.$router.push('/product/'+pid )
       }
    },
    computed : {
      ...mapGetters({
          getAllProducts : 'getAllProducts'
      })
    },
    watch : {
        getAllProducts: function(newValue, oldValue) {
            console.log('****:', newValue)
            this.products = newValue
        }
    },
    mounted() {
        console.log("before mount")
        // localStorage.setItem("loggedIn", false)
        this.$store.dispatch('allProducts');
    }    
}
</script>
<style scoped>
.row{
    margin:0
}
#products{
    margin:2% 4%;

}
.productItem{
    width:300px;
    height:300px;
  
}
</style>

