export default {
    state: {
        loggedin: false
    },
    getters: {
        getLoggedIn: (state) => state.loggedin
    },
    mutations: {
        SET_LOGIN: (state) => {
            state.loggedin = !state.loggedin
        }
    },
    actions: {
        checkLogin: (context) => {
            context.commit('SET_LOGIN')
        }
    }
}