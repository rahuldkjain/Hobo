import orderApis from '../apis/orderApis.js'
export default {
    state: {
        order: {}
    },
    getters: {
        getOrder: (state) => state.order,
    },
    mutations: {
        SET_ORDER: (state, result) => {
            if(result.data != null){
                state.order = result.data
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
    }
}