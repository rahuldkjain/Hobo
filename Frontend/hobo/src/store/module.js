import userApis from '../apis/userApis'

export default {
    state: {
        loggedin: false,
        user: {},
        merchant: {}


    },
    getters: {
        getLoggedIn: (state) => state.loggedin,
        getMerchant: (state) => state.merchant,
        getUser: (state) => state.user

    },
    mutations: {
        SET_LOGIN: (state, result) => {
            state.loggedin = true
            state.user = result
        },
        MERCHANT_LOGIN: (state, result) => {
            state.loggedin = true
            state.merchant = result
        }
    },
    actions: {
        checkLogin: (context, payload) => {
            userApis.getUser((result) => {
                context.commit('SET_LOGIN', result.data)
            }, payload)
        },
        merchantLogin: (context, payload) => {
            userApis.getMerchant((result) => {
                context.commit('MERCHANT_LOGIN', result.data)
            }, payload)
        }
    }
}