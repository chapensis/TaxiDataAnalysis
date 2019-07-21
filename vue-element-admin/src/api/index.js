import 'es6-promise/auto';
import 'whatwg-fetch';
import { MessageBox } from 'element-ui'
import { getToken } from '@/utils/auth'
import store from '@/store'

const fetchUtil = request => {
  return fetch(request)
    .then(response => {
      if (response.ok) {
        return response.json();
      } else {
        return Promise.reject(response.stateText);
      }
    })
    .then(response => {
      console.log('response:', response)
      if (response.code === 50017) {
        MessageBox.confirm('您的账号已在其他设备登录，可以取消继续留在该页面，或者重新登录', '重新登录', {
          confirmButtonText: '重新登录',
          cancelButtonText: '取消',
          type: 'warning'
        }).then(() => {
          store.dispatch('FedLogOut').then(() => {
            location.reload() // 为了重新实例化vue-router对象 避免bug
          })
        })
        // // code = 50015,50016,50017 token失效，重新登录
        // store.dispatch('FedLogOut').then(() => {
        //   window.location.reload(true); // 为了重新实例化vue-router对象 避免bug
        // })
      } else if (response.code === 50015 || response.code === 50016) {
        // // code = 50015,50016,50017 token失效，重新登录
        store.dispatch('FedLogOut').then(() => {
          window.location.reload(true); // 为了重新实例化vue-router对象 避免bug
        })
      } else {
        return response.code === 0 ? Promise.resolve(response) : Promise.reject(response.message);
      }
    });
}

export default {
  fetch(data) {
    const token = getToken()
    const init = {
      method: data.method,
      headers: new Headers({
        'Content-Type': 'application/json',
        'Authorization': token // 每次请求都必须带上token信息
      }),
      credentials: 'include',
      redirect: 'follow',
      body: JSON.stringify(data.body)
    };
    const request = new Request(data.url, init);
    return fetchUtil(request);
  }
}
