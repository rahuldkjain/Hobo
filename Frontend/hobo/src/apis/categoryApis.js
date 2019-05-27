import makeApiCalls from './makeApiCalls.js'
export default {
    getCategoryProducts(callback, category) {
        var url = '/goToProduct/product/category?category=' + category
        makeApiCalls.makeGetRequest(url, callback)
    }
}