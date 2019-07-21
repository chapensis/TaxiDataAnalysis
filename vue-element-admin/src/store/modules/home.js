import Home from '@/api/home';

const state = {
  visitCount: 0
}

const getters = {

}

const mutations = {
  setVisitCount(state, visitCount) {
    state.visitCount = visitCount;
  }
}

const actions = {
  getVisitCount({ commit }, body) {
    return Home.getVisitCount(body).then(response => {
      commit('setVisitCount', response.data);
    });
  }
}

export default {
  state,
  getters,
  mutations,
  actions,
  namespaced: true
}
