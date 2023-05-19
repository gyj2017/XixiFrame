<template>
  <div class="app-container">
    <el-row :gutter="20" class="header">
      <el-col :span="7">
        <el-input placeholder="请输入用户名……" v-model="queryForm.query" clearable></el-input>
      </el-col>
      <el-button type="primary" :icon="Search" @click="initUserList">搜索</el-button>
    </el-row>


    <el-table :data="tableData" stripe style="width: 100%;">
      <el-table-column type="selection" width="55"/>
      <el-table-column prop="avatar" label="头像" width="80" align="center">
        <template v-slot="scope">
          <img :src="getServerUrl() + '/image/userAvatar/'+scope.row.avatar" width="50" height="50">
        </template>
      </el-table-column>
      <el-table-column prop="username" label="用户名" width="180"/>
      <el-table-column prop="roles" label="拥有角色" width="200" align="center">
        <template v-slot="scope">
          <el-tag size="small" type="warning" v-for="item in scope.row.sysRoleList">
            {{item.name}}
          </el-tag>
        </template>
      </el-table-column>




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
import {Search, Delete, DocumentAdd, Edit, Tools, RefreshRight} from "@element-plus/icons-vue";

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

.header{
  padding-bottom: 16px;
  box-sizing: border-box;
}

.el-pagination{
  float: right;
  padding: 20px;
  box-sizing: border-box;
}

::v-deep th.el-table__cell{
  word-break: break-word;
  background-color: #f8f8f9 !important;
  color: #515a6e;
  height: 40px;
  font-size: 13px;

}

.el-tag--small {
  margin-left: 5px;
}







</style>
