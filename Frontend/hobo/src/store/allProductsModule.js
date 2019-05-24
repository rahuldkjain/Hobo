import productsAPI from '../apis/productsAPI.js'
export default {
    state: {
        products: []
    },
    getters: {
        getAllProducts: (state) => state.products
    },
    mutations: {
        SET_PRODUCTS: (state, result) => {
            state.products = result.data
        }
    },
    actions: {
        allProducts: (context) => {
            productsAPI.getProducts((result) => {
                context.commit('SET_PRODUCTS', result.data)
            })
        }
    }
}