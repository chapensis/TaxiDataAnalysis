import Road from '@/api/road';

const state = {
  roadList: [],
  roadListNum: 0,
  roadSeekingTripList: [],
  roadSeekingTripListNum: 0,
  roadDrivingTripList: [],
  roadDrivingTripListNum: 0
}

const getters = {

}

const mutations = {
  setRoadInfo(state, roadList) {
    state.roadList = roadList;
  },

  setRoadTotalNum(state, data) {
    state.roadListNum = data[0].roadListNum;
  },

  setRoadSeekingTripList(state, roadSeekingTripList) {
    state.roadSeekingTripList = roadSeekingTripList;
  },

  setRoadSeekingTripListNum(state, data) {
    state.roadSeekingTripListNum = data[0];
  },

  setRoadDrivingTripList(state, roadDrivingTripList) {
    state.roadDrivingTripList = roadDrivingTripList;
  },

  setRoadDrivingTripListNum(state, data) {
    state.roadDrivingTripListNum = data[0];
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
  },

  getRoadSeekingTripList({ commit }, body) {
    return Road.getRoadSeekingTripList(body).then(response => {
      commit('setRoadSeekingTripList', response.data);
    });
  },

  getRoadSeekingTripListNum({ commit }, body) {
    return Road.getRoadSeekingTripListNum(body).then(response => {
      commit('setRoadSeekingTripListNum', response.data);
    });
  },

  getRoadDrivingTripList({ commit }, body) {
    return Road.getRoadSeekingTripList(body).then(response => {
      commit('setRoadDrivingTripList', response.data);
    });
  },

  getRoadDrivingTripListNum({ commit }, body) {
    return Road.getRoadSeekingTripListNum(body).then(response => {
      commit('setRoadDrivingTripListNum', response.data);
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
