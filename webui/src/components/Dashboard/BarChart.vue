<template>
  <div class="chart-card">
    <div class="card-header">
      <h3 class="card-title">{{ title }}</h3>
    </div>
    <div class="chart-container">
      <v-chart :option="chartOption" autoresize />
    </div>
  </div>
</template>

<script setup>
import { computed } from 'vue'
import VChart from 'vue-echarts'
import { use } from 'echarts/core'
import { CanvasRenderer } from 'echarts/renderers'
import { BarChart } from 'echarts/charts'
import {
  GridComponent,
  TooltipComponent
} from 'echarts/components'

use([CanvasRenderer, BarChart, GridComponent, TooltipComponent])

const props = defineProps({
  title: {
    type: String,
    default: ''
  },
  data: {
    type: Object,
    default: () => ({ labels: [], values: [] })
  }
})

const chartOption = computed(() => ({
  tooltip: {
    trigger: 'axis',
    backgroundColor: 'rgba(255, 255, 255, 0.95)',
    borderColor: '#e8e8e8',
    borderWidth: 1,
    textStyle: {
      color: '#1D2129'
    },
    formatter: '{b}<br/>患者数量: {c}人'
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
    data: props.data.labels.length > 0 ? props.data.labels : ['暂无数据'],
    axisLine: {
      lineStyle: {
        color: '#e8e8e8'
      }
    },
    axisLabel: {
      color: '#8F959E',
      fontSize: 12,
      rotate: props.data.labels.length > 5 ? 30 : 0
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
      type: 'bar',
      barWidth: '50%',
      itemStyle: {
        borderRadius: [6, 6, 0, 0],
        color: {
          type: 'linear',
          x: 0,
          y: 0,
          x2: 0,
          y2: 1,
          colorStops: [
            { offset: 0, color: '#165DFF' },
            { offset: 1, color: '#4080FF' }
          ]
        }
      },
      data: props.data.values.length > 0 ? props.data.values : [0]
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
  margin-bottom: 20px;
}

.card-title {
  font-size: 16px;
  font-weight: 600;
  color: #1D2129;
  margin: 0;
}

.chart-container {
  height: 280px;
}
</style>