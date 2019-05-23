import makeApiCalls from './makeApiCalls.js'
export default {
    getCategory(callback,name){
        var url = 'api/category?name=' + name
        makeApiCalls.makeGetRequest(url,callback)
    },
    createCategory(callback,obj) {
        var url = 'api/category'
        makeApiCalls.makePostRequest(url,callback,obj) 
    },
    updateCategory(callback,obj) {
        var url = 'api/category'
        makeApiCalls.makePostRequest(url,callback,obj) 
    },
    deleteCategory(callback,name) {
        var url = 'api/category?name=' + name
        makeApiCalls.makePostRequest(url,callback) 
    },
    getAllCategory(callback) {
        var url = 'api/listcategory'
        makeApiCalls.makePostRequest(url,callback) 
    },
}