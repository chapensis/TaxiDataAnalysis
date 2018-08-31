import Road from '@/api/road';

const state = {
  roadList: [],
  totalRoadNum: 0
}

const getters = {

}

const mutations = {
  setRoadInfo(state, roadList) {
    state.roadList = roadList;
  },

  setRoadTotalNum(state, data) {
    console.log('data:' + data);
    state.totalRoadNum = data[0];
  }
}

const actions = {
  getRoadInfo({ commit }, body) {
    return Road.get(body).then(response => {
      commit('setRoadInfo', response.data);
    });
  },

  getTotalRoadNum({ commit }, body) {
    return Road.getTotalRoadNum(body).then(response => {
      commit('setRoadTotalNum', response.data);
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
