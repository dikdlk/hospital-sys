<template>
  <header class="top-header">
    <div class="header-left">
      <button class="menu-toggle" @click="toggleSidebar">
        <el-icon><Menu /></el-icon>
      </button>
      <div class="breadcrumb">
        <span class="breadcrumb-item">首页</span>
        <el-icon class="breadcrumb-separator"><ArrowRight /></el-icon>
        <span class="breadcrumb-item active">{{ currentPage }}</span>
      </div>
    </div>
    
    <div class="header-right">
      <el-badge :value="3" class="notification-badge">
        <el-button circle class="header-btn">
          <el-icon><Bell /></el-icon>
        </el-button>
      </el-badge>
      
      <el-dropdown trigger="click">
        <div class="user-info">
          <el-avatar :size="32" :icon="UserFilled" class="user-avatar" />
          <span class="user-name">管理员</span>
          <el-icon><ArrowDown /></el-icon>
        </div>
        <template #dropdown>
          <el-dropdown-menu>
            <el-dropdown-item>
              <el-icon><User /></el-icon>
              <span>个人中心</span>
            </el-dropdown-item>
            <el-dropdown-item>
              <el-icon><Setting /></el-icon>
              <span>系统设置</span>
            </el-dropdown-item>
            <el-dropdown-item divided>
              <el-icon><SwitchButton /></el-icon>
              <span>退出登录</span>
            </el-dropdown-item>
          </el-dropdown-menu>
        </template>
      </el-dropdown>
    </div>
  </header>
</template>

<script setup>
import { 
  Menu, 
  Bell, 
  User, 
  UserFilled,
  ArrowRight, 
  ArrowDown, 
  Setting, 
  SwitchButton
} from '@element-plus/icons-vue'

defineProps({
  currentPage: {
    type: String,
    default: '仪表盘'
  }
})

const emit = defineEmits(['toggle-sidebar'])

const toggleSidebar = () => {
  emit('toggle-sidebar')
}
</script>

<style scoped>
.top-header {
  height: 60px;
  background: #fff;
  border-bottom: 1px solid #e8e8e8;
  display: flex;
  align-items: center;
  justify-content: space-between;
  padding: 0 20px;
  position: fixed;
  left: 220px;
  right: 0;
  top: 0;
  z-index: 99;
  box-shadow: 0 2px 8px rgba(0, 0, 0, 0.04);
}

.header-left {
  display: flex;
  align-items: center;
  gap: 16px;
}

.menu-toggle {
  width: 36px;
  height: 36px;
  border: none;
  background: #f5f7fa;
  border-radius: 8px;
  display: flex;
  align-items: center;
  justify-content: center;
  cursor: pointer;
  color: #4E5969;
  transition: all 0.2s ease;
}

.menu-toggle:hover {
  background: #e8e8e8;
}

.breadcrumb {
  display: flex;
  align-items: center;
  gap: 8px;
}

.breadcrumb-item {
  font-size: 14px;
  color: #4E5969;
}

.breadcrumb-item.active {
  color: #1D2129;
  font-weight: 500;
}

.breadcrumb-separator {
  color: #8F959E;
  font-size: 14px;
}

.header-right {
  display: flex;
  align-items: center;
  gap: 16px;
}

.header-btn {
  width: 36px;
  height: 36px;
  border: none;
  background: transparent;
  color: #4E5969;
}

.header-btn:hover {
  background: #f5f7fa;
  color: #165DFF;
}

.notification-badge :deep(.el-badge__content) {
  top: 4px;
  right: 4px;
}

.user-info {
  display: flex;
  align-items: center;
  gap: 8px;
  cursor: pointer;
  padding: 4px 8px;
  border-radius: 8px;
  transition: all 0.2s ease;
}

.user-info:hover {
  background: #f5f7fa;
}

.user-avatar {
  background: #e8f0ff;
  color: #165DFF;
}

.user-name {
  font-size: 14px;
  color: #1D2129;
}
</style>
