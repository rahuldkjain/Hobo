import orderApis from '../apis/orderApis.js'
export default {
    state: {
        order: {},
        orderId: '',
        orderProductNum: 0,
        orderHistory: []
    },
    getters: {
        getOrder: (state) => state.order,
        getOrderId: (state) => state.orderId,
        getOrderProductNum: (state) => state.orderProductNum,
        getOrderHistory: (state) => state.orderHistory
    },
    mutations: {
        SET_ORDER: (state, result) => {
            if (result.data != null) {
                state.order = result.data
                state.orderId = result.data.orderId
            }
        },
        SET_PRODUCT_ORDER: (state, result) => {
            if (result.data != null) {
                state.orderProductNum += 1
            }
        },
        SET_ORDER_HISTORY: (state, result) => {
            if (result.data != null) {
                state.orderHistory = result.data
            }
        },
    },
    actions: {
        createOrder: (context, payload) => {
            // console.log('data in action'+payload)
            orderApis.placeOrder((result) => {
                context.commit('SET_ORDER', result.data)
            }, payload)
        },
        createProductOrder: (context, payload) => {
            // console.log('data in action'+payload)
            orderApis.placeProductOrder((result) => {
                context.commit('SET_PRODUCT_ORDER', result.data)
            }, payload)
        },
        fetchOrderHistory: (context, email) => {
            // console.log('data in action'+payload)
            orderApis.orderHistory((result) => {
                context.commit('SET_ORDER_HISTORY', result.data)
            }, email)
        }
    }
}