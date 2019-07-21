import request from '@/utils/request'
// import api from './index'

export function loginByUsername(username, password) {
  const data = {
    username,
    password
  }
  // const method = 'POST'
  console.log('开始登陆...');
  // return api.fetch({ url: '/login/login', method, data })
  return request({
    url: '/service/user/login',
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
  console.log('我准备去请求getUserInfo啦：', token)
  // 实验表明，这里并没有真正请求到后台
  return request({
    url: '/service/user/info',
    method: 'get',
    params: { token }
  })
}

