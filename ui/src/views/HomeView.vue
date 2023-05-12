<template>
  <div class="home">


    <el-button type="danger" @click="testHandler">测试将诶口</el-button>



  </div>
</template>

<script setup>
import request from "@/util/request";
const testHandler = async ()=>{
  let result = await request.get("test/user/list")
}
</script>
