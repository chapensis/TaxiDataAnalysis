import api from './index'
const url = '/service/road'

export default {
  get(condition) {
    const method = 'POST'
    const postUrl = url + '/list'
    console.log('get condition:', condition);
    return api.fetch({ url: postUrl, method, body: condition })
  },

  getTotalRoadNum(condition) {
    const method = 'POST'
    const postUrl = url + '/total'
    console.log('getTotalRoadNum condition:', condition);
    return api.fetch({ url: postUrl, method, body: condition })
  }
}
