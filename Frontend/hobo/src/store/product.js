import productAPI from '../apis/productApis'
export default {
    state: {
        product: {},
        productDetails: {},
        cartProduct: []
    },
    getters: {
        getProduct: (state) => state.product,
        getProductDetails: (state) => state.productDetails,
        getCartProduct: (state) => state.cartProduct
    },
    mutations: {
        SET_PRODUCT: (state, result) => {
            state.product = result.data
        },
        SET_PRODUCT_DETAILS: (state, result) => {
            state.productDetails = result.data
        },
        SET_CART_PRODUCT: (state,result) => {
            console.log("set_cart_result: "+result.data)
            state.cartProduct.push(result)
            console.log("mutation "+state.cartProduct.productName)
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
        },
        cartProduct: (context, pid) => {
            productAPI.fetchCartProduct((result) => {
                context.commit('SET_CART_PRODUCT', result.data)
            }, pid)
        }
    }
}