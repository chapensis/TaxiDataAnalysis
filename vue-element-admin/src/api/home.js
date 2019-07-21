import api from './index'
const url = '/service/home'

export default {
  getVisitCount(condition) {
    const method = 'POST'
    const postUrl = url + '/visit'
    return api.fetch({ url: postUrl, method, body: condition })
  }
}
