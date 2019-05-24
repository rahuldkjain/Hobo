import makeApiCalls from './makeApiCalls.js'
export default {
    getProducts(callback) {
        var url = '/allProducts/product/getall'
        makeApiCalls.makeGetRequest(url, callback)
    }
}