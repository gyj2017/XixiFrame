<template>
  <div class="app-container">
    <el-table :data="tableData" stripe style="width: 100%;">
      <el-table-column prop="username" label="用户名" width="180"/>
    </el-table>
    <el-pagination
        v-model:current-page="queryForm.pageNum"
        v-model:page-size="queryForm.pageSize"
        :page-sizes="[2, 3, 4, 5]"
        layout="total, sizes, prev, pager, next, jumper"
        :total="total"
        @size-change="handleSizeChange"
        @current-change="handleCurrentChange"
    />
  </div>
</template>

<script setup>
import {ref} from "vue";
import request,{getServerUrl} from "@/util/request";

const tableData = ref([])
const queryForm = ref({
  query:'',
  pageNum:1,
  pageSize:10
})

const handleSizeChange=async(pageSize)=>{
  queryForm.value.pageNum = 1;
  queryForm.value.pageSize = pageSize;
  initUserList();

}

const handleCurrentChange=async(pageNum)=>{
  queryForm.value.pageNum = pageNum;
  initUserList();
}

const total = ref(0)

const initUserList=async()=>{
  const res = await request.post("sys/user/list", queryForm.value);
  console.log(res)
  tableData.value = res.data.userList
  total.value = res.data.total
}

initUserList();
</script>

<style lang="scss" scoped>








</style>
