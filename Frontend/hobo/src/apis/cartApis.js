import makeApiCalls from './makeApiCalls.js'
export default {
    addCartItem(callback, payload) {
        var url = '/Cart/cart'
        makeApiCalls.makePostRequest(url, callback, payload)
    },
    fetchCartItems(callback, emailId) {
        var url = '/Cart/cart/usercart?emailId=' + emailId
        makeApiCalls.makeGetRequest(url, callback)
    },
    deleteCartItem(callback, cartItemId) {
        var url = '/Cart/cart?cartItemId=' + cartItemId
        makeApiCalls.makeDeleteRequest(url, callback)
    }
}