import Cookies from 'js-cookie'

export default {
  user: null,
  language: Cookies.get('language') || 'zh',
  isSidebarCollapse: false
}
