import axios from 'axios'
import { ElMessage } from 'element-plus'

const request = axios.create({
  baseURL: '/registration',
  timeout: 5000
})

request.interceptors.response.use(
  response => response.data,
  error => {
    ElMessage.error('请求失败: ' + (error.message || '网络错误'))
    return Promise.reject(error)
  }
)

export const registrationAPI = {
  register: (registration) => {
    return request.post('/register', registration)
  },

  cancelRegistration: (id) => {
    return request.put(`/cancel/${id}`)
  },

  getById: (id) => {
    return request.get(`/getById/${id}`)
  },

  getByPatientId: (patientId) => {
    return request.get(`/listByPatient/${patientId}`)
  },

  getByDoctorId: (doctorId) => {
    return request.get(`/listByDoctor/${doctorId}`)
  },

  getAll: () => {
    return request.get('/list')
  }
}