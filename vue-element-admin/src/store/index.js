import Vue from 'vue'
import Vuex from 'vuex'
import app from './modules/app'
import errorLog from './modules/errorLog'
import permission from './modules/permission'
import tagsView from './modules/tagsView'
import user from './modules/user'
import taxi from './modules/taxi'
import road from './modules/road'
import getters from './getters'

Vue.use(Vuex)

const store = new Vuex.Store({
  modules: {
    app,
    errorLog,
    permission,
    tagsView,
    user,
    taxi,
    road
  },
  getters
})

if (module.hot) {
	module.hot.accept([
    './getters',
    './modules/user',
    './modules/taxi',
    './modules/road'
	], () => {
    store.hotUpdate({
      getters: require('./getters'),
      user: require('./modules/user'),
      taxi: require('./modules/taxi'),
      road: require('./modules/road')
    });
  })
}

export default store
