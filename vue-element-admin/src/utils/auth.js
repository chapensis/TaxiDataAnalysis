import Cookies from 'js-cookie'

const TokenKey = 'Admin-Token'

export function getToken() {
  return Cookies.get(TokenKey)
}
// 让浏览器手动设置cookie是一种方式
export function setToken(token) {
  const result = Cookies.set(TokenKey, token)
  return result
}

export function removeToken() {
  return Cookies.remove(TokenKey)
}
