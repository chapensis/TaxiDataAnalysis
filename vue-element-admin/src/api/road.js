import api from './index'
const url = '/service/road'
const urlRoadTrip = '/service/roadtrip'

export default {
  get(condition) {
    const method = 'POST'
    const postUrl = url + '/list'
    return api.fetch({ url: postUrl, method, body: condition })
  },

  getTotalRoadNum(condition) {
    const method = 'POST'
    const postUrl = url + '/total'
    return api.fetch({ url: postUrl, method, body: condition })
  },

  getRoadSeekingTripList(condition) {
    const method = 'POST'
    const postUrl = urlRoadTrip + '/slist'
    return api.fetch({ url: postUrl, method, body: condition })
  },

  getRoadSeekingTripListNum(condition) {
    const method = 'POST'
    const postUrl = urlRoadTrip + '/stotal'
    return api.fetch({ url: postUrl, method, body: condition })
  },

  getRoadDrivingTripList(condition) {
    const method = 'POST'
    const postUrl = urlRoadTrip + '/dlist'
    return api.fetch({ url: postUrl, method, body: condition })
  },

  getRoadDrivingTripListNum(condition) {
    const method = 'POST'
    const postUrl = urlRoadTrip + '/dtotal'
    return api.fetch({ url: postUrl, method, body: condition })
  }
}
