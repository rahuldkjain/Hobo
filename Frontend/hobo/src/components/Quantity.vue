<template>
    <div class="quantity-toggle">
      <button @click="decrement()">&mdash;</button>
      <input class="input" type="text" :value="quantity" readonly>
      <button @click="increment()">&#xff0b;</button>
    </div>
</template>
<script>
export default {
    name: 'Quantity',
    data () {
    return {
      quantity: 1
    }
  },
  methods: {
    increment () {
      this.quantity++
    },
    decrement () {
      if(this.quantity === 1) {
        alert('Negative quantity not allowed')
      } else {
        this.quantity--
      }
    }
  }
}
</script>
<style scoped>
.input{
  width: 10%;
}

    #app {
  display: flex;
  width: 100%;
  height: 100vh;
  justify-content: center;
  align-items: center;
}

pre {
  background: #eee;
  padding: 1rem;
  border-radius: 5px;
}

</style>


