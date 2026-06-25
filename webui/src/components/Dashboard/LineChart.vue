<template>
  <div class="chart-card">
    <div class="card-header">
      <h3 class="card-title">{{ title }}</h3>
      <div class="card-actions">
        <el-select v-model="timeRange" size="small" class="time-select">
          <el-option label="近7日" value="7" />
          <el-option label="近30日" value="30" />
          <el-option label="近90日" value="90" />
        </el-select>
      </div>
    </div>
    <div class="chart-container">
      <v-chart :option="chartOption" autoresize />
    </div>
  </div>
</template>

<script setup>
import { ref, computed } from 'vue'
import VChart from 'vue-echarts'
import { use } from 'echarts/core'
import { CanvasRenderer } from 'echarts/renderers'
import { LineChart } from 'echarts/charts'
import {
  GridComponent,
  TooltipComponent,
  LegendComponent
} from 'echarts/components'

use([CanvasRenderer, LineChart, GridComponent, TooltipComponent, LegendComponent])

defineProps({
  title: {
    type: String,
    default: ''
  }
})

const timeRange = ref('7')

const chartOption = computed(() => ({
  tooltip: {
    trigger: 'axis',
    backgroundColor: 'rgba(255, 255, 255, 0.95)',
    borderColor: '#e8e8e8',
    borderWidth: 1,
    textStyle: {
      color: '#1D2129'
    },
    formatter: '{b}<br/>挂号量: {c}'
  },
  grid: {
    left: '3%',
    right: '4%',
    bottom: '3%',
    top: '10%',
    containLabel: true
  },
  xAxis: {
    type: 'category',
    boundaryGap: false,
    data: ['6/1', '6/2', '6/3', '6/4', '6/5', '6/6', '6/7'],
    axisLine: {
      lineStyle: {
        color: '#e8e8e8'
      }
    },
    axisLabel: {
      color: '#8F959E',
      fontSize: 12
    }
  },
  yAxis: {
    type: 'value',
    axisLine: {
      show: false
    },
    axisTick: {
      show: false
    },
    splitLine: {
      lineStyle: {
        color: '#f5f7fa'
      }
    },
    axisLabel: {
      color: '#8F959E',
      fontSize: 12
    }
  },
  series: [
    {
      name: '挂号量',
      type: 'line',
      smooth: true,
      symbol: 'circle',
      symbolSize: 8,
      lineStyle: {
        width: 3,
        color: '#165DFF'
      },
      itemStyle: {
        color: '#165DFF',
        borderWidth: 2,
        borderColor: '#fff'
      },
      areaStyle: {
        color: {
          type: 'linear',
          x: 0,
          y: 0,
          x2: 0,
          y2: 1,
          colorStops: [
            { offset: 0, color: 'rgba(22, 93, 255, 0.2)' },
            { offset: 1, color: 'rgba(22, 93, 255, 0.02)' }
          ]
        }
      },
      data: [120, 150, 135, 180, 165, 200, 190]
    }
  ]
}))
</script>

<style scoped>
.chart-card {
  background: #fff;
  border-radius: 12px;
  padding: 20px;
  box-shadow: 0 2px 12px rgba(0, 0, 0, 0.04);
}

.card-header {
  display: flex;
  justify-content: space-between;
  align-items: center;
  margin-bottom: 20px;
}

.card-title {
  font-size: 16px;
  font-weight: 600;
  color: #1D2129;
  margin: 0;
}

.time-select {
  width: 100px;
}

.chart-container {
  height: 280px;
}
</style>
