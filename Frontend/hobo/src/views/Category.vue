<template>
    <div>
        <h1> Categories </h1>
        <!-- <ProductItems/> -->
        <b-container class="bv-example-row row">
        <b-row>
            
                <b-col sm="3" id="products" v-for="(product,index) in getCategory" :key="index">
                   
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

    </div>
</template>
<script>
import ProductItems from '@/components/ProductItems.vue'
import {mapGetters, mapActions} from 'vuex'
export default {
    name: 'Category',
    computed: {
        ...mapGetters(['getCategory'])
    },
    mounted(){
        // console.log(this.getCategory[0].productId)
        // console.log(this.$router.currentRoute.path)
        var res = this.$router.currentRoute.path.split("/");
        // console.log(res)
     var pid = res[2]

     if(pid==1)
            this.category = "Electronics"
          else if(pid==2)
            this.category = "Sports"
          else
            this.category = "Clothing"
          this.$store.dispatch("categoryProducts",this.category)

    },
    methods: {
        goToProduct(pid) {
            console.log("in goToProd function")
            this.$router.push('/product/'+pid )
        }
    },
    components: {
        ProductItems
    }
}
</script>
<style scoped>
.row{
    width:100%;
    margin:0 auto;
}
</style>


