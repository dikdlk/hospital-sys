<template>
  <div class="patient-page">
    <div class="page-header">
      <div class="header-left">
        <h2 class="page-title">患者管理</h2>
        <p class="page-subtitle">管理患者基本信息</p>
      </div>
      <div class="header-right">
        <el-button type="primary" @click="handleAdd" :icon="Plus">新增患者</el-button>
      </div>
    </div>

    <div class="search-card">
      <el-form :inline="true" :model="searchForm" class="search-form">
        <el-form-item label="患者姓名">
          <el-input v-model="searchForm.patientName" placeholder="请输入患者姓名" clearable class="search-input" />
        </el-form-item>
        <el-form-item label="手机号码">
          <el-input v-model="searchForm.patientPhone" placeholder="请输入手机号码" clearable class="search-input" />
        </el-form-item>
        <el-form-item label="身份证号">
          <el-input v-model="searchForm.patientIdcard" placeholder="请输入身份证号" clearable class="search-input" />
        </el-form-item>
        <el-form-item>
          <el-button type="primary" @click="handleSearch" :icon="Search">查询</el-button>
          <el-button @click="handleReset" :icon="Refresh">重置</el-button>
          <el-button @click="handleExport" :icon="Download">导出</el-button>
        </el-form-item>
      </el-form>
    </div>

    <div class="table-card">
      <el-table :data="patientList" v-loading="loading" @selection-change="handleSelectionChange" stripe class="data-table">
        <el-table-column type="selection" width="55" />
        <el-table-column prop="id" label="ID" width="80" />
        <el-table-column prop="patientName" label="患者姓名" min-width="120" show-overflow-tooltip />
        <el-table-column prop="patientGender" label="性别" width="80">
          <template #default="{ row }">
            <el-tag :type="row.patientGender === '男' ? 'primary' : 'success'">{{ row.patientGender }}</el-tag>
          </template>
        </el-table-column>
        <el-table-column prop="patientAge" label="年龄" width="80" />
        <el-table-column prop="patientPhone" label="手机号码" width="130" />
        <el-table-column prop="patientIdcard" label="身份证号" width="180" />
        <el-table-column prop="patientBloodType" label="血型" width="80" />
        <el-table-column prop="hospitalName" label="所属医院" min-width="180" show-overflow-tooltip />
        <el-table-column label="操作" width="180" fixed="right">
          <template #default="{ row }">
            <el-button link type="primary" @click="handleEdit(row)" :icon="Edit">编辑</el-button>
            <el-button link type="danger" @click="handleDelete(row)" :icon="Delete">删除</el-button>
          </template>
        </el-table-column>
      </el-table>

      <div class="pagination-wrapper">
        <div class="batch-actions">
          <el-button type="danger" @click="handleBatchDelete" :icon="Delete" :disabled="selectedRows.length === 0">
            批量删除 ({{ selectedRows.length }})
          </el-button>
        </div>
        <el-pagination v-model:current-page="currentPage" v-model:page-size="pageSize" :page-sizes="[10, 20, 50, 100]" :total="total" layout="total, sizes, prev, pager, next, jumper" class="pagination" />
      </div>
    </div>

    <el-dialog v-model="dialogVisible" :title="dialogTitle" width="600px" :close-on-click-modal="false" class="form-dialog">
      <el-form ref="formRef" :model="form" :rules="formRules" label-width="100px" class="form-content">
        <el-form-item label="患者姓名" prop="patientName">
          <el-input v-model="form.patientName" placeholder="请输入患者姓名" class="form-input" />
        </el-form-item>
        <el-form-item label="性别" prop="patientGender">
          <el-radio-group v-model="form.patientGender" class="status-radio">
            <el-radio label="男">男</el-radio>
            <el-radio label="女">女</el-radio>
          </el-radio-group>
        </el-form-item>
        <el-form-item label="年龄" prop="patientAge">
          <el-input v-model.number="form.patientAge" placeholder="请输入年龄" class="form-input" type="number" />
        </el-form-item>
        <el-form-item label="手机号码" prop="patientPhone">
          <el-input v-model="form.patientPhone" placeholder="请输入手机号码" class="form-input" />
        </el-form-item>
        <el-form-item label="身份证号" prop="patientIdcard">
          <el-input v-model="form.patientIdcard" placeholder="请输入身份证号" class="form-input" />
        </el-form-item>
        <el-form-item label="出生日期" prop="patientBirthday">
          <el-date-picker v-model="form.patientBirthday" type="date" placeholder="请选择出生日期" class="form-input" />
        </el-form-item>
        <el-form-item label="血型" prop="patientBloodType">
          <el-select v-model="form.patientBloodType" placeholder="请选择血型" class="form-select">
            <el-option label="A型" value="A型" />
            <el-option label="B型" value="B型" />
            <el-option label="AB型" value="AB型" />
            <el-option label="O型" value="O型" />
          </el-select>
        </el-form-item>
        <el-form-item label="过敏史" prop="patientAllergy">
          <el-input v-model="form.patientAllergy" placeholder="请输入过敏史" type="textarea" :rows="2" class="form-textarea" />
        </el-form-item>
        <el-form-item label="家庭住址" prop="patientAddress">
          <el-input v-model="form.patientAddress" placeholder="请输入家庭住址" type="textarea" :rows="2" class="form-textarea" />
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
import { Search, Refresh, Plus, Delete, Edit, Download } from '@element-plus/icons-vue'
import { patientAPI } from '../api/patient'
import { hospitalAPI } from '../api/hospital'

