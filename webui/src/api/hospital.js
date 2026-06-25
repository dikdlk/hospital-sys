import axios from 'axios'
import { ElMessage } from 'element-plus'

const request = axios.create({
  baseURL: '/hos',
  timeout: 10000
})

const staRequest = axios.create({
  baseURL: '/sta',
  timeout: 10000
})

staRequest.interceptors.response.use(
  response => response.data,
  error => {
    ElMessage.error('请求失败: ' + (error.message || '网络错误'))
    return Promise.reject(error)
  }
)

request.interceptors.response.use(
  response => response.data,
  error => {
    ElMessage.error('请求失败: ' + (error.message || '网络错误'))
    return Promise.reject(error)
  }
)

export const hospitalAPI = {
  findHosList: () => request.get('/findHosList'),

  insertHos: (hospital) => request.post('/insertHos', hospital),

  deleteById: (id) => request.delete('/deleteById', { params: { id } }),

  deleteByIds: (ids) => request.delete('/deleteByIds', { data: ids }),

  updateHos: (hospital) => request.put('/updateHos', hospital),

  findHosByLikeName: (hosName, hosLevel) => request.get('/findHosByLikeName', {
    params: { hosName, hosLevel }
  })
}

export const staAPI = {
  getHosCount: () => staRequest.get('/getHosCount'),
  
  getPatCount: () => staRequest.get('/getPatCount'),
  
  getPatAge: () => staRequest.get('/getPatAge'),
  
  getHosPatCount: () => staRequest.get('/getHosPatCount')
}
