<template>
  <div class="stat-card" :class="{ 'trend-up': trend > 0, 'trend-down': trend < 0 }">
    <div class="card-icon" :style="{ background: iconBg }">
      <component :is="icon" :style="{ color: iconColor }" />
    </div>
    <div class="card-content">
      <p class="card-label">{{ label }}</p>
      <p class="card-value">{{ value }}</p>
      <div class="card-trend">
        <component :is="trend > 0 ? ArrowUp : ArrowDown" />
        <span>{{ Math.abs(trend) }}%</span>
        <span class="trend-label">较昨日</span>
      </div>
    </div>
  </div>
</template>

<script setup>
import { ArrowUp, ArrowDown } from 'lucide-vue-next'

defineProps({
  label: {
    type: String,
    required: true
  },
  value: {
    type: [String, Number],
    required: true
  },
  icon: {
    type: Object,
    required: true
  },
  iconBg: {
    type: String,
    default: 'linear-gradient(135deg, #e8f0ff 0%, #d4e4ff 100%)'
  },
  iconColor: {
    type: String,
    default: '#165DFF'
  },
  trend: {
    type: Number,
    default: 0
  }
})
</script>

<style scoped>
.stat-card {
  background: #fff;
  border-radius: 12px;
  padding: 20px;
  display: flex;
  align-items: center;
  box-shadow: 0 2px 12px rgba(0, 0, 0, 0.04);
  transition: all 0.3s ease;
}

.stat-card:hover {
  transform: translateY(-2px);
  box-shadow: 0 4px 16px rgba(0, 0, 0, 0.08);
}

.card-icon {
  width: 56px;
  height: 56px;
  border-radius: 12px;
  display: flex;
  align-items: center;
  justify-content: center;
  margin-right: 20px;
  flex-shrink: 0;
}

.card-icon svg {
  width: 24px;
  height: 24px;
}

.card-content {
  flex: 1;
}

.card-label {
  font-size: 13px;
  color: #8F959E;
  margin: 0 0 8px 0;
}

.card-value {
  font-size: 28px;
  font-weight: 600;
  color: #1D2129;
  margin: 0 0 8px 0;
}

.card-trend {
  display: flex;
  align-items: center;
  font-size: 12px;
}

.card-trend svg {
  width: 14px;
  height: 14px;
  margin-right: 4px;
}

.trend-up .card-trend {
  color: #00B42A;
}

.trend-down .card-trend {
  color: #F53F3F;
}

.trend-label {
  margin-left: 4px;
  color: #8F959E;
}
</style>
