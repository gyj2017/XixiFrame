<template>
用户管理

  <div class="app-container">

    <el-table :data="tableData" stripe style="width: 100%;">
      <el-table-column prop="" label="" width="180"/>
    </el-table>

  </div>
</template>

<script setup>
import {ref} from "vue";
import request,{getServerUrl} from "@/util/request";

const tableData = ref([])



</script>

<style lang="scss" scoped>

</style>
