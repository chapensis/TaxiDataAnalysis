import api from './index'
const url = '/service/road'
const urlRoadTrip = '/service/roadtrip'

export default {
  get(condition) {
    const method = 'POST'
    const postUrl = url + '/list'
    return api.fetch({ url: postUrl, method, body: condition })
  },

  add(condition) {
    const method = 'POST'
    const postUrl = url + '/add'
    return api.fetch({ url: postUrl, method, body: condition })
  },

  delete(condition) {
    const method = 'POST'
    const postUrl = url + '/delete'
    return api.fetch({ url: postUrl, method, body: condition })
  },

  update(condition) {
    const method = 'POST'
    const postUrl = url + '/update'
    return api.fetch({ url: postUrl, method, body: condition })
  },

  getRoadSeekingTripList(condition) {
    const method = 'POST'
    const postUrl = urlRoadTrip + '/slist'
    return api.fetch({ url: postUrl, method, body: condition })
  },

  getRoadDrivingTripList(condition) {
    const method = 'POST'
    const postUrl = urlRoadTrip + '/dlist'
    return api.fetch({ url: postUrl, method, body: condition })
  }
}
