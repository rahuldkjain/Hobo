import productAPI from '../apis/productApis'
export default {
    state: {
        product: {},
        productDetails: {}
    },
    getters: {
        getProduct: (state) => state.product,
        getProductDetails: (state) => state.productDetails
    },
    mutations: {
        SET_PRODUCT: (state, result) => {
            state.product = result.data
        },
        SET_PRODUCT_DETAILS: (state, result) => {
            state.productDetails = result.data
        }
    },
    actions: {
        productPage: (context, pid) => {
            productAPI.getProduct((result) => {
                context.commit('SET_PRODUCT', result.data)
            }, pid)
        },
        productDetails: (context, pid) => {
            productAPI.getProductDetails((result) => {
                context.commit('SET_PRODUCT_DETAILS', result.data)
            }, pid)
        }
    }
}