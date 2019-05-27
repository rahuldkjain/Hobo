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
    deleteCartItem(callback, payload) {
        var url = '/Cart/cart'
        makeApiCalls.makeDeleteRequest(url, callback, payload)
    }
}