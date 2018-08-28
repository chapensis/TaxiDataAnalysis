import api from './index'
const url = '/road'

export default {
  get(condition) {
    const method = 'GET'
    const getUrl = url + `/info?pageNum=${condition.pageNum}&pageSize=${condition.pageSize}`
    return api.fetch({ url: getUrl, method })
  }
}
