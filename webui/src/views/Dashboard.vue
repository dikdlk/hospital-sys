<template>
  <div class="dashboard">
    <div class="page-header">
      <h2 class="page-title">数据概览</h2>
      <p class="page-subtitle">实时监控医院运营数据</p>
    </div>
    
    <div class="stats-grid">
      <StatCard
        label="医院总数"
        :value="hosCount"
        :icon="Calendar"
        icon-bg="linear-gradient(135deg, #e8f0ff 0%, #d4e4ff 100%)"
        icon-color="#165DFF"
      />
      <StatCard
        label="患者总数"
        :value="patCount"
        :icon="Users"
        icon-bg="linear-gradient(135deg, #e8fff0 0%, #d4ffdf 100%)"
        icon-color="#00B42A"
      />
      <StatCard
        label="患者平均年龄"
        :value="avgAge"
        :icon="User"
        icon-bg="linear-gradient(135deg, #fff7e8 0%, #ffe8d4 100%)"
        icon-color="#FF7D00"
      />
      <StatCard
        label="年龄分组数"
        :value="ageGroupCount"
        :icon="FileText"
        icon-bg="linear-gradient(135deg, #f0e8ff 0%, #e4d4ff 100%)"
        icon-color="#7B68EE"
      />
    </div>
    
    <div class="charts-row">
      <div class="chart-col">
        <BarChart title="各医院患者数量" :data="hosPatData" />
      </div>
      <div class="chart-col">
        <PieChart title="患者年龄分布" :data="patAgeData" />
      </div>
    </div>
  </div>
</template>

<script setup>
import { ref, computed, onMounted } from 'vue'
import { Calendar, Users, User, FileText } from 'lucide-vue-next'
import StatCard from '../components/Dashboard/StatCard.vue'
import BarChart from '../components/Dashboard/BarChart.vue'
import PieChart from '../components/Dashboard/PieChart.vue'
import { staAPI } from '../api/hospital.js'

const hosCount = ref('-')
const patCount = ref('-')
const patAge = ref([])
const hosPat = ref([])

const ageGroupLabels = {
  'A': '0-19岁',
  'B': '20-29岁',
  'C': '30-39岁',
  'D': '40-49岁'
}

const avgAge = computed(() => {
  if (!patAge.value || patAge.value.length === 0) return '-'
  
  const ageGroupMidpoints = {
    'A': 10,
    'B': 25,
    'C': 35,
    'D': 45
  }
  
  let total = 0
  let count = 0
  
  const firstRow = patAge.value[0]
  if (firstRow) {
    for (const [group, num] of Object.entries(firstRow)) {
      const midpoint = ageGroupMidpoints[group]
      if (midpoint && typeof num === 'number') {
        total += midpoint * num
        count += num
      }
    }
  }
  
  return count > 0 ? Math.round(total / count) + '岁' : '-'
})

const ageGroupCount = computed(() => {
  if (!patAge.value || patAge.value.length === 0) return '-'
  const firstRow = patAge.value[0]
  return firstRow ? Object.keys(firstRow).length + '组' : '-'
})

const patAgeData = computed(() => {
  const data = []
  const colors = ['#165DFF', '#4080FF', '#7B68EE', '#A78BFA', '#C4B5FD']
  let colorIndex = 0
  
  if (patAge.value && patAge.value.length > 0) {
    const firstRow = patAge.value[0]
    if (firstRow) {
      for (const [group, value] of Object.entries(firstRow)) {
        if (typeof value === 'number') {
          data.push({
            name: ageGroupLabels[group] || group,
            value: value,
            itemStyle: { color: colors[colorIndex % colors.length] }
          })
          colorIndex++
        }
      }
    }
  }
  
  return data.length > 0 ? data : [{ value: 1, name: '暂无数据', itemStyle: { color: '#ccc' } }]
})

const hosPatData = computed(() => {
  const labels = []
  const values = []
  
  if (hosPat.value && hosPat.value.length > 0) {
    for (const item of hosPat.value) {
      if (item && item.hospital_name !== undefined) {
        labels.push(item.hospital_name)
        values.push(item.count || 0)
      }
    }
  }
  
  return { 
    labels: labels.length > 0 ? labels : ['暂无数据'], 
    values: values.length > 0 ? values : [0] 
  }
})

const loadData = async () => {
  try {
    const [hosRes, patRes, ageRes, hosPatRes] = await Promise.all([
      staAPI.getHosCount(),
      staAPI.getPatCount(),
      staAPI.getPatAge(),
      staAPI.getHosPatCount()
    ])
    
    if (hosRes.code === '200') {
      hosCount.value = hosRes.data
    }
    if (patRes.code === '200') {
      patCount.value = patRes.data
    }
    if (ageRes.code === '200') {
      patAge.value = ageRes.data
    }
    if (hosPatRes.code === '200') {
      hosPat.value = hosPatRes.data
    }
  } catch (error) {
    console.error('加载数据失败:', error)
  }
}

onMounted(() => {
  loadData()
})
</script>

<style scoped>
.dashboard {
  padding: 20px;
}

.page-header {
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

.stats-grid {
  display: grid;
  grid-template-columns: repeat(4, 1fr);
  gap: 20px;
  margin-bottom: 24px;
}

.charts-row {
  display: grid;
  grid-template-columns: repeat(2, 1fr);
  gap: 20px;
  margin-bottom: 24px;
}

.chart-col {
  flex: 1;
}

@media (max-width: 1200px) {
  .stats-grid {
    grid-template-columns: repeat(2, 1fr);
  }
  
  .charts-row {
    grid-template-columns: 1fr;
  }
}
</style>