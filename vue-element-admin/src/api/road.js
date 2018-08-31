import api from './index'
const url = '/service/road'

export default {
  get(condition) {
    const method = 'GET'
    const getUrl = url + `/list?pageNum=${condition.pageNum}&pageSize=${condition.pageSize}`
    return api.fetch({ url: getUrl, method })
  },

  getTotalRoadNum(condition) {
    const method = 'GET'
    const getUrl = url + '/total'
    return api.fetch({ url: getUrl, method })
  }
}
