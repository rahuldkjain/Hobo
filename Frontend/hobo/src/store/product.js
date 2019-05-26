import productAPI from '../apis/productApis'
export default {
    state: {
        product: {},
        productDetails: {},
        cartProduct: [],
        cartProductId: [],
        cartImage: [],
        cartProductPrice: [],
        cartQuantity: [],
        cartProductMerchantId: [],
        totalAmount: 0,
        buyNowProduct: '',
        buyNowProductId: '',
        buyNowProductImage: '',
        buyNowProductPrice: '',
        buyNowProductQuantity: 1,
        buyNowProductMerchantId: ''
    },
    getters: {
        getProduct: (state) => state.product,
        getProductDetails: (state) => state.productDetails,
        getCartProduct: (state) => state.cartProduct,
        getCartImage: (state) => state.cartImage,
        getCartProductPrice: (state) => state.cartProductPrice,
        getCartProductId: (state) => state.cartProductId,
        getCartQuantity: (state) => state.cartQuantity,
        getTotalAmount: (state) => state.totalAmount,
        getCartProductMerchantId: (state) => state.cartProductMerchantId,
        getBuyNowProduct: (state) => state.buyNowProduct,
        getBuyNowProductId: (state) => state.buyNowProductId,
        getBuyNowProductImage: (state) => state.buyNowProductImage,
        getBuyNowProductPrice: (state) => state.buyNowProductPrice,
        getBuyNowProductQuantity: (state) => state.buyNowProductQuantity,
        getBuyNowProductMerchantId: (state) => state.buyNowProductMerchantId

    },
    mutations: {
        SET_PRODUCT: (state, result) => {
            state.product = result.data
        },
        SET_PRODUCT_DETAILS: (state, result) => {
            state.productDetails = result.data
        },
        SET_CART_PRODUCT: (state, result) => {
            if (!state.cartProductId.includes(result.data.productId)) {
                state.cartProduct.push(result.data.productName)
                state.cartProductId.push(result.data.productId)
                state.cartImage.push(result.data.productImage)
            }
        },
        SET_CART_PRODUCT_PRICE: (state, result) => {
            console.log('result price:' + result.data[0].price)
            if (!state.cartProductPrice.includes(result.data[0].price)) {
                state.cartProductPrice.push(result.data[0].price)
                state.cartProductMerchantId.push(result.data[0].merchantId)
            }
        },
        SET_CART_QUANTITY: (state, result) => {
            state.cartQuantity = result
        },
        SET_TOTAL_AMOUNT: (state, result) => {
            state.totalAmount = result
        },
        SET_BUY_NOW_PRODUCT: (state, result) => {
            state.buyNowProduct = result.data.productName
            state.buyNowProductId = result.data.productId
            state.buyNowProductImage = result.data.productImage
            state.buyNowProductQuantity = 1
        },
        SET_BUY_NOW_PRODUCT_PRICE: (state, result) => {
            console.log('result price:' + result.data[0].price)
            state.buyNowProductPrice = result.data[0].price
            state.buyNowProductMerchantId = result.data[0].merchantId
            state.totalAmount = state.buyNowProductQuantity * state.buyNowProductPrice
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
        },
        cartQuantity: (context, quantity) => {

            context.commit('SET_CART_QUANTITY', quantity)
        },
        checkoutAmount: (context, total) => {
            context.commit('SET_TOTAL_AMOUNT', total)
        },
        buyNowProduct: (context, pid) => {
            productAPI.fetchBuyNow((result) => {
                context.commit('SET_BUY_NOW_PRODUCT', result.data)
            }, pid)
        },
        buyNowProductPrice: (context, pid) => {
            productAPI.getProductDetails((result) => {
                context.commit('SET_BUY_NOW_PRODUCT_PRICE', result.data)
            }, pid)
        }
    }
}