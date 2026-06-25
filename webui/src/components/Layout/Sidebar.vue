<template>
  <aside
    class="sidebar"
    :class="{ collapsed: isCollapsed }"
  >
    <div class="sidebar-logo">
      <div class="logo-icon">
        <component :is="Hospital" />
      </div>
      <span v-show="!isCollapsed" class="logo-text">医院管理系统</span>
    </div>
    
    <nav class="sidebar-nav">
      <ul>
        <li v-for="item in menuItems" :key="item.path" class="nav-item">
          <a
            :class="{ active: currentPath === item.path }"
            @click="handleNavClick(item.path)"
          >
            <component :is="item.icon" />
            <span v-show="!isCollapsed">{{ item.label }}</span>
            <el-badge v-if="item.badge" :value="item.badge" class="badge" />
          </a>
        </li>
      </ul>
    </nav>
  </aside>
</template>

<script setup>
import { ref } from 'vue'
import { 
  OfficeBuilding, 
  PieChart, 
  UserFilled, 
  User 
} from '@element-plus/icons-vue'

const isCollapsed = ref(false)
const currentPath = ref('/')

const emit = defineEmits(['navigate'])

const menuItems = [
  { path: '/', label: '仪表盘', icon: PieChart },
  { path: '/hospital', label: '医院管理', icon: OfficeBuilding },
  { path: '/patient', label: '患者管理', icon: UserFilled, badge: 12 },
  { path: '/doctor', label: '医生管理', icon: User }
]

const handleNavClick = (path) => {
  currentPath.value = path
  emit('navigate', path)
}
</script>

<style scoped>
.sidebar {
  width: 220px;
  height: 100vh;
  background: #fff;
  border-right: 1px solid #e8e8e8;
  position: fixed;
  left: 0;
  top: 0;
  z-index: 100;
  transition: width 0.3s ease;
  box-shadow: 2px 0 8px rgba(0, 0, 0, 0.05);
}

.sidebar.collapsed {
  width: 64px;
}

.sidebar-logo {
  height: 60px;
  display: flex;
  align-items: center;
  justify-content: center;
  padding: 0 16px;
  border-bottom: 1px solid #e8e8e8;
  background: linear-gradient(135deg, #165DFF 0%, #0F4CD4 100%);
}

.logo-icon {
  width: 36px;
  height: 36px;
  background: rgba(255, 255, 255, 0.2);
  border-radius: 8px;
  display: flex;
  align-items: center;
  justify-content: center;
  color: #fff;
  font-size: 18px;
}

.logo-text {
  margin-left: 12px;
  font-size: 16px;
  font-weight: 600;
  color: #fff;
}

.sidebar-nav {
  padding: 16px 0;
}

.nav-item {
  list-style: none;
  margin-bottom: 4px;
}

.nav-item a {
  display: flex;
  align-items: center;
  padding: 12px 16px;
  color: #4E5969;
  text-decoration: none;
  border-radius: 0 8px 8px 0;
  transition: all 0.2s ease;
  position: relative;
}

.nav-item a:hover {
  background: #f0f5ff;
  color: #165DFF;
}

.nav-item a.active {
  background: #e8f0ff;
  color: #165DFF;
}

.nav-item a.active::before {
  content: '';
  position: absolute;
  left: 0;
  top: 50%;
  transform: translateY(-50%);
  width: 3px;
  height: 24px;
  background: #165DFF;
  border-radius: 0 2px 2px 0;
}

.nav-item a svg {
  width: 20px;
  height: 20px;
  flex-shrink: 0;
}

.nav-item a span {
  margin-left: 12px;
  font-size: 14px;
}

.badge {
  margin-left: auto;
  margin-right: 8px;
}
</style>
