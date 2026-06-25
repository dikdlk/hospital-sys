<template>
  <div class="doctor-page">
    <div class="page-header">
      <div class="header-left">
        <h2 class="page-title">医生排班</h2>
        <p class="page-subtitle">查看医生信息并进行挂号操作</p>
      </div>
      <div class="header-right">
        <el-button type="primary" @click="handleAdd" :icon="Plus">新增医生</el-button>
      </div>
    </div>

    <div class="search-card">
      <el-form :inline="true" :model="searchForm" class="search-form">
        <el-form-item label="医生姓名">
          <el-input v-model="searchForm.doctorName" placeholder="请输入医生姓名" clearable class="search-input" />
        </el-form-item>
        <el-form-item label="科室">
          <el-input v-model="searchForm.department" placeholder="请输入科室名称" clearable class="search-input" />
        </el-form-item>
        <el-form-item label="职称">
          <el-select v-model="searchForm.doctorTitle" placeholder="请选择职称" class="search-select">
            <el-option label="全部" value="all" />
            <el-option label="主任医师" value="主任医师" />
            <el-option label="副主任医师" value="副主任医师" />
            <el-option label="主治医师" value="主治医师" />
            <el-option label="住院医师" value="住院医师" />
          </el-select>
        </el-form-item>
        <el-form-item>
          <el-button type="primary" @click="handleSearch" :icon="Search">查询</el-button>
          <el-button @click="handleReset" :icon="Refresh">重置</el-button>
        </el-form-item>
      </el-form>
    </div>

    <div class="card-container">
      <div v-for="doctor in doctorList" :key="doctor.id" class="doctor-card">
        <div class="card-header">
          <div class="doctor-avatar">
            <div class="avatar-circle">
              <span class="avatar-text">{{ doctor.doctorName.charAt(0) }}</span>
            </div>
            <div class="doctor-info">
              <h3 class="doctor-name">{{ doctor.doctorName }}</h3>
              <p class="doctor-title">{{ doctor.doctorTitle }}</p>
            </div>
          </div>
          <div class="card-actions">
            <el-button link type="primary" @click="handleEdit(doctor)" :icon="Edit">编辑</el-button>
            <el-button link type="danger" @click="handleDelete(doctor)" :icon="Delete">删除</el-button>
          </div>
        </div>

        <div class="card-body">
          <div class="info-row">
            <span class="info-label">科室：</span>
            <span class="info-value">{{ doctor.department }}</span>
          </div>
          <div class="info-row">
            <span class="info-label">工号：</span>
            <span class="info-value">{{ doctor.doctorCode }}</span>
          </div>
          <div class="info-row">
            <span class="info-label">电话：</span>
            <span class="info-value">{{ doctor.phone }}</span>
          </div>
          <div class="info-row">
            <span class="info-label">医院：</span>
            <span class="info-value">{{ doctor.hospitalName }}</span>
          </div>
          <div class="info-row">
            <span class="info-label">状态：</span>
            <el-tag :type="doctor.status === '在职' ? 'success' : 'info'" size="small">{{ doctor.status }}</el-tag>
          </div>
          <div class="info-row">
            <span class="info-label">剩余号源：</span>
            <el-tag :type="getCountType(doctor.numCount)" size="large" effect="dark">{{ doctor.numCount || 0 }}个</el-tag>
          </div>
        </div>

        <div class="card-footer">
          <div class="patient-select">
            <el-select
              v-model="selectedPatients[doctor.id]"
              placeholder="选择患者"
              filterable
              class="patient-selector"
              :disabled="!doctor.numCount || doctor.numCount <= 0"
            >
              <el-option
                v-for="patient in patientOptions"
                :key="patient.id"
                :label="patient.patientName + ' (' + patient.patientPhone + ')'"
                :value="patient.id"
                :disabled="registeredPatientIds.has(patient.id)"
              />
            </el-select>
          </div>
          <div class="action-buttons">
            <el-button
              type="primary"
              size="small"
              @click="handleRegistration(doctor)"
              :disabled="!selectedPatients[doctor.id] || !doctor.numCount || doctor.numCount <= 0"
              :icon="CirclePlus"
            >
              挂号
            </el-button>
            <el-button
              type="warning"
              size="small"
              @click="handleCancelRegistration(doctor)"
              :disabled="!selectedPatients[doctor.id]"
              :icon="CircleClose"
            >
              取消挂号
            </el-button>
          </div>
        </div>
      </div>

      <div v-if="doctorList.length === 0 && !loading" class="empty-state">
        <el-empty description="暂无医生信息" />
      </div>
    </div>

    <div class="pagination-wrapper">
      <el-pagination
        v-model:current-page="currentPage"
        v-model:page-size="pageSize"
        :page-sizes="[6, 12, 24, 48]"
        :total="total"
        layout="total, sizes, prev, pager, next, jumper"
        class="pagination"
      />
    </div>

    <el-dialog
      v-model="dialogVisible"
      :title="dialogTitle"
      width="600px"
      :close-on-click-modal="false"
      class="form-dialog"
    >
      <el-form
        ref="formRef"
        :model="form"
        :rules="formRules"
        label-width="100px"
        class="form-content"
      >
        <el-form-item label="医生姓名" prop="doctorName">
          <el-input v-model="form.doctorName" placeholder="请输入医生姓名" class="form-input" />
        </el-form-item>
        <el-form-item label="职称" prop="doctorTitle">
          <el-select v-model="form.doctorTitle" placeholder="请选择职称" class="form-select">
            <el-option label="主任医师" value="主任医师" />
            <el-option label="副主任医师" value="副主任医师" />
            <el-option label="主治医师" value="主治医师" />
            <el-option label="住院医师" value="住院医师" />
          </el-select>
        </el-form-item>
        <el-form-item label="科室" prop="department">
          <el-input v-model="form.department" placeholder="请输入科室" class="form-input" />
        </el-form-item>
        <el-form-item label="工号" prop="doctorCode">
          <el-input v-model="form.doctorCode" placeholder="请输入工号" class="form-input" />
        </el-form-item>
        <el-form-item label="联系电话" prop="phone">
          <el-input v-model="form.phone" placeholder="请输入联系电话" class="form-input" />
        </el-form-item>
        <el-form-item label="状态" prop="status">
          <el-radio-group v-model="form.status" class="status-radio">
            <el-radio label="在职">在职</el-radio>
            <el-radio label="离职">离职</el-radio>
          </el-radio-group>
        </el-form-item>
        <el-form-item label="剩余号源" prop="numCount">
          <el-input-number v-model="form.numCount" :min="0" :max="100" class="form-number" />
        </el-form-item>
        <el-form-item label="所属医院" prop="hospitalId">
          <el-select v-model="form.hospitalId" placeholder="请选择医院" class="form-select">
            <el-option v-for="hospital in hospitalOptions" :key="hospital.id" :label="hospital.hospitalName" :value="hospital.id" />
          </el-select>
        </el-form-item>
      </el-form>
      <template #footer>
        <el-button @click="dialogVisible = false">取消</el-button>
        <el-button type="primary" @click="handleSubmit" :loading="submitLoading">确定</el-button>
      </template>
    </el-dialog>
  </div>
