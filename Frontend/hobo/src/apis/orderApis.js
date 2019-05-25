import makeApiCalls from './makeApiCalls.js'
export default {
    placeOrder(callback, payload) {
        var url = '/order/orders'
        makeApiCalls.makePostRequest(url, callback, payload)
    },
}