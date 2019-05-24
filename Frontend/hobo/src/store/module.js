import productsAPI from '../apis/productsAPI.js'
export default {
    state: {
        loggedin: false,
        merchant: false,
        products: []
    },
    getters: {
        getLoggedIn: (state) => state.loggedin,
        getMerchant: (state) => state.merchant,
        getAllProducts: (state) => state.products
    },
    mutations: {
        SET_LOGIN: (state) => {
            state.loggedin = !state.loggedin
        },
        MERCHANT_LOGIN: (state) => {
            state.merchantLogin = true
        },
        SET_PRODUCTS: (state, result) => {
            state.products = result
        }
    },
    actions: {
        checkLogin: (context) => {
            context.commit('SET_LOGIN')
        },
        merchantLogin: (context) => {
            context.commit('MERCHANT_LOGIN')
        },
        allProducts: (context) => {
            productsAPI.getProducts((result) => {
                context.commit('SET_PRODUCTS', result.data)
            })
        }
    }
}