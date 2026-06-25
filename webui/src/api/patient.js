import axios from 'axios'
import { ElMessage } from 'element-plus'

const request = axios.create({
  baseURL: '/patient',
  timeout: 5000
})

request.interceptors.response.use(
  response => response.data,
  error => {
    ElMessage.error('请求失败: ' + (error.message || '网络错误'))
    return Promise.reject(error)
  }
)

export const patientAPI = {
  insertPatient: (patient) => {
    return request.post('/add', patient)
  },

  updatePatient: (patient) => {
    return request.put('/update', patient)
  },

  deleteById: (id) => {
    return request.delete(`/delete/${id}`)
  },

  deleteByIds: (ids) => {
    return request.delete('/deleteByIds', { data: ids })
  },

  findPatientList: () => {
    return request.get('/list')
  },

  getPatientById: (id) => {
    return request.get(`/getById/${id}`)
  },

  findPatientByHospitalId: (hospitalId) => {
    return request.get(`/listByHospital/${hospitalId}`)
  },

  findPatientByLike: (patientName, patientPhone, patientIdcard) => {
    return request.get('/listByCondition', {
      params: {
        patientName,
        patientPhone,
        patientIdcard
      }
    })
  }
}