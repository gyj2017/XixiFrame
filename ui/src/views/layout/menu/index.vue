<template>
  <el-menu
      active-text-color="#ffd04b"
      background-color="#2d3a4b"
      class="el-menu-vertical-demo"
      default-active="/index"
      text-color="#fff"
      router
  >
    <el-menu-item index="/index">
<!--      <el-icon>
        <location/>
      </el-icon>-->
      <span>首页</span>
    </el-menu-item>

    <el-sub-menu :index="menu.path" v-for="menu in menuList">
      <template #title>
<!--        <el-icon>
          <location/>
        </el-icon>-->
        <span>{{menu.name}}</span>
      </template>

      <el-menu-item :index="item.path" v-for="item in menu.children">
<!--        <el-icon>
          <location/>
        </el-icon>-->
        <span>{{item.name}}</span>
      </el-menu-item>
    </el-sub-menu>




  </el-menu>
</template>

<script setup>
import {Menu as IconMenu,} from '@element-plus/icons-vue'
import HomeView from "@/views/HomeView";
import {HomeFilled,User,Tickets,Goods,DocumentAdd,Management,Setting,Edit,SwitchButton,Promotion} from '@element-plus/icons-vue'
import {ref} from 'vue'
import store from '@/store';
const menuList = ref(store.getters.GET_MENU_LIST);



</script>


<style lang="sass" scoped>

</style>