</template>

<script setup>
import { ref, reactive, onMounted } from 'vue'
import { ElMessage, ElMessageBox } from 'element-plus'
import { Search, Refresh, Plus, Delete, Edit, CirclePlus, CircleClose } from '@element-plus/icons-vue'
import { doctorAPI } from '../api/doctor'
import { hospitalAPI } from '../api/hospital'
import { patientAPI } from '../api/patient'
import { registrationAPI } from '../api/registration'

const loading = ref(false)
const doctorList = ref([])
const selectedRows = ref([])
const currentPage = ref(1)
const pageSize = ref(6)
const total = ref(0)
const hospitalOptions = ref([])
const patientOptions = ref([])
const selectedPatients = reactive({})
const registeredPatientIds = ref(new Set())

const searchForm = reactive({
  doctorName: '',
  department: '',
  doctorTitle: ''
})

const dialogVisible = ref(false)
const dialogTitle = ref('')
const submitLoading = ref(false)
const formRef = ref(null)
const isEdit = ref(false)

const form = reactive({
  id: null,
  doctorName: '',
  doctorTitle: '',
  department: '',
  doctorCode: '',
  phone: '',
  status: '在职',
  numCount: 10,
  hospitalId: null
})

const formRules = {
  doctorName: [{ required: true, message: '请输入医生姓名', trigger: 'blur' }],
  doctorTitle: [{ required: true, message: '请选择职称', trigger: 'change' }],
  department: [{ required: true, message: '请输入科室', trigger: 'blur' }],
  hospitalId: [{ required: true, message: '请选择所属医院', trigger: 'change' }]
}

