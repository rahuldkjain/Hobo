<template>
    <b-container class="bv-example-row row">
        <b-row>
            <b-col sm="4">
            <h2> View All Products </h2>
            </b-col>
            <b-col sm="6">
            <b-button @click="editFunc" variant="danger" class="edit">Edit</b-button>
            </b-col>
        </b-row>
            
                <b-row id="products" v-for="(product,index) in products" :key="index">
                    <img class="productItem" :src='product.image'>
                    <div class="detail">Product: {{product.name}}</div>
                    <div class="detail"><b-form-group id="price" label="Price(Rs)" label-for="price-box">
                <b-row>
                    <b-col sm="8">

                    <b-form-input
                    id="price-box"
                    type="number"
                    v-model="product.price"
                    :disabled="validated==1"
                    placeholder="Price">
                    </b-form-input>
                    </b-col>
                    
                </b-row>
            </b-form-group> </div>
            <div class="detail"><b-form-group id="price" label="Stock" label-for="stock-box">
                <b-row>
                    <b-col sm="8">

                    <b-form-input
                    id="stock-box"
                    type="number"
                    v-model="product.stock"
                    :disabled="validated==1"
                    placeholder="Stock">
                    </b-form-input>
                    </b-col>
                    
                </b-row>
            </b-form-group> </div>
                    <!-- <div class="detail">Rating: {{product.rating}}</div> -->
                    
                </b-row>
                <b-row>
                    <b-button class="successbutton" variant="success">Save Changes</b-button>
                </b-row>
            
      
        
    </b-container>
</template>
<script>
export default {
    name: 'ViewProduct',
    data() {
        return {
        products: [
                {
                    image: 'https://picsum.photos/300/150/?image=41',
                    name: 'hello',
                    price: '125',
                    stock: '3',
                },
                {
                    image: 'https://picsum.photos/300/150/?image=41',
                    name: 'hello',
                    price: '125',
                    stock: '5'
                },
                {
                    image: 'https://picsum.photos/300/150/?image=41',
                    name: 'hello',
                    price: '125',
                    stock: '3'
                },
                {
                    image: 'https://picsum.photos/300/150/?image=41',
                    name: 'hello',
                    price: '125',
                    stock: '0'
                }
            ],
            validated: true
        }
},
methods: {
    editFunc() {
        this.validated = !this.validated;
    }
}
}

</script>
<style scoped>
.row{
    width:100%;
}
#products{
    width:100%;
    margin: 5%;
}
.detail{
    margin-top:5%;
    margin-left:5%;
}
input{
    margin-left:50%;
}
.edit{
    margin-left: 40%;
    margin-top:5%;
    height:40px;
   width:100px;
}
.successbutton{
    margin: 3% auto;
    width:180px;
    height:50px;
    font-size:20px;
    text-align: center;
}
</style>