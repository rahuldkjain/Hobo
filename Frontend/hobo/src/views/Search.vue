<template>
    <div>
    <b-row id="products" v-for="(product,index) in getSearchData" :key="index">
        <b-card class="eachCard">
                <div class="cartItem">
                    <img :src='product.productImage'>
                    <div class="head">
                        <h3> Name: <b-link @click="productDetails(product.productId)">  {{product.productName}} </b-link></h3>
                         <h3> Brand: {{product.productBrand}}</h3>
                        <h3> Description: {{product.description}}</h3>
                         <h3> Category: {{product.category}}</h3>
                        

                    </div>
                    <div class="quantity">
                        <!-- <Quantity/> -->


                    </div>
                </div>
        </b-card>
            </b-row>
   </div>
</template>
<script>
import { mapMutations, mapGetters } from 'vuex';
export default {
    name: 'Search',
    computed: {
        ...mapGetters(['getSearchData'])
    },
    mounted() {
        console.log(this.getSearchData, this.$route.params)
    },
    created() {
        this.$store.dispatch("checkSearch",this.$route.params.query)
    },
    methods: {
        productDetails(pid) {
            this.$router.push('/product/'+pid )
        }
    }
}
</script>
<style scoped>
.eachCard{
    width: 100%;
    margin: 3%;
}
img{
    width: 300px;
    height: 300px;
}
</style>