const fetchHospitalOptions = async () => {
  try {
    const res = await hospitalAPI.findHosList()
    if (res.code === '200' || res.code === '205') {
      hospitalOptions.value = res.data || []
    }
  } catch (error) {
    console.error('获取医院列表失败:', error)
  }
}

const fetchPatientOptions = async () => {
  try {
   const res = await patientAPI.findPatientList()
   //const res =await patientAPI.cancelRegistration()
    if (res.code === '200') {
      patientOptions.value = res.data || []
    }
    
    const registrationRes = await registrationAPI.getAll()
    if (registrationRes.code === '200' && registrationRes.data) {
      const registered = registrationRes.data.filter(r => r.status === '已挂号')
      registeredPatientIds.value = new Set(registered.map(r => r.patientId))
    }
  } catch (error) {
    console.error('获取患者列表失败:', error)
  }
}

const fetchData = async () => {
  loading.value = true
  try {
    let res
    if (searchForm.doctorName || searchForm.department || searchForm.doctorTitle ) {
      res = await doctorAPI.findDoctorByLike(searchForm.doctorName, searchForm.department, searchForm.doctorTitle)
    } else {
      res = await doctorAPI.findDoctorList()
    }
    if (res.code === '200') {
      const doctors = res.data || []
      doctorList.value = doctors.map(doctor => ({
        ...doctor,
        hospitalName: hospitalOptions.value.find(h => h.id === doctor.hospitalId)?.hospitalName || '未知'
      }))
      total.value = doctorList.value.length
    } else {
      ElMessage.error(res.msg || '获取数据失败')
    }
  } catch (error) {
    console.error('获取数据失败:', error)
  } finally {
    loading.value = false
  }
}

const handleSearch = () => {
  currentPage.value = 1
  fetchData()
}

const handleReset = () => {
  searchForm.doctorName = ''
  searchForm.department = ''
  searchForm.doctorTitle = ''
  currentPage.value = 1
  fetchData()
}

const handleSelectionChange = (selection) => {
  selectedRows.value = selection
}

const handleAdd = () => {
  dialogTitle.value = '新增医生'
  isEdit.value = false
  resetForm()
  dialogVisible.value = true
}

const handleEdit = (row) => {
  dialogTitle.value = '编辑医生'
  isEdit.value = true
  Object.assign(form, {
    id: row.id,
    doctorName: row.doctorName,
    doctorTitle: row.doctorTitle,
    department: row.department,
    doctorCode: row.doctorCode,
    phone: row.phone,
    status: row.status,
    numCount: row.numCount,
    hospitalId: row.hospitalId
  })
  dialogVisible.value = true
}

const handleDelete = async (row) => {
  try {
    await ElMessageBox.confirm(`确定要删除医生"${row.doctorName}"吗？`, '提示', {
      confirmButtonText: '确定',
      cancelButtonText: '取消',
      type: 'warning'
    })
    const res = await doctorAPI.deleteById(row.id)
    if (res.code === '200') {
      ElMessage.success('删除成功')
      fetchData()
    } else {
      ElMessage.error(res.msg || '删除失败')
    }
  } catch (error) {
    if (error !== 'cancel') {
      console.error('删除失败:', error)
    }
  }
}

