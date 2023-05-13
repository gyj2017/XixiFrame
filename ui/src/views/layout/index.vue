<!--<template>
  <div class="home">
    <el-button type="danger" @click="testHandler">测试接口</el-button>
    <el-button type="danger" @click="testLogoutHandler">测试logout接口</el-button>
  </div>
</template>-->


<template>
  <div class="app-wrapper">
    <el-container>
      <el-aside width="200px" class="sidebar-container"><Menu/></el-aside>
      <el-container>
        <el-header><Header/></el-header>
        <el-main>
          <Tabs/>
          <router-view/>

        </el-main>
        <el-footer><Footer/></el-footer>
      </el-container>
    </el-container>
  </div>
</template>



<script setup>
import request from "@/util/request";
/*const testHandler = async ()=>{
  let result = await request.get("test/user/list")
}
const testLogoutHandler = async ()=>{
  let result = await request.get("logout")
}*/

import Menu from '@/views/layout/menu'
import Header from '@/views/layout/header'
import Footer from '@/views/layout/footer'
import Tabs from '@/views/layout/tabs'


</script>

<style scoped>
.app-wrapper {
  position: relative;
  width: 100%;
  height: 100%;
}

.sidebar-container {
  background-color: #2d3a4b;
  height: 100%;
}

.el-container{
  height:100%
}

.el-header{
  padding-left: 0px;
  padding-right: 0px;
}

:deep(ul.el-menu){
  border-right-width: 0px
}

</style>
