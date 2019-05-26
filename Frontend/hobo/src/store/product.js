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
        totalAmount: 0
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
        getCartProductMerchantId: (state) => state.cartProductMerchantId

    },
    mutations: {
        SET_PRODUCT: (state, result) => {
            state.product = result.data
        },
        SET_PRODUCT_DETAILS: (state, result) => {
            state.productDetails = result.data
        },
        SET_CART_PRODUCT: (state, result) => {
            if(!state.cartProductId.includes(result.data.productId)){
                state.cartProduct.push(result.data.productName)
                state.cartProductId.push(result.data.productId)
                state.cartImage.push(result.data.productImage)
            }
        },
        SET_CART_PRODUCT_PRICE: (state, result) => {
            console.log('result price:' + result.data[0].price)
            if(!state.cartProductPrice.includes(result.data[0].price)){
                state.cartProductPrice.push(result.data[0].price)
                state.cartProductMerchantId.push(result.data[0].merchantId)
            }
            // console.log('cartProductPrice: ' + state.cartProductPrice)
        },
        SET_CART_QUANTITY: (state, result) => {
            state.cartQuantity = result;
        },
        SET_TOTAL_AMOUNT: (state, result) => {
            state.totalAmount = result;
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
        cartQuantity: (context,quantity) => {
            
            context.commit('SET_CART_QUANTITY',quantity)
        },
        checkoutAmount: (context,total) => {
            context.commit('SET_TOTAL_AMOUNT',total)
        }
        
    }
}