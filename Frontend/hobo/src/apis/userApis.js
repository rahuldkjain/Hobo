import makeApiCalls from './makeApiCalls.js'
export default {
    getUserDetails(callback, payload) {
        var url = '/user/login'
        makeApiCalls.makePostRequest(url, callback, payload)
    },
    getMerchantDetails(callback, payload) {
        var url = '/user/merchantlogin'
        makeApiCalls.makePostRequest(url, callback, payload)
    }
}