const handleBatchDelete = async () => {
  if (selectedRows.value.length === 0) return
  try {
    await ElMessageBox.confirm(`确定要删除选中的${selectedRows.value.length}位医生吗？`, '提示', {
      confirmButtonText: '确定',
      cancelButtonText: '取消',
      type: 'warning'
    })
    const ids = selectedRows.value.map(row => row.id)
    const res = await doctorAPI.deleteByIds(ids)
    if (res.code === '200') {
      ElMessage.success('批量删除成功')
      fetchData()
    } else {
      ElMessage.error(res.msg || '批量删除失败')
    }
  } catch (error) {
    if (error !== 'cancel') {
      console.error('批量删除失败:', error)
    }
  }
}

const handleSubmit = async () => {
  if (!formRef.value) return
  try {
    await formRef.value.validate()
    submitLoading.value = true
    let res
    if (isEdit.value) {
      res = await doctorAPI.updateDoctor(form)
    } else {
      res = await doctorAPI.insertDoctor(form)
    }
    if (res.code === '200') {
      ElMessage.success(isEdit.value ? '更新成功' : '添加成功')
      dialogVisible.value = false
      fetchData()
    } else {
      ElMessage.error(res.msg || (isEdit.value ? '更新失败' : '添加失败'))
    }
  } catch (error) {
    console.error('提交失败:', error)
  } finally {
    submitLoading.value = false
  }
}

const handleRegistration = async (doctor) => {
  const patientId = selectedPatients[doctor.id]
  if (!patientId) {
    ElMessage.warning('请先选择患者')
    return
  }

  try {
    await ElMessageBox.confirm(`确定要为患者"${patientOptions.value.find(p => p.id === patientId)?.patientName}"挂号"${doctor.doctorName}"医生吗？`, '确认挂号', {
      confirmButtonText: '确定',
      cancelButtonText: '取消',
      type: 'warning'
    })

    const res = await registrationAPI.register({
      patientId: patientId,
      doctorId: doctor.id
    })

    if (res.code === '200') {
      ElMessage.success('挂号成功')
      doctor.numCount = (doctor.numCount || 0) - 1
      selectedPatients[doctor.id] = null
    } else {
      ElMessage.error(res.msg || '挂号失败')
    }
  } catch (error) {
    if (error !== 'cancel') {
      console.error('挂号失败:', error)
    }
  }
}

const handleCancelRegistration = async (doctor) => {
  const patientId = selectedPatients[doctor.id]
  if (!patientId) {
    ElMessage.warning('请先选择患者')
    return
  }

  try {
    const patientName = patientOptions.value.find(p => p.id === patientId)?.patientName
    await ElMessageBox.confirm(`确定要取消患者"${patientName}"在"${doctor.doctorName}"医生处的挂号吗？`, '确认取消', {
      confirmButtonText: '确定',
      cancelButtonText: '取消',
      type: 'warning'
    })

    const res = await registrationAPI.getByPatientId(patientId)
    if (res.code === '200' && res.data && res.data.length > 0) {
      const registration = res.data.find(r => r.doctorId === doctor.id && r.status === '已挂号')
      if (registration) {
        //const cancelRes = await registrationAPI.cancelRegistration(registration.id)
        const cancelRes = await registrationAPI.cancelRegistration(registration.id)
        if (cancelRes.code === '200') {
          ElMessage.success('取消成功')
          doctor.numCount = (doctor.numCount || 0) + 1
          selectedPatients[doctor.id] = null
          registeredPatientIds.value.delete(patientId)
        } else {
          ElMessage.error(cancelRes.msg || '取消失败')
        }
    } else {
       ElMessage.warning('该患者没有在该医生处的挂号记录')
      }
   } else {
      ElMessage.warning('没有找到该患者的挂号记录')
    }
  } catch (error) {
    if (error !== 'cancel') {
      console.error('取消失败:', error)
    }
  }
}

const getTitleType = (title) => {
  const typeMap = {
    '主任医师': 'danger',
    '副主任医师': 'warning',
    '主治医师': 'success',
    '住院医师': 'info'
  }
  return typeMap[title] || 'info'
}

const getCountType = (count) => {
  if (!count || count <= 0) return 'danger'
  if (count <= 5) return 'warning'
  return 'success'
}

