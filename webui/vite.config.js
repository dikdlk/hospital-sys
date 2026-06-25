import { defineConfig } from 'vite'
import vue from '@vitejs/plugin-vue'

export default defineConfig({
  plugins: [vue()],
  server: {
    port: 5173,
    proxy: {
      '/hos': {
        target: 'http://localhost:8080',
        changeOrigin: true
      },
      '/sta': {
        target: 'http://localhost:8080',
        changeOrigin: true
      },
      '/doctor': {
        target: 'http://localhost:8080',
        changeOrigin: true
      },
      '/patient': {
        target: 'http://localhost:8080',
        changeOrigin: true
      },
      '/registration': {
        target: 'http://localhost:8080',
        changeOrigin: true
      }
    }
  }
})
