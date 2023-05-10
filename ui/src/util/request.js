import axios from 'axios';
import store from '@/store';

const TOKEN_KEY = 'jwt_token'; // 保存在本地存储中的JWT Token的Key

// 创建一个axios实例
const request = axios.create({
    baseURL: "http://localhost:8080", // API的base_url
    timeout: 5000 // 请求超时时间
});

// 请求拦截器
request.interceptors.request.use(
    config => {
        // 在请求头中添加JWT Token
        const token = store.getters.GET_TOKEN;
        alert(token)
        if (token) {
            config.headers.token = token;
        }
        return config;
    },
    error => {
        console.error(error);
        return Promise.reject(error);
    }
);

// 响应拦截器
request.interceptors.response.use(
    response => {
        return response.data;
    },
    error => {
        console.error(error);
        return Promise.reject(error);
    }
);

// 封装GET方法
export function get(url, params = {}) {
    return new Promise((resolve, reject) => {
        request.get(url, { params })
            .then(response => {
                resolve(response);
            })
            .catch(error => {
                reject(error);
            });
    });
}

// 封装POST方法
export function post(url, data = {}) {
    return new Promise((resolve, reject) => {
        request.post(url, data)
            .then(response => {
                resolve(response);
            })
            .catch(error => {
                reject(error);
            });
    });
}

// 封装文件上传方法
export function fileupload(url, data = {}) {
    const formData = new FormData();
    Object.keys(data).forEach(key => {
        formData.append(key, data[key]);
    });
    return new Promise((resolve, reject) => {
        request.post(url, formData, {
            headers: {
                'Content-Type': 'multipart/form-data'
            }
        })
            .then(response => {
                resolve(response);
            })
            .catch(error => {
                reject(error);
            });
    });
}

export default request;
