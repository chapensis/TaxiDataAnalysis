import Road from '@/api/road';

const state = {
  roadInfo: []
}

const getters = {

}

const mutations = {
  setRoadInfo(state, roadInfo) {
    state.roadInfo = roadInfo;
  }
}

const actions = {
  getRoadInfo({commit}, body) {
    return Road.get(body).then(response => {
    	commit('setRoadInfo', response.data);
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
