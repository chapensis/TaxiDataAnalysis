import Cookies from 'js-cookie'

const TokenKey = 'Admin-Token'

export function getToken() {
  return Cookies.get(TokenKey)
}

export function setToken(token) {
  const result = Cookies.set(TokenKey, token)
  return result
}

export function removeToken() {
  return Cookies.remove(TokenKey)
}
