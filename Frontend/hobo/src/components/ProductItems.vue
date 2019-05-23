<template>
    <b-container class="bv-example-row row">
        <b-row>
            
                <b-col sm="3" id="products" v-for="(product,index) in products" :key="index">
                    <img class="productItem" :src='product.image'>
                    <div>Product: {{product.name}}</div>
                    <div>Price: {{product.price}}</div>
                    <div>Rating: {{product.rating}}</div>
                
                </b-col>
            
        </b-row>
        
    </b-container>
</template>
<script>
export default {
    name: 'ProductItems',
    data() {
        return {
            items: [1,2,3],
            products: [
                {
                    image: 'https://picsum.photos/300/150/?image=41',
                    name: 'hello',
                    price: '125',
                    rating: '4/5'
                },
                {
                    image: 'https://picsum.photos/300/150/?image=41',
                    name: 'hello',
                    price: '125',
                    rating: '4/5'
                },
                {
                    image: 'https://picsum.photos/300/150/?image=41',
                    name: 'hello',
                    price: '125',
                    rating: '4/5'
                },
                {
                    image: 'https://picsum.photos/300/150/?image=41',
                    name: 'hello',
                    price: '125',
                    rating: '4/5'
                },
                {
                    image: 'https://picsum.photos/300/150/?image=41',
                    name: 'hello',
                    price: '125',
                    rating: '4/5'
                },
                {
                    image: 'https://picsum.photos/300/150/?image=41',
                    name: 'hello',
                    price: '125',
                    rating: '4/5'
                },
                {
                    image: 'https://picsum.photos/300/150/?image=41',
                    name: 'hello',
                    price: '125',
                    rating: '4/5'
                }
            ],
            images: [
                'https://picsum.photos/300/150/?image=41','https://picsum.photos/300/150/?image=41','https://picsum.photos/300/150/?image=41',
                'https://picsum.photos/300/150/?image=41','https://picsum.photos/300/150/?image=41'
            ]
        }
    },
    methods: {
       
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

