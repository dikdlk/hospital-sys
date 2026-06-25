<template>
  <div class="app-container">
    <Sidebar @navigate="handleNavigate" />
    <div class="main-content" :class="{ 'sidebar-collapsed': isSidebarCollapsed }">
      <Header :current-page="currentPageTitle" @toggle-sidebar="toggleSidebar" />
      <div class="page-content">
        <Dashboard v-if="currentPage === '/'" />
        <Hospital v-else-if="currentPage === '/hospital'" />
        <Doctor v-else-if="currentPage === '/doctor'" />
        <Patient v-else-if="currentPage === '/patient'" />
        <div v-else class="page-placeholder">
          <div class="placeholder-content">
            <component :is="Notebook" class="placeholder-icon" />
            <p>页面开发中...</p>
          </div>
        </div>
      </div>
    </div>
    <ChatBubble @toggle="toggleChatWindow" />
    <ChatWindow :visible="isChatVisible" @close="closeChatWindow" />
  </div>
</template>

<script setup>
import { ref, computed } from 'vue'
import { Notebook } from '@element-plus/icons-vue'
import Sidebar from './components/Layout/Sidebar.vue'
import Header from './components/Layout/Header.vue'
import Dashboard from './views/Dashboard.vue'
import Hospital from './views/Hospital.vue'
import Doctor from './views/Doctor.vue'
import Patient from './views/Patient.vue'
import ChatBubble from './components/Chat/ChatBubble.vue'
import ChatWindow from './components/Chat/ChatWindow.vue'

const currentPage = ref('/')
const isSidebarCollapsed = ref(false)
const isChatVisible = ref(false)

const pageTitleMap = {
  '/': '仪表盘',
  '/hospital': '医院管理',
  '/patient': '患者管理',
  '/doctor': '医生管理'
}

const currentPageTitle = computed(() => pageTitleMap[currentPage.value] || '仪表盘')

const handleNavigate = (path) => {
  currentPage.value = path
}

const toggleSidebar = () => {
  isSidebarCollapsed.value = !isSidebarCollapsed.value
}

const toggleChatWindow = () => {
  isChatVisible.value = !isChatVisible.value
}

const closeChatWindow = () => {
  isChatVisible.value = false
}
</script>

<style scoped>
.app-container {
  display: flex;
  min-height: 100vh;
  background: #f5f7fa;
}

.main-content {
  flex: 1;
  min-height: 100vh;
  margin-left: 220px;
  transition: margin-left 0.3s ease;
}

.main-content.sidebar-collapsed {
  margin-left: 64px;
}

.page-content {
  padding-top: 60px;
  min-height: calc(100vh - 60px);
}

.page-placeholder {
  display: flex;
  align-items: center;
  justify-content: center;
  height: 50vh;
}

.placeholder-content {
  text-align: center;
  color: #8F959E;
}

.placeholder-icon {
  width: 64px;
  height: 64px;
  margin-bottom: 16px;
  opacity: 0.5;
}

.placeholder-content p {
  font-size: 14px;
}
</style>

<style>
* {
  margin: 0;
  padding: 0;
  box-sizing: border-box;
}

body {
  font-family: 'PingFang SC', 'Microsoft YaHei', 'Segoe UI', sans-serif;
  font-size: 14px;
  color: #1D2129;
  background: #f5f7fa;
}

::-webkit-scrollbar {
  width: 6px;
  height: 6px;
}

::-webkit-scrollbar-track {
  background: #f1f1f1;
}

::-webkit-scrollbar-thumb {
  background: #c1c1c1;
  border-radius: 3px;
}

::-webkit-scrollbar-thumb:hover {
  background: #a8a8a8;
}
</style>
