export default {
    state: {
        loggedin: false,
        merchant: false
    },
    getters: {
        getLoggedIn: (state) => state.loggedin,
        getMerchant: (state) => state.merchant
    },
    mutations: {
        SET_LOGIN: (state) => {
            state.loggedin = !state.loggedin
        },
        MERCHANT_LOGIN: (state) => {
            state.merchantLogin = true
        }
    },
    actions: {
        checkLogin: (context) => {
            context.commit('SET_LOGIN')
        },
        merchantLogin: (context) => {
            context.commit('MERCHANT_LOGIN')
        }
    }
}