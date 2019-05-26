<template>
<div class="success">
    <h2>Your order has been placed!</h2>
    <h1>Thank you for Shopping with </h1>
    <h1 class="hobo">HOBO</h1>
    <h3>You will soon get a confirmation mail from the merchant</h3>
</div>
</template>
<script>
export default {
   mounted() {
        console.log("sessionStorage before: " + sessionStorage )
        
        sessionStorage.clear()
        console.log("sessionStorage after: " + sessionStorage )
    } 
}
</script>
<style scoped>
.success {
    margin: 10%;
}
.hobo{
    font-weight: bold;
    font-size: 80px;
    color:darkgoldenrod;
}
</style>