const loading = ref(false)
const patientList = ref([])
const selectedRows = ref([])
const currentPage = ref(1)
const pageSize = ref(10)
const total = ref(0)
const hospitalOptions = ref([])

const searchForm = reactive({
  patientName: '',
  patientPhone: '',
  patientIdcard: ''
})

const dialogVisible = ref(false)
const dialogTitle = ref('')
const submitLoading = ref(false)
const formRef = ref(null)
const isEdit = ref(false)

const form = reactive({
  id: null,
  patientName: '',
  patientPhone: '',
  patientIdcard: '',
  patientGender: '男',
  patientAge: null,
  patientBirthday: '',
  patientAddress: '',
  patientBloodType: '',
  patientAllergy: '',
  hospitalId: null
})

const formRules = {
  patientName: [{ required: true, message: '请输入患者姓名', trigger: 'blur' }],
  patientGender: [{ required: true, message: '请选择性别', trigger: 'change' }],
  patientAge: [{ required: true, message: '请输入年龄', trigger: 'blur' }],
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

const fetchData = async () => {
  loading.value = true
  try {
    let res
    if (searchForm.patientName || searchForm.patientPhone || searchForm.patientIdcard) {
      res = await patientAPI.findPatientByLike(searchForm.patientName, searchForm.patientPhone, searchForm.patientIdcard)
    } else {
      res = await patientAPI.findPatientList()
    }
    if (res.code === '200') {
      const patients = res.data || []
      patientList.value = patients.map(patient => ({
        ...patient,
        hospitalName: hospitalOptions.value.find(h => h.id === patient.hospitalId)?.hospitalName || '未知'
      }))
      total.value = patientList.value.length
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
  searchForm.patientName = ''
  searchForm.patientPhone = ''
  searchForm.patientIdcard = ''
  currentPage.value = 1
  fetchData()
}

const handleSelectionChange = (selection) => {
  selectedRows.value = selection
}

const handleAdd = () => {
  dialogTitle.value = '新增患者'
  isEdit.value = false
  resetForm()
  dialogVisible.value = true
}

const handleEdit = (row) => {
  dialogTitle.value = '编辑患者'
  isEdit.value = true
  Object.assign(form, {
    id: row.id,
    patientName: row.patientName,
    patientPhone: row.patientPhone,
    patientIdcard: row.patientIdcard,
    patientGender: row.patientGender,
    patientAge: row.patientAge,
    patientBirthday: row.patientBirthday,
    patientAddress: row.patientAddress,
    patientBloodType: row.patientBloodType,
    patientAllergy: row.patientAllergy,
    hospitalId: row.hospitalId
  })
  dialogVisible.value = true
}

const handleDelete = async (row) => {
  try {
    await ElMessageBox.confirm(`确定要删除患者"${row.patientName}"吗？`, '提示', {
      confirmButtonText: '确定',
      cancelButtonText: '取消',
      type: 'warning'
    })
    const res = await patientAPI.deleteById(row.id)
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
    await ElMessageBox.confirm(`确定要删除选中的${selectedRows.value.length}位患者吗？`, '提示', {
      confirmButtonText: '确定',
      cancelButtonText: '取消',
      type: 'warning'
    })
    const ids = selectedRows.value.map(row => row.id)
    const res = await patientAPI.deleteByIds(ids)
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
      res = await patientAPI.updatePatient(form)
    } else {
      res = await patientAPI.insertPatient(form)
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

const handleExport = async () => {
  if (patientList.value.length === 0) {
    ElMessage.warning('没有数据可导出')
    return
  }
  try {
    const XLSX = await import('xlsx')
    const exportData = patientList.value.map(item => ({
      'ID': item.id,
      '患者姓名': item.patientName,
      '性别': item.patientGender,
      '年龄': item.patientAge,
      '手机号码': item.patientPhone,
      '身份证号': item.patientIdcard,
      '出生日期': item.patientBirthday,
      '血型': item.patientBloodType,
      '过敏史': item.patientAllergy,
      '家庭住址': item.patientAddress,
      '所属医院': item.hospitalName
    }))
    const worksheet = XLSX.utils.json_to_sheet(exportData)
    const workbook = XLSX.utils.book_new()
    XLSX.utils.book_append_sheet(workbook, worksheet, '患者列表')
    worksheet['!cols'] = [
      { wch: 8 }, { wch: 12 }, { wch: 8 }, { wch: 8 }, { wch: 15 },
      { wch: 18 }, { wch: 15 }, { wch: 8 }, { wch: 30 }, { wch: 30 }, { wch: 25 }
    ]
    XLSX.writeFile(workbook, `患者列表_${new Date().toLocaleDateString('zh-CN').replace(/\//g, '-')}.xlsx`)
    ElMessage.success('导出成功')
  } catch (error) {
    console.error('导出失败:', error)
    ElMessage.error('导出失败')
  }
}

const resetForm = () => {
  Object.assign(form, {
    id: null,
    patientName: '',
    patientPhone: '',
    patientIdcard: '',
    patientGender: '男',
    patientAge: null,
    patientBirthday: '',
    patientAddress: '',
    patientBloodType: '',
    patientAllergy: '',
    hospitalId: null
  })
}

onMounted(() => {
  fetchHospitalOptions()
  fetchData()
})
</script>

<style scoped>
.patient-page {
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

.table-card {
  background: #fff;
  border-radius: 12px;
  padding: 20px;
  box-shadow: 0 2px 12px rgba(0, 0, 0, 0.04);
}

.data-table {
  border-radius: 8px;
}

.data-table :deep(.el-table th) {
  background: #f5f7fa;
  color: #606266;
  font-weight: 500;
  font-size: 13px;
}

.data-table :deep(.el-table td) {
  font-size: 13px;
  color: #4E5969;
}

.pagination-wrapper {
  display: flex;
  justify-content: space-between;
  align-items: center;
  margin-top: 20px;
}

.batch-actions {
  display: flex;
  align-items: center;
}

.pagination {
  margin: 0;
}

.form-input, .form-select {
  width: 100%;
}

.form-textarea {
  width: 100%;
}

.status-radio {
  display: flex;
  gap: 24px;
}
</style>