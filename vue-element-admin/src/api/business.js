import api from './index'
const url = '/service/business'

export default {
  get(condition) {
    const method = 'POST'
    const postUrl = url + '/list'
    return api.fetch({ url: postUrl, method, body: condition })
  },

  getTotalBusinessNum(condition) {
    const method = 'POST'
    const postUrl = url + '/total'
    return api.fetch({ url: postUrl, method, body: condition })
  }
}
