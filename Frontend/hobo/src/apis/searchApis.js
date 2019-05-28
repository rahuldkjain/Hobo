import makeApiCalls from './makeApiCalls.js'
export default {
    getSearch(callback, query) {
        var url = '/searchpage/search/suggestion?query=' + query
        makeApiCalls.makeGetRequest(url, callback)
    }
}