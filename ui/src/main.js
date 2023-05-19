import { createApp } from 'vue'
import ElementPlus from 'element-plus'
import 'element-plus/dist/index.css'
import App from './App.vue'
import router from './router'
import store from './store'
import '@/assets/styles/border.css'
import '@/assets/styles/reset.css'
import * as ElementPlusIconsVue from '@element-plus/icons-vue'
import '@/router/permission.js'
import zhCn from 'element-plus/es/locale/lang/zh-cn'


const app = createApp(App)
app.use(ElementPlus, {
    locale: zhCn,
})
app.use(store).use(router).use(ElementPlus).mount('#app')
for (const [key, component] of Object.entries(ElementPlusIconsVue)) {
    app.component(key, component)
}
