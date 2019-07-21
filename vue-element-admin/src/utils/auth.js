import Cookies from 'js-cookie'

const TokenKey = 'Admin-Token'

export function getToken() {
  console.log('有人要想获得token哦:', Cookies.get(TokenKey))
  return Cookies.get(TokenKey)
}

export function setToken(token) {
  console.log('有人要想设置token哦:', token)
  const result = Cookies.set(TokenKey, token)

  console.log('看看设置后的结果:', Cookies.get(TokenKey), '  result:', result)
  return result
}

export function removeToken() {
  console.log('有人要想删除token哦:')
  return Cookies.remove(TokenKey)
}
