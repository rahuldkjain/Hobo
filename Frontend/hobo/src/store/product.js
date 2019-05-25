import productAPI from '../apis/productApis'
export default {
    state: {
        product: {},
        productDetails: {},
        cartProduct: [],
        cartImage: []
    },
    getters: {
        getProduct: (state) => state.product,
        getProductDetails: (state) => state.productDetails,
        getCartProduct: (state) => state.cartProduct,
        getCartImage: (state) => state.cartImage
    },
    mutations: {
        SET_PRODUCT: (state, result) => {
            state.product = result.data
        },
        SET_PRODUCT_DETAILS: (state, result) => {
            state.productDetails = result.data
        },
        SET_CART_PRODUCT: (state, result) => {
            state.cartProduct.push(result.data.productName)
            state.cartImage.push(result.data.productImage)

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
            productAPI.fetchCart((result) => {
                context.commit('SET_CART_PRODUCT', result.data)
            }, pid)
        }
    }
}