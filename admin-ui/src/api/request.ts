import axios from 'axios';
import {ElMessage} from 'element-plus';
import router from "@/router";

const service = axios.create({
    baseURL: import.meta.env.VITE_BASE_URL,
    timeout: 5000
})

service.defaults.withCredentials = true;

service.interceptors.request.use(
    config => {
        return config;
    },
    error => {
        return Promise.reject(error.data.error.msg);
    },
)

service.interceptors.response.use(
    response => {
        if (response.data.success) {
            return Promise.resolve(response);
        } else {
            ElMessage.error(response.data.msg);
            if (response.data.code === 2001) {
                router.push('/login');
            }
            return Promise.reject(response.data.msg);
        }
    },
    error => {
        ElMessage.error(error.msg);
        return Promise.reject(error);
    }
)

export default service;