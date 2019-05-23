<template>
  <div class="home">
    <!-- <img alt="Vue logo" src="../assets/logo.png"> -->
    <Carousel/><br><br>
    <h4>Products </h4><br>
    <ProductItems/>
    <!-- <StarRating/> -->
    <!-- <HelloWorld msg="Welcome to Your Vue.js App"/> -->
  </div>
</template>

<script>
// @ is an alias to /src
import HelloWorld from '@/components/HelloWorld.vue'
import Carousel from '@/components/Carousel.vue'
import ProductItems from '@/components/ProductItems.vue'

export default {
  name: 'home',
  components: {
    HelloWorld,
    Carousel,
    ProductItems,
   
  }
}
</script>
