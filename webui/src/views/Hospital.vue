<template>
  <div class="hospital-page">
    <div class="page-header">
      <div class="header-left">
        <h2 class="page-title">医院管理</h2>
        <p class="page-subtitle">管理医院基本信息</p>
      </div>
      <div class="header-right">
        <el-button type="primary" @click="handleAdd" :icon="Plus">新增医院</el-button>
      </div>
    </div>
    
    <div class="search-card">
      <el-form :inline="true" :model="searchForm" class="search-form">
        <el-form-item label="医院名称">
          <el-input v-model="searchForm.hosName" placeholder="请输入医院名称" clearable class="search-input" />
        </el-form-item>
        <el-form-item label="医院等级">
          <el-select v-model="searchForm.hosLevel" placeholder="请选择医院等级" class="search-select">
            <el-option label="全部" value="all" />
            <el-option label="三甲" value="三甲" />
            <el-option label="三乙" value="三乙" />
            <el-option label="二甲" value="二甲" />
            <el-option label="二乙" value="二乙" />
            <el-option label="一甲" value="一甲" />
            <el-option label="一乙" value="一乙" />
          </el-select>
        </el-form-item>
        <el-form-item>
          <el-button type="primary" @click="handleSearch" :icon="Search">查询</el-button>
          <el-button @click="handleReset" :icon="Refresh">重置</el-button>
          <el-button @click="handleExport" :icon="Download">导出</el-button>
        </el-form-item>
      </el-form>
    </div>
    
    <div class="table-card">
      <el-table
        :data="hospitalList"
        v-loading="loading"
        @selection-change="handleSelectionChange"
        stripe
        class="data-table"
      >
        <el-table-column type="selection" width="55" />
        <el-table-column prop="id" label="ID" width="80" />
        <el-table-column prop="hospitalName" label="医院名称" min-width="180" show-overflow-tooltip />
        <el-table-column prop="hospitalType" label="医院类型" width="120" />
        <el-table-column prop="hospitalLevel" label="医院等级" width="100">
          <template #default="{ row }">
            <el-tag :type="getLevelType(row.hospitalLevel)">{{ row.hospitalLevel }}</el-tag>
          </template>
        </el-table-column>
        <el-table-column prop="hospitalCode" label="医院编码" width="150" />
        <el-table-column prop="hospitalAddress" label="医院地址" min-width="200" show-overflow-tooltip />
        <el-table-column prop="hospitalStatus" label="状态" width="100">
          <template #default="{ row }">
            <el-tag :type="getStatusType(row.hospitalStatus)">{{ row.hospitalStatus }}</el-tag>
          </template>
        </el-table-column>
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
        <el-pagination
          v-model:current-page="currentPage"
          v-model:page-size="pageSize"
          :page-sizes="[10, 20, 50, 100]"
          :total="total"
          layout="total, sizes, prev, pager, next, jumper"
          class="pagination"
        />
      </div>
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
        <el-form-item label="医院名称" prop="hospitalName">
          <el-input v-model="form.hospitalName" placeholder="请输入医院名称" class="form-input" />
        </el-form-item>
        <el-form-item label="医院类型" prop="hospitalType">
          <el-select v-model="form.hospitalType" placeholder="请选择医院类型" class="form-select">
            <el-option label="综合医院" value="综合医院" />
            <el-option label="专科医院" value="专科医院" />
            <el-option label="中医医院" value="中医医院" />
            <el-option label="妇幼保健院" value="妇幼保健院" />
            <el-option label="社区医院" value="社区医院" />
              <el-option label="精神病院" value="精神病医院" />
          </el-select>
        </el-form-item>
        <el-form-item label="医院等级" prop="hospitalLevel">
          <el-select v-model="form.hospitalLevel" placeholder="请选择医院等级" class="form-select">
            <el-option label="三甲" value="三甲" />
            <el-option label="三乙" value="三乙" />
            <el-option label="二甲" value="二甲" />
            <el-option label="二乙" value="二乙" />
            <el-option label="一甲" value="一甲" />
            <el-option label="一乙" value="一乙" />
          </el-select>
        </el-form-item>
        <el-form-item label="医院编码" prop="hospitalCode">
          <el-input v-model="form.hospitalCode" placeholder="请输入医院编码" class="form-input" />
        </el-form-item>
        <el-form-item label="医院地址" prop="hospitalAddress">
          <el-input v-model="form.hospitalAddress" placeholder="请输入医院地址" type="textarea" :rows="2" class="form-textarea" />
        </el-form-item>
        <el-form-item label="状态" prop="hospitalStatus">
          <el-radio-group v-model="form.hospitalStatus" class="status-radio">
            <el-radio label="正常">正常</el-radio>
            <el-radio label="暂停">暂停</el-radio>
          </el-radio-group>
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
import { hospitalAPI } from '../api/hospital'

const loading = ref(false)
const hospitalList = ref([])
const selectedRows = ref([])
const currentPage = ref(1)
const pageSize = ref(10)
const total = ref(0)

const searchForm = reactive({
  hosName: '',
  hosLevel: 'all'
})

const dialogVisible = ref(false)
const dialogTitle = ref('')
const submitLoading = ref(false)
const formRef = ref(null)
const isEdit = ref(false)

