import axios from 'axios'
import { ElMessage } from 'element-plus'

const request = axios.create({
  baseURL: '/doctor',
  timeout: 5000
})

request.interceptors.response.use(
  response => response.data,
  error => {
    ElMessage.error('请求失败: ' + (error.message || '网络错误'))
    return Promise.reject(error)
  }
)

export const doctorAPI = {
  insertDoctor: (doctor) => {
    return request.post('/add', doctor)
  },

  updateDoctor: (doctor) => {
    return request.put('/update', doctor)
  },

  deleteById: (id) => {
    return request.delete(`/delete/${id}`)
  },

  deleteByIds: (ids) => {
    return request.delete('/deleteByIds', { data: ids })
  },

  findDoctorList: () => {
    return request.get('/list')
  },

  getDoctorById: (id) => {
    return request.get(`/getById/${id}`)
  },

  findDoctorByLike: (doctorName, department, doctorTitle) => {
    return request.get('/listByCondition', {
      params: {
        doctorName,
        department,
        doctorTitle
      }
    })
  }
}