<template>
<!--  <el-button>Default</el-button>
  <el-button type="primary" @click="handleLogin">测试登录</el-button>
  <el-button type="danger" @click="handleUserList">测试获取用户请求</el-button>-->

  <router-view/>

</template>


<script setup>
import {ref, watch} from "vue";
import { useRoute, useRouter } from 'vue-router'
const route = useRoute();
const router = useRouter();
import store from '@/store'
const whitePath = ['/login', '/index', '/']

watch(route, (to, from)=>{
  console.log("to" + to.name)
  console.log(to.path)
  if (whitePath.indexOf(to.path) === -1){
    console.log("to.path="+to.path)
    let obj = {
      name: to.name,
      path: to.path
    }
    store.commit("ADD_TABS", obj)
  }
},{deep: true, immediate:true})


/*import request from "@/util/request";
import store from "@/store";
import { get, post, fileupload } from "@/util/request";

const handleLogin = async () => {
  let result = await request.get("test/login");
  console.log(result)

  if (result.code = 200) {
    const token = result.data;
    console.log("登录成功，token=" + token);
    store.commit("SET_TOKEN", token);
  } else {
    console.log("登录出错");
  }

}
const handleUserList = async () => {
  let result = await get("test/user/list");
  console.log(result)
  if (result.code = 200) {
    const userlist = result.data;
    console.log("用户信息列表，userList="+userlist);
  } else {
    console.log("登录出错");
  }
}*/

</script>

<style>

html,body,#app{
  height: 100%;
}

.app-container{
  padding-top: 20px;
}

</style>


