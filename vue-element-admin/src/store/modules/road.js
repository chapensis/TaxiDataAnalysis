import Road from '@/api/road';

const state = {
  roadList: [],
  roadSeekingTripList: [],
  roadDrivingTripList: []
}

const getters = {

}

const mutations = {
  setRoadInfo(state, roadList) {
    state.roadList = roadList;
  },

  setRoadSeekingTripList(state, roadSeekingTripList) {
    state.roadSeekingTripList = roadSeekingTripList;
  },

  setRoadDrivingTripList(state, roadDrivingTripList) {
    state.roadDrivingTripList = roadDrivingTripList;
  },

  updateRoadData(state, data) {
    const index = state.roadList.findIndex(road => road.roadId === data.roadId);
    if (index >= 0) {
      const newRoadInfo = Object.assign({}, state.roadList[index], data);
      state.roadList.splice(index, 1, newRoadInfo);
    }
  }
}

const actions = {
  getRoadList({ commit }, body) {
    return Road.get(body).then(response => {
      commit('setRoadInfo', response.data);
    });
  },

  addRoad({ commit }, body) {
    return Road.add(body);
  },

  deleteRoad({ commit }, body) {
    return Road.delete(body);
  },

  updateRoad({ commit }, body) {
    return Road.update(body).then(response => {
      commit('updateRoadData', body);
    });
  },

  getRoadSeekingTripList({ commit }, body) {
    return Road.getRoadSeekingTripList(body).then(response => {
      commit('setRoadSeekingTripList', response.data);
    });
  },

  getRoadDrivingTripList({ commit }, body) {
    return Road.getRoadDrivingTripList(body).then(response => {
      commit('setRoadDrivingTripList', response.data);
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
