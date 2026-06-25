<template>
  <Teleport to="body">
    <Transition name="fade">
      <div v-if="visible" class="chat-window-overlay" @click.self="closeChat">
        <Transition name="scale">
          <div v-if="visible" class="chat-window">
            <div class="chat-header">
              <div class="header-title">
                <Bot class="header-icon" />
                <span>AI 智能助手</span>
              </div>
              <button class="close-btn" @click="closeChat">
                <X class="close-icon" />
              </button>
            </div>
            <div class="chat-body">
              <iframe 
                :src="chatUrl" 
                class="chat-iframe"
                frameborder="0"
                allowfullscreen
              ></iframe>
            </div>
          </div>
        </Transition>
      </div>
    </Transition>
  </Teleport>
</template>

<script setup>
import { Bot, X } from 'lucide-vue-next'

const props = defineProps({
  visible: {
    type: Boolean,
    default: false
  }
})

const emit = defineEmits(['close'])

const chatUrl = 'https://rpcfknh8fy.coze.site/'

const closeChat = () => {
  emit('close')
}
</script>

<style scoped>
.chat-window-overlay {
  position: fixed;
  top: 0;
  left: 0;
  right: 0;
  bottom: 0;
  background: rgba(0, 0, 0, 0.3);
  display: flex;
  align-items: center;
  justify-content: center;
  z-index: 2000;
}

.chat-window {
  width: 800px;
  height: 700px;
  max-width: 90vw;
  max-height: 85vh;
  background: #fff;
  border-radius: 16px;
  box-shadow: 0 20px 60px rgba(0, 0, 0, 0.15);
  display: flex;
  flex-direction: column;
  overflow: hidden;
}

.chat-header {
  height: 48px;
  background: linear-gradient(135deg, #165DFF 0%, #0F4CD4 100%);
  display: flex;
  align-items: center;
  justify-content: space-between;
  padding: 0 16px;
}

.header-title {
  display: flex;
  align-items: center;
  color: #fff;
  font-size: 14px;
  font-weight: 500;
}

.header-icon {
  width: 20px;
  height: 20px;
  margin-right: 8px;
}

.close-btn {
  width: 32px;
  height: 32px;
  background: rgba(255, 255, 255, 0.2);
  border: none;
  border-radius: 8px;
  display: flex;
  align-items: center;
  justify-content: center;
  cursor: pointer;
  transition: background 0.2s ease;
}

.close-btn:hover {
  background: rgba(255, 255, 255, 0.3);
}

.close-icon {
  width: 16px;
  height: 16px;
  color: #fff;
}

.chat-body {
  flex: 1;
  padding: 0;
  overflow: hidden;
}

.chat-iframe {
  width: 100%;
  height: 100%;
  border: none;
}

/* Transitions */
.fade-enter-active,
.fade-leave-active {
  transition: opacity 0.3s ease;
}

.fade-enter-from,
.fade-leave-to {
  opacity: 0;
}

.scale-enter-active,
.scale-leave-active {
  transition: all 0.3s ease;
}

.scale-enter-from,
.scale-leave-to {
  opacity: 0;
  transform: scale(0.9);
}
</style>