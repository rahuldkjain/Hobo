import makeApiCalls from './makeApiCalls.js'
export default {
    placeOrder(callback, payload) {
        var url = '/order/orders'
        makeApiCalls.makePostRequest(url, callback, payload)
    },
    orderHistory(callback, email) {
        var url = '/order/orders/getall?email=' + email
        makeApiCalls.makeGetRequest(url, callback)
    },
    placeProductOrder(callback, payload) {
        var url = '/orderProduct/orderproduct'
        makeApiCalls.makePostRequest(url, callback, payload)
    },
}