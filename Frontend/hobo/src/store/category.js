import categoryApis from '../apis/categoryApis.js'
export default {
    state: {
        category: []

    },
    getters: {
        getCategory: (state) => state.category
    },
    mutations: {
        SET_CATEGORY: (state, result) => {
            state.category = result.data
        }
    },
    actions: {
        categoryProducts: (context, category) => {
            categoryApis.getCategoryProducts((result) => {
                context.commit('SET_CATEGORY', result.data)
            }, category)
        }
    }
}