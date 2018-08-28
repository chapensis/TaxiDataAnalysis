import 'es6-promise/auto';
import 'whatwg-fetch';

const fetchUtil = request => {
	return fetch(request)
	  .then(response => {
	  	if(response.ok) {
	  		return response.json();
	  	} else {
	  		return Promise.reject(response.stateText);
	  	}
	  })
	  .then(response => {
        if(response.code === 50015) {
        	// code = 50015 用户未通过认证返回码，重新加载
        	window.location.reload(true);
        } else {
        	return response.code === 0 ? Promise.resolve(response) : Promise.reject(response.message);
        }
	  });
}

export default {
  fetch(data) {
    const init = {
      method: data.method,
      headers: new Headers({
        'Content-Type': 'application/json'
      }),
      credentials: 'include',
      redirect: 'follow',
      body: JSON.stringify(data.body)
    };
    const request = new Request(data.url, init);
    return fetchUtil(request);
  }
}
