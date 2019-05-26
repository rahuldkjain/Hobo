import makeApiCalls from './makeApiCalls.js'
export default {
    getProduct(callback, pid) {
        var url = '/goToProduct/product?productId=' + pid
        makeApiCalls.makeGetRequest(url, callback)
    },
    getProductDetails(callback, pid) {
        var url = '/merchantProduct/merchantproduct/productmerchants?productId=' + pid
        makeApiCalls.makeGetRequest(url, callback)
    },
    fetchCart(callback, pid) {
        var url = '/fetchCartProduct/product?productId=' + pid
        makeApiCalls.makeGetRequest(url, callback)
    },
    fetchBuyNow(callback, pid) {
        var url = '/fetchBuyNowProduct/product?productId=' + pid
        makeApiCalls.makeGetRequest(url, callback)
    },
}