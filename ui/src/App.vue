<template>
  <el-button>Default</el-button>
  <el-button type="primary" @click="handleLogin">测试登录</el-button>
  <el-button type="danger" @click="handleUserList">测试获取用户请求</el-button>

</template>


<script setup>

import request from "@/util/request";
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

}

</script>

<style>


</style>


