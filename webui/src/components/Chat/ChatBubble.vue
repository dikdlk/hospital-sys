<template>
  <div class="chat-bubble" @click="toggleChat">
    <div class="bubble-container">
      <div class="bubble-main">
        <MessageCircle class="bubble-icon" />
      </div>
      <div class="bubble-pulse"></div>
      <div class="bubble-pulse delay-1"></div>
      <div class="bubble-pulse delay-2"></div>
    </div>
    <span class="bubble-tooltip">AI 助手</span>
  </div>
</template>

<script setup>
import { MessageCircle } from 'lucide-vue-next'

const emit = defineEmits(['toggle'])

const toggleChat = () => {
  emit('toggle')
}
</script>

<style scoped>
.chat-bubble {
  position: fixed;
  left: 24px;
  bottom: 24px;
  z-index: 1000;
  cursor: pointer;
}

.bubble-container {
  position: relative;
  width: 56px;
  height: 56px;
}

.bubble-main {
  width: 56px;
  height: 56px;
  background: linear-gradient(135deg, #165DFF 0%, #0F4CD4 100%);
  border-radius: 50%;
  display: flex;
  align-items: center;
  justify-content: center;
  box-shadow: 0 4px 16px rgba(22, 93, 255, 0.4);
  transition: all 0.3s ease;
  z-index: 10;
}

.chat-bubble:hover .bubble-main {
  transform: scale(1.1);
  box-shadow: 0 6px 20px rgba(22, 93, 255, 0.5);
}

.bubble-icon {
  width: 28px;
  height: 28px;
  color: #fff;
}

.bubble-pulse {
  position: absolute;
  top: 0;
  left: 0;
  width: 56px;
  height: 56px;
  border-radius: 50%;
  background: rgba(22, 93, 255, 0.3);
  animation: pulse 2s ease-out infinite;
  z-index: 1;
}

.bubble-pulse.delay-1 {
  animation-delay: 0.5s;
}

.bubble-pulse.delay-2 {
  animation-delay: 1s;
}

@keyframes pulse {
  0% {
    transform: scale(1);
    opacity: 0.6;
  }
  100% {
    transform: scale(1.8);
    opacity: 0;
  }
}

.bubble-tooltip {
  position: absolute;
  left: 70px;
  top: 50%;
  transform: translateY(-50%);
  background: #1D2129;
  color: #fff;
  padding: 6px 12px;
  border-radius: 6px;
  font-size: 12px;
  white-space: nowrap;
  opacity: 0;
  transition: opacity 0.3s ease;
  pointer-events: none;
}

.chat-bubble:hover .bubble-tooltip {
  opacity: 1;
}
</style>