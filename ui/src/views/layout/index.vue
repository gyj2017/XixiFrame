<template>
  <div class="home">


    <el-button type="danger" @click="testHandler">测试接口</el-button>
    <el-button type="danger" @click="testLogoutHandler">测试logout接口</el-button>



  </div>
</template>

<script setup>
import request from "@/util/request";
const testHandler = async ()=>{
  let result = await request.get("test/user/list")
}

const testLogoutHandler = async ()=>{
  let result = await request.get("logout")
}


</script>
