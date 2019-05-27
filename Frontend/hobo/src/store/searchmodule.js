import searchApis from '../apis/searchApis.js'

export default {
    state: {
        searchdata: []
    },
    getters: {
        getSearchData: (state) => state.searchdata
    },
    mutations: {
        SEARCH_DATA: (state, result) => {
            state.searchdata = result.data
        }
    },
    actions: {
        checkSearch: (context, query) => {
            searchApis.getSearch((result) => {
                context.commit('SEARCH_DATA', result.data)
            }, query)
        }

    }
}