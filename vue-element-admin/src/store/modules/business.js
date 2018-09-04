import Business from '@/api/business';

const state = {
  businessList: [],
  businessListNum: 0
}

const getters = {

}

const mutations = {
  setBusinessList(state, businessList) {
    state.businessList = businessList;
  },

  setBusinessTotalNum(state, data) {
    state.businessListNum = data[0];
  }
}

const actions = {
  getBusinessList({ commit }, body) {
    return Business.get(body).then(response => {
      commit('setBusinessList', response.data);
    });
  },

  getTotalBusinessNum({ commit }, body) {
    return Business.getTotalBusinessNum(body).then(response => {
      commit('setBusinessTotalNum', response.data);
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
