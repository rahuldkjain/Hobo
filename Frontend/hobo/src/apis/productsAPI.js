import makeApiCalls from './makeApiCalls.js'
export default {
    getProducts(callback) {
        var url = 'api/product/getall'
        makeApiCalls.makeGetRequest(url, callback)
    },

}