const resetForm = () => {
  Object.assign(form, {
    id: null,
    doctorName: '',
    doctorTitle: '',
    department: '',
    doctorCode: '',
    phone: '',
    status: '在职',
    numCount: 10,
    hospitalId: null
  })
}

onMounted(() => {
  fetchHospitalOptions()
  fetchPatientOptions()
  fetchData()
})
</script>

<style scoped>
.doctor-page {
  padding: 20px;
}

.page-header {
  display: flex;
  justify-content: space-between;
  align-items: center;
  margin-bottom: 24px;
}

.page-title {
  font-size: 24px;
  font-weight: 600;
  color: #1D2129;
  margin: 0 0 8px 0;
}

.page-subtitle {
  font-size: 14px;
  color: #8F959E;
  margin: 0;
}

.search-card {
  background: #fff;
  border-radius: 12px;
  padding: 20px;
  margin-bottom: 20px;
  box-shadow: 0 2px 12px rgba(0, 0, 0, 0.04);
}

.search-form {
  margin-bottom: 0;
}

.search-input {
  width: 200px;
}

.search-select {
  width: 150px;
}

.card-container {
  display: grid;
  grid-template-columns: repeat(auto-fill, minmax(380px, 1fr));
  gap: 20px;
  margin-bottom: 20px;
}

.doctor-card {
  background: #fff;
  border-radius: 12px;
  overflow: hidden;
  box-shadow: 0 2px 12px rgba(0, 0, 0, 0.08);
  transition: all 0.3s ease;
  border: 1px solid #E5E6EB;
}

.doctor-card:hover {
  box-shadow: 0 4px 20px rgba(0, 0, 0, 0.12);
  transform: translateY(-2px);
}

.card-header {
  display: flex;
  justify-content: space-between;
  align-items: center;
  padding: 20px;
  background: linear-gradient(135deg, #667eea 0%, #764ba2 100%);
  color: #fff;
}

.doctor-avatar {
  display: flex;
  align-items: center;
  gap: 12px;
}

.avatar-circle {
  width: 50px;
  height: 50px;
  border-radius: 50%;
  background: rgba(255, 255, 255, 0.3);
  display: flex;
  align-items: center;
  justify-content: center;
  font-size: 24px;
  font-weight: 600;
}

.avatar-text {
  color: #fff;
}

.doctor-name {
  font-size: 18px;
  font-weight: 600;
  margin: 0 0 4px 0;
}

.doctor-title {
  font-size: 13px;
  opacity: 0.9;
  margin: 0;
}

.card-actions {
  display: flex;
  gap: 8px;
}

.card-actions :deep(.el-button) {
  color: #fff;
  border-color: transparent;
}

.card-actions :deep(.el-button:hover) {
  background: rgba(255, 255, 255, 0.2);
}

.card-body {
  padding: 20px;
}

.info-row {
  display: flex;
  align-items: center;
  margin-bottom: 12px;
  font-size: 14px;
}

.info-label {
  color: #8F959E;
  width: 70px;
  flex-shrink: 0;
}

.info-value {
  color: #1D2129;
  font-weight: 500;
}

.card-footer {
  padding: 16px 20px;
  border-top: 1px solid #E5E6EB;
  background: #F7F8FA;
}

.patient-select {
  margin-bottom: 12px;
}

.patient-selector {
  width: 100%;
}

.action-buttons {
  display: flex;
  gap: 12px;
}

.action-buttons :deep(.el-button) {
  flex: 1;
}

.empty-state {
  grid-column: 1 / -1;
  padding: 60px 20px;
  text-align: center;
}

.pagination-wrapper {
  display: flex;
  justify-content: center;
  margin-top: 20px;
}

.pagination {
  margin: 0;
}

.form-dialog :deep(.el-dialog) {
  border-radius: 12px;
}

.form-content {
  padding: 8px 0;
}

.form-input, .form-select {
  width: 100%;
}

.form-number {
  width: 100%;
}

.status-radio {
  display: flex;
  gap: 24px;
}
</style>