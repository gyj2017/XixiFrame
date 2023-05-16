<template>
  <el-menu
      active-text-color="#ffd04b"
      background-color="#2d3a4b"
      class="el-menu-vertical-demo"
      :default-active="activeIndex"
      text-color="#fff"
      router
  >
    <el-menu-item index="/index">
      <span>首页</span>
    </el-menu-item>

    <el-sub-menu :index="menu.path" v-for="menu in menuList">
      <template #title>
        <span>{{menu.name}}</span>
      </template>

      <el-menu-item :index="item.path" v-for="item in menu.children" @click="openTab(item)">
        <span>{{item.name}}</span>
      </el-menu-item>
    </el-sub-menu>

  </el-menu>
</template>

<script setup>
import {Menu as IconMenu,} from '@element-plus/icons-vue'
import HomeView from "@/views/HomeView";
import {HomeFilled,User,Tickets,Goods,DocumentAdd,Management,Setting,Edit,SwitchButton,Promotion} from '@element-plus/icons-vue'
import {ref, watch} from 'vue'
import store from '@/store'
const menuList = ref(store.getters.GET_MENU_LIST)

const openTab=(item)=>{
  store.commit('ADD_TABS', item);
}

const activeIndex = ref("/index")

watch(store.state, ()=>{
  console.log("editabletabsValue="+ store.state.editableTabsValue)
  activeIndex.value = store.state.editableTabsValue
}, {deep: true, immediate: true})








</script>


<style lang="scss" scoped>

</style>
