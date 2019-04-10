import Business from '@/api/business';

const state = {
  businessList: []
}

const getters = {

}

const mutations = {
  setBusinessList(state, businessList) {
    state.businessList = businessList;
  }
}

const actions = {
  getBusinessList({ commit }, body) {
    return Business.get(body).then(response => {
      commit('setBusinessList', response.data);
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
