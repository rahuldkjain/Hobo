import makeApiCalls from './makeApiCalls.js'
export default {
    getSearch(callback,query){
        var url = '/api/search/suggestion?query=' + query
        makeApiCalls.makeGetRequest(url,callback)
    }
}