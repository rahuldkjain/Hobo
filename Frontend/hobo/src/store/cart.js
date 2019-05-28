import cartApis from '../apis/cartApis.js'
export default {
    state: {
        cartItem: {},
        cartItems: [],
        cartItemList: []
    },
    getters: {
        getCartItem: (state) => state.cartItem
    },
    mutations: {
        SET_ADD_TO_CART: (state, result) => {
            state.cartItem = result.data
        },
        SET_FETCH_CART_ITEMS: (state, result) => {
            state.cartItems = result.data
        },
        SET_ADD_CART_ITEM_LIST: (state, result) => {
            state.cartItemList.push(result.data)
        }
    },
    actions: {
        addItemToCart: (context, payload) => {
            // console.log('data in action'+payload)
            cartApis.addCartItem((result) => {
                context.commit('SET_ADD_TO_CART', result.data)
            }, payload)
        },
        cartItems: (context, emailId) => {
            // console.log('data in action'+payload)
            cartApis.fetchCartItems((result) => {
                context.commit('SET_FETCH_CART_ITEMS', result.data)
            }, emailId)
        },
        addCartItemList: (context, payload) => {
            // console.log('data in action'+payload)
            cartApis.putCartItemList((result) => {
                context.commit('SET_ADD_CART_ITEM_LIST', result.data)
            }, payload)
        }
    }
}