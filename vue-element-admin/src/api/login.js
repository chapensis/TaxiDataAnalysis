import request from '@/utils/request'
import api from './index'

export function loginByUsername(username, password) {
  const data = {
    username,
    password
  }
  // const method = 'POST'
  console.log('开始登陆...');
  // return api.fetch({ url: '/login/login', method, data })
  return request({
    url: '/login/login',
    method: 'post',
    data
  })
}

export function logout() {
  return request({
    url: '/login/logout',
    method: 'post'
  })
}

export function getUserInfo(token) {
  return request({
    url: '/user/info',
    method: 'get',
    params: { token }
  })
}

