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
            if(result.data != null){
                state.loggedin = true
                state.user = result.data
                
            }
        },
        MERCHANT_LOGIN: (state, result) => {
            state.loggedin = true
            state.merchant = result
        },
        USER_LOGOUT: (state) => {
            state.loggedin = false
            state.user = {}
        }
    },
    actions: {
        checkLogin: (context, payload) => {
            console.log('data in action'+payload)
            userApis.getUserDetails((result) => {
                context.commit('SET_LOGIN', result.data)
            }, payload)
        },
        merchantLogin: (context, payload) => {
            
            userApis.getMerchantDetails((result) => {
                context.commit('MERCHANT_LOGIN', result.data)
            }, payload)
        },
        checkLogout: (context) => {
            context.commit('USER_LOGOUT')
        }
    }
}