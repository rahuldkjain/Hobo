import makeApiCalls from './makeApiCalls.js'
export default {
    getUser(callback, payload) {
        var url = '/user/login'
        makeApiCalls.makePostRequest(url, callback, payload)
    },
    getMerchant(callback, payload) {
        var url = '/user/merchantlogin'
        makeApiCalls.makePostRequest(url, callback, payload)
    }
}