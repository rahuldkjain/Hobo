<template>
    <div>
        <h1> Categories </h1>
        <ProductItems/>
    </div>
</template>
<script>
import ProductItems from '@/components/ProductItems.vue'
export default {
    name: 'Category',
    components: {
        ProductItems
    }
}
</script>

