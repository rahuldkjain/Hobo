<template>
    <div id="app">

  <h2>Default</h2>
  <star-rating></star-rating>

  




    </div>

</template>
<script>
export default {
    name: 'StarRating',
    methods: {
    setRating: function(rating) {
      this.rating = "You have Selected: " + rating + " stars";
    },
    showCurrentRating: function(rating) {
      this.currentRating = (rating === 0) ? this.currentSelectedRating : "Click to select " + rating + " stars"
    },
    setCurrentSelectedRating: function(rating) {
      this.currentSelectedRating = "You have Selected: " + rating + " stars";
    }
  },
  
}
</script>


