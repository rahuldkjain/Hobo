import searchApis from '../apis/searchApis.js'

export default {
    state: {
        searchdata: null
    },
    getters: {
        getSearchData: (state) => state.searchdata
    },
    mutations: {
        SEARCH_DATA: (state, result) => {
            state.searchdata = result
        }
    },
    actions: {
        checkSearch: (context, query) => {
            searchApis.getSearch((result) => {
                context.commit('SEARCH_DATA', result.data.data)
            }, query)
        }

    }
}