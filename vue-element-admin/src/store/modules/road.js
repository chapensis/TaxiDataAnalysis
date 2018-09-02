import Road from '@/api/road';

const state = {
  roadList: [],
  roadListNum: 0
}

const getters = {

}

const mutations = {
  setRoadInfo(state, roadList) {
    state.roadList = roadList;
  },

  setRoadTotalNum(state, data) {
    console.log('data:' + data);
    state.roadListNum = data[0].roadListNum;
  }
}

const actions = {
  getRoadList({ commit }, body) {
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
