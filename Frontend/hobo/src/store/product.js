import productAPI from '../apis/productApis'
export default {
    state: {
        product: {},
        productDetails: {},
        cartProduct: [],
        cartProductId: [],
        cartImage: [],
        cartProductPrice: []
    },
    getters: {
        getProduct: (state) => state.product,
        getProductDetails: (state) => state.productDetails,
        getCartProduct: (state) => state.cartProduct,
        getCartImage: (state) => state.cartImage,
        getCartProductPrice: (state) => state.cartProductPrice,
        getCartProductId: (state) => state.cartProductId

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
            state.cartProductId.push(result.data.productId)
            state.cartImage.push(result.data.productImage)
        },
        SET_CART_PRODUCT_PRICE: (state, result) => {
            console.log('result price:' + result.data[0].price)
            state.cartProductPrice.push(result.data[0].price)
            console.log('cartProductPrice: ' + state.cartProductPrice)
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
        },
        cartProductPrice: (context, pid) => {
            productAPI.getProductDetails((result) => {
                context.commit('SET_CART_PRODUCT_PRICE', result.data)
            }, pid)
        }
    }
}