const form = reactive({
  id: null,
  hospitalName: '',
  hospitalType: '',
  hospitalLevel: '',
  hospitalCode: '',
  hospitalAddress: '',
  hospitalStatus: '正常'
})

const formRules = {
  hospitalName: [{ required: true, message: '请输入医院名称', trigger: 'blur' }],
  hospitalType: [{ required: true, message: '请选择医院类型', trigger: 'change' }],
  hospitalLevel: [{ required: true, message: '请选择医院等级', trigger: 'change' }],
  hospitalCode: [{ required: true, message: '请输入医院编码', trigger: 'blur' }],
  hospitalStatus: [{ required: true, message: '请选择状态', trigger: 'change' }]
}

const getLevelType = (level) => {
  const typeMap = {
    '三甲': 'success',
    '三乙': 'success',
    '二甲': 'warning',
    '二乙': 'warning',
    '一甲': 'info',
    '一乙': 'info'
  }
  return typeMap[level] || ''
}

const getStatusType = (status) => {
  if (!status || status === '' || status === null) return 'info'
  const successStatus = ['正常', '营业', '运行']
  const warningStatus = ['暂停', '停业', '关闭']
  if (successStatus.includes(status)) return 'success'
  if (warningStatus.includes(status)) return 'warning'
  return 'info'
}

const fetchData = async () => {
  loading.value = true
  try {
    let res
    const level = searchForm.hosLevel === 'all' ? '' : searchForm.hosLevel
    if (searchForm.hosName || level) {
      res = await hospitalAPI.findHosByLikeName(searchForm.hosName, level)
    } else {
      res = await hospitalAPI.findHosList()
    }
    if (res.code === '200' || res.code === '205') {
      hospitalList.value = res.data || []
      total.value = hospitalList.value.length
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
  searchForm.hosName = ''
  searchForm.hosLevel = 'all'
  currentPage.value = 1
  fetchData()
}

const handleSelectionChange = (selection) => {
  selectedRows.value = selection
}

const handleAdd = () => {
  dialogTitle.value = '新增医院'
  isEdit.value = false
  resetForm()
  dialogVisible.value = true
}

const handleEdit = (row) => {
  dialogTitle.value = '编辑医院'
  isEdit.value = true
  Object.assign(form, {
    id: row.id,
    hospitalName: row.hospitalName,
    hospitalType: row.hospitalType,
    hospitalLevel: row.hospitalLevel,
    hospitalCode: row.hospitalCode,
    hospitalAddress: row.hospitalAddress,
    hospitalStatus: row.hospitalStatus
  })
  dialogVisible.value = true
}

const handleDelete = async (row) => {
  try {
    await ElMessageBox.confirm(`确定要删除医院"${row.hospitalName}"吗？`, '提示', {
      confirmButtonText: '确定',
      cancelButtonText: '取消',
      type: 'warning'
    })
    const res = await hospitalAPI.deleteById(row.id)
    if (res.code === '202') {
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
    await ElMessageBox.confirm(`确定要删除选中的${selectedRows.value.length}家医院吗？`, '提示', {
      confirmButtonText: '确定',
      cancelButtonText: '取消',
      type: 'warning'
    })
    const ids = selectedRows.value.map(row => row.id)
    const res = await hospitalAPI.deleteByIds(ids)
    if (res.code === '203') {
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
      res = await hospitalAPI.updateHos(form)
    } else {
      res = await hospitalAPI.insertHos(form)
    }
    if (res.code === '201' || res.code === '204') {
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
  if (hospitalList.value.length === 0) {
    ElMessage.warning('没有数据可导出')
    return
  }
  try {
    const XLSX = await import('xlsx')
    const exportData = hospitalList.value.map(item => ({
      'ID': item.id,
      '医院名称': item.hospitalName,
      '医院类型': item.hospitalType,
      '医院等级': item.hospitalLevel,
      '医院编码': item.hospitalCode,
      '医院地址': item.hospitalAddress,
      '状态': item.hospatalStatus
    }))
    const worksheet = XLSX.utils.json_to_sheet(exportData)
    const workbook = XLSX.utils.book_new()
    XLSX.utils.book_append_sheet(workbook, worksheet, '医院列表')
    worksheet['!cols'] = [
      { wch: 10 }, { wch: 25 }, { wch: 15 }, { wch: 10 }, { wch: 15 }, { wch: 30 }, { wch: 10 }
    ]
    XLSX.writeFile(workbook, `医院列表_${new Date().toLocaleDateString('zh-CN').replace(/\//g, '-')}.xlsx`)
    ElMessage.success('导出成功')
  } catch (error) {
    console.error('导出失败:', error)
    ElMessage.error('导出失败')
  }
}

const resetForm = () => {
  Object.assign(form, {
    id: null,
    hospitalName: '',
    hospitalType: '',
    hospitalLevel: '',
    hospitalCode: '',
    hospitalAddress: '',
    hospitalStatus: '正常'
  })
}

onMounted(() => {
  fetchData()
})
</script>

<style scoped>
.hospital-page {
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
  width: 240px;
}

.search-select {
  width: 150px;
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

.form-dialog :deep(.el-dialog) {
  border-radius: 12px;
}

.form-content {
  padding: 8px 0;